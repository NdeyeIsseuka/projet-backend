package uam.polytech.dstti.app_file_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uam.polytech.dstti.app_file_manager.model.Agent;
import uam.polytech.dstti.app_file_manager.model.AgentRequest;
import uam.polytech.dstti.app_file_manager.repository.AgentRepository;
import uam.polytech.dstti.app_file_manager.services.AgentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agents")
public class AgentController {

    private final AgentRepository agentRepository;
    private final AgentService agentService;

    public AgentController(AgentRepository agentRepository, AgentService agentService) {
        this.agentRepository = agentRepository;
        this.agentService = agentService;
    }

    @GetMapping
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Agent> getAgentById(@PathVariable Long id) {
        return agentRepository.findById(id);
    }

    @GetMapping("/current-ticket/{localisationId}/{serviceId}")
    public ResponseEntity<Integer> getCurrentTicketNumber(@PathVariable Long localisationId,
                                                          @PathVariable Long serviceId) {
        int currentNumber = agentService.getCurrentTicketNumber(localisationId, serviceId);
        return ResponseEntity.ok(currentNumber);
    }


    @PostMapping("/next")
    public ResponseEntity<Integer> processNext(@RequestBody AgentRequest request) {
        try {
            int nextTicket = agentService.processNextTicket(request.getLocalisationId(), request.getServiceId());
            return new ResponseEntity<>(nextTicket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/previous")
    public ResponseEntity<Integer> processPrevious(@RequestBody AgentRequest request) {
        try {
            int previousTicket = agentService.processPreviousTicket(request.getLocalisationId(), request.getServiceId());
            return new ResponseEntity<>(previousTicket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
