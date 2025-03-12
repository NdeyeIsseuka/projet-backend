package uam.polytech.dstti.app_file_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uam.polytech.dstti.app_file_manager.model.Ticket;
import uam.polytech.dstti.app_file_manager.services.ServiceManagement;
import uam.polytech.dstti.app_file_manager.services.TicketService;


@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final ServiceManagement serviceManagement;

    public TicketController(TicketService ticketService, ServiceManagement serviceManagement) {
        this.serviceManagement = serviceManagement;
        this.ticketService = ticketService;
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
    public ResponseEntity<Integer> processNextTicket(@PathVariable Long localisationId, @PathVariable Long serviceId) {
        int currentNumber = serviceManagement.processNextTicket(localisationId, serviceId);
        return new ResponseEntity<>(currentNumber, HttpStatus.OK);
    }

    @PostMapping("/previous/{localisationId}/{serviceId}")
    public ResponseEntity<Integer> processPreviousTicket(@PathVariable Long localisationId, @PathVariable Long serviceId) {
        int currentNumber = serviceManagement.processPreviousTicket(localisationId, serviceId);
        return new ResponseEntity<>(currentNumber, HttpStatus.OK);
    }
}
