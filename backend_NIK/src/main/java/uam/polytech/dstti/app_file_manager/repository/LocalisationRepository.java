package uam.polytech.dstti.app_file_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uam.polytech.dstti.app_file_manager.model.Localisation;

import java.util.List;

public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
}
