package uam.polytech.dstti.app_file_manager.services;

import org.springframework.stereotype.Service;
import uam.polytech.dstti.app_file_manager.model.Agent;
import uam.polytech.dstti.app_file_manager.repository.AgentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@Service
public class AgentService {

    // Map pour stocker les numéros en cours de traitement par agent
    private final Map<String, Integer> currentTicketNumbers = new HashMap<>();
    private AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }

    public Optional<Agent> getAgentById(Long id) {
        return agentRepository.findById(id);
    }

    public int getCurrentTicketNumber(Long localisationId, Long serviceId) {
        String key = generateKey(localisationId, serviceId);
        return currentTicketNumbers.getOrDefault(key, 100);
    }

    public int processNextTicket(Long localisationId, Long serviceId) {
        String key = generateKey(localisationId, serviceId);
        int currentNumber = currentTicketNumbers.getOrDefault(key, 100);
        int nextNumber = currentNumber + 1;
        currentTicketNumbers.put(key, nextNumber);
        return nextNumber;
    }

    public int processPreviousTicket(Long localisationId, Long serviceId) {
        String key = generateKey(localisationId, serviceId);
        int currentNumber = currentTicketNumbers.getOrDefault(key, 100);
        int previousNumber = Math.max(currentNumber - 1, 100);
        currentTicketNumbers.put(key, previousNumber);
        return previousNumber;
    }

    private String generateKey(Long localisationId, Long serviceId) {
        return "loc-" + localisationId + "-srv-" + serviceId;
    }
}