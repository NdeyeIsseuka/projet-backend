package uam.polytech.dstti.app_file_manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uam.polytech.dstti.app_file_manager.model.AdminDTO;
import uam.polytech.dstti.app_file_manager.model.Agent;
import uam.polytech.dstti.app_file_manager.model.Localisation;
import uam.polytech.dstti.app_file_manager.model.ServiceApp;
import uam.polytech.dstti.app_file_manager.repository.AgentRepository;
import uam.polytech.dstti.app_file_manager.repository.LocalisationRepository;
import uam.polytech.dstti.app_file_manager.repository.ServiceAppRepository;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AgentRepository agentRepository;
    private final LocalisationRepository localisationRepository;
    private final ServiceAppRepository serviceAppRepository;

    public AdminController(AgentRepository agentRepository,
                           LocalisationRepository localisationRepository,
                           ServiceAppRepository serviceAppRepository) {
        this.agentRepository = agentRepository;
        this.localisationRepository = localisationRepository;
        this.serviceAppRepository = serviceAppRepository;
    };

    @GetMapping
    public AdminDTO getAdminDashboard() {
        List<ServiceApp> services = serviceAppRepository.findAll();
        List<Localisation> localisations = localisationRepository.findAll();
        List<Agent> agents = agentRepository.findAll();

        return new AdminDTO(services, localisations, agents);
    }
}
