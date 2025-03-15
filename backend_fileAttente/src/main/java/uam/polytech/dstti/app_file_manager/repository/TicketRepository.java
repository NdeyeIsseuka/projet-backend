package uam.polytech.dstti.app_file_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uam.polytech.dstti.app_file_manager.model.Ticket;
import uam.polytech.dstti.app_file_manager.model.TicketId;

public interface TicketRepository extends JpaRepository<Ticket, TicketId>{

    Ticket findByLocalisationIdAndServiceId(Long localisationId, Long serviceId);

    Ticket findTopByLocalisationIdAndServiceIdOrderByCurrentNumberDesc(Long localisationId, Long serviceId);
}
