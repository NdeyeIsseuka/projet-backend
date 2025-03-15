package uam.polytech.dstti.app_file_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uam.polytech.dstti.app_file_manager.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
