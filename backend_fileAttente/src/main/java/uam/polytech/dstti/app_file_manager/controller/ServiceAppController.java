package uam.polytech.dstti.app_file_manager.controller;

import org.springframework.web.bind.annotation.*;
import uam.polytech.dstti.app_file_manager.model.ServiceApp;
import uam.polytech.dstti.app_file_manager.repository.ServiceAppRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceAppController {
    private final ServiceAppRepository serviceAppRepository;

    public ServiceAppController(ServiceAppRepository serviceAppRepository) {
        this.serviceAppRepository = serviceAppRepository;
    }
    @GetMapping
    public List<ServiceApp> getAllServices() {
        return serviceAppRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ServiceApp> getServiceById(@PathVariable Long id) {
        return serviceAppRepository.findById(id);
    }


//    @DeleteMapping("/{id}")
//    public void deleteService(@PathVariable Long id) {
//        serviceAppRepository.deleteById(id);
//    }


}
