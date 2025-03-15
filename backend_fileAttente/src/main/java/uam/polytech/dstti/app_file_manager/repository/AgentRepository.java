package uam.polytech.dstti.app_file_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uam.polytech.dstti.app_file_manager.model.Agent;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {
        List<Agent> findByServiceAppIdAndLocalisationId(Long serviceId, Long localisationId);

}
