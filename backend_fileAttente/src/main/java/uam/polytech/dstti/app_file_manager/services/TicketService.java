package uam.polytech.dstti.app_file_manager.services;

import org.springframework.stereotype.Service;
import uam.polytech.dstti.app_file_manager.model.*;
import uam.polytech.dstti.app_file_manager.repository.*;

@Service
public class TicketService {


    private final ServiceManagement serviceManagement;
    private AgentRepository agentRepository;
    private ServiceAppRepository serviceAppRepository;
    private LocalisationRepository localisationRepository;
    private TicketRepository ticketRepository;

    public TicketService(AgentRepository agentRepository, ServiceAppRepository serviceAppRepository, LocalisationRepository localisationRepository, TicketRepository ticketRepository, ServiceManagement serviceManagement) {
        this.agentRepository = agentRepository;
        this.serviceAppRepository = serviceAppRepository;
        this.localisationRepository = localisationRepository;
        this.ticketRepository = ticketRepository;
        this.serviceManagement = serviceManagement;
    }

    public Ticket generateTicket(Long serviceId, Long localisationId) {
       return serviceManagement.generateTicket(serviceId, localisationId);
    }


    public Ticket generateNewTicket(Long localisationId, Long serviceId) {
        Ticket ticket = new Ticket();
        ticket.setLocalisationId(localisationId);
        ticket.setServiceId(serviceId);

        // Générer le nouveau numéro de ticket (exemple)
        int nextTicketNumber = getNextTicketNumber(localisationId, serviceId);
        ticket.setNumeroTicket(nextTicketNumber);

        // Récupérer le numéro actuel de traitement
        int currentTicketNumber = getCurrentTicketNumber(localisationId, serviceId);

//        // Calcul de la position dans la file
//        int positionInFile = currentTicketNumber - 1;
//        ticket.setPositionInFile(positionInFile);
//
//        // Calcul du nombre de personnes devant
//        int nbrPersonneAvant = nextTicketNumber > currentTicketNumber ? nextTicketNumber - currentTicketNumber : 0;
//        ticket.setNbrPersonneAvant(nbrPersonneAvant);

        // Enregistrer le numéro actuel de traitement
        ticket.setCurrentNumber(currentTicketNumber);

        // Sauvegarder le ticket
        return ticketRepository.save(ticket);
    }

//    public int getCurrentTicketNumber(Long localisationId, Long serviceId) {
//        Ticket ticket = ticketRepository.findByLocalisationIdAndServiceId(localisationId, serviceId);
//        return (ticket != null) ? ticket.getCurrentNumber() : 100;
//    }

    public int getCurrentTicketNumber(Long localisationId, Long serviceId) {
        // Obtenir le ticket avec le plus petit numéro de ticket qui est supérieur ou égal au numéro actuel de traitement
        Ticket currentTicket = ticketRepository.findTopByLocalisationIdAndServiceIdOrderByCurrentNumberDesc(localisationId, serviceId);

        if (currentTicket != null) {
            // Retourner le numéro actuel de traitement si un ticket existe
            return currentTicket.getCurrentNumber();
        } else {
            // Si aucun ticket n'existe, commencer à 100
            return 100;
        }
    }



    private int getNextTicketNumber(Long localisationId, Long serviceId) {
        Ticket ticket = ticketRepository.findByLocalisationIdAndServiceId(localisationId, serviceId);
        if (ticket == null) {
            // Si aucun ticket n'existe, commencer à 100
            return 100;
        } else {
            // Sinon, incrémenter le numéro existant
            return ticket.getCurrentNumber() + 1;
        }
    }
}
