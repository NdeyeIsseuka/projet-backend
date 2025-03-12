package uam.polytech.dstti.app_file_manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uam.polytech.dstti.app_file_manager.model.Localisation;
import uam.polytech.dstti.app_file_manager.model.ServiceApp;
import uam.polytech.dstti.app_file_manager.model.Ticket;
import uam.polytech.dstti.app_file_manager.repository.LocalisationRepository;
import uam.polytech.dstti.app_file_manager.repository.ServiceAppRepository;
import uam.polytech.dstti.app_file_manager.repository.TicketRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

@Service
public class ServiceManagement {

    private final ServiceAppRepository serviceAppRepository;
    private final LocalisationRepository localisationRepository;

    // Map pour stocker les compteurs de tickets par localisation et service
    private final Map<String, Integer> ticketCounters = new HashMap<>();
    private Map<String, Integer> currentNumbers = new HashMap<>();

    public ServiceManagement(ServiceAppRepository serviceAppRepository, LocalisationRepository localisationRepository) {
        this.serviceAppRepository = serviceAppRepository;
        this.localisationRepository = localisationRepository;
    }
        public Ticket generateTicket(Long serviceId, Long localisationId) {
            ServiceApp service = serviceAppRepository.findById(serviceId).orElseThrow(
                    () -> new IllegalArgumentException("serviceId: " + serviceId + " not found"));
            Localisation localisation = localisationRepository.findById(localisationId).orElseThrow(
                    () -> new IllegalArgumentException("localisationId: " + localisationId + " not found"));
            // Générer une clé unique pour la combinaison localisation-service
            String key = generateKey(localisationId, serviceId);

            // Initialiser le compteur si nécessaire
            ticketCounters.putIfAbsent(key, 100);

            // Obtenir le numéro de ticket actuel et l'incrémenter
            int numeroTicket = ticketCounters.get(key);
            ticketCounters.put(key, numeroTicket + 1);

            // Calculer les autres champs du ticket
            int currentNumber = numeroTicket; // Le numéro en cours est le numéro actuel du ticket
            int positionInFile = numeroTicket - currentNumber; // Position dans la file d'attente (0 car c'est le premier)
            int nbrPersonneAvant = numeroTicket - 100; // Nombre de personnes devant

        // Créer et retourner le ticket
            return new Ticket(localisationId, serviceId, numeroTicket, positionInFile, nbrPersonneAvant, currentNumber);
        }

    public int processNextTicket(Long localisationId, Long serviceId) {
        String key = generateKey(localisationId, serviceId);
        int currentNumber = currentNumbers.getOrDefault(key, 100);
        currentNumbers.put(key, currentNumber + 1);
        return currentNumbers.get(key);
    }

    public int processPreviousTicket(Long localisationId, Long serviceId) {
        String key = generateKey(localisationId, serviceId);
        int currentNumber = currentNumbers.getOrDefault(key, 100);
        if (currentNumber > 100) {
            currentNumbers.put(key, currentNumber - 1);
        }
        return currentNumbers.get(key);
    }

    private String generateKey(Long localisationId, Long serviceId) {
        return "loc-" + localisationId + "-srv-" + serviceId;
    }

}
