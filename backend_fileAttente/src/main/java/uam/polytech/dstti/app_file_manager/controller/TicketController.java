package uam.polytech.dstti.app_file_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uam.polytech.dstti.app_file_manager.model.Ticket;
import uam.polytech.dstti.app_file_manager.repository.TicketRepository;
import uam.polytech.dstti.app_file_manager.services.ServiceManagement;
import uam.polytech.dstti.app_file_manager.services.TicketService;


@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final ServiceManagement serviceManagement;
    private TicketRepository ticketRepository;

    public TicketController(TicketService ticketService, ServiceManagement serviceManagement, TicketRepository ticketRepository) {
        this.serviceManagement = serviceManagement;
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
    }

    @PostMapping
    public ResponseEntity<Ticket>
    generateTicket(@RequestBody Ticket ticketLocSer) {
        System.out.println("serviceId: " + ticketLocSer.getServiceId());
        System.out.println("localisationId: " + ticketLocSer.getLocalisationId());
        Ticket ticket = ticketService.generateTicket(ticketLocSer.getServiceId(), ticketLocSer.getLocalisationId());
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }


    @GetMapping("/current-ticket/{localisationId}/{serviceId}")
    public ResponseEntity<Integer> getCurrentTicketNumber(@PathVariable Long localisationId, @PathVariable Long serviceId) {
        int currentNumber = ticketService.getCurrentTicketNumber(localisationId, serviceId);
        return ResponseEntity.ok(currentNumber);
    }


    @PostMapping("/next/{localisationId}/{serviceId}")
//    public ResponseEntity<Integer> processNextTicket(@PathVariable Long localisationId, @PathVariable Long serviceId) {
//        int currentNumber = serviceManagement.processNextTicket(localisationId, serviceId);
//        return new ResponseEntity<>(currentNumber, HttpStatus.OK);
//    }
    public int processNextTicket(Long localisationId, Long serviceId) {
        Ticket currentTicket = ticketRepository.findTopByLocalisationIdAndServiceIdOrderByCurrentNumberDesc(localisationId, serviceId);

        if (currentTicket != null) {
            // Incrémenter le currentNumber
            currentTicket.setCurrentNumber(currentTicket.getCurrentNumber() + 1);
            ticketRepository.save(currentTicket);
        } else {
            // Si aucun ticket actuel n'est trouvé, on commence par 1
            currentTicket = new Ticket(localisationId, serviceId, 1, 0, 0, 1);
            ticketRepository.save(currentTicket);
        }

        return currentTicket.getCurrentNumber();
    }

    @PostMapping("/previous/{localisationId}/{serviceId}")
//    public ResponseEntity<Integer> processPreviousTicket(@PathVariable Long localisationId, @PathVariable Long serviceId) {
//        int currentNumber = serviceManagement.processPreviousTicket(localisationId, serviceId);
//        return new ResponseEntity<>(currentNumber, HttpStatus.OK);
//    }
    public int processPreviousTicket(Long localisationId, Long serviceId) {
        Ticket currentTicket = ticketRepository.findTopByLocalisationIdAndServiceIdOrderByCurrentNumberDesc(localisationId, serviceId);

        if (currentTicket != null && currentTicket.getCurrentNumber() > 1) {
            // Décrémenter le currentNumber
            currentTicket.setCurrentNumber(currentTicket.getCurrentNumber() - 1);
            ticketRepository.save(currentTicket);
        }

        return (currentTicket != null) ? currentTicket.getCurrentNumber() : 1;
    }


}
