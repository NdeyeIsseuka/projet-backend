package uam.polytech.dstti.app_file_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uam.polytech.dstti.app_file_manager.model.ServiceApp;

import java.util.List;

@Repository
public interface ServiceAppRepository extends JpaRepository<ServiceApp, Long>
{
//    List<ServiceApp> findByNameService(String nameService);
}
