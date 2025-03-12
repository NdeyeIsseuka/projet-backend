package uam.polytech.dstti.app_file_manager.model;

import java.util.List;

public class AdminDTO {
    private List<ServiceApp> services;
    private List<Localisation> localisations;
    private List<Agent> agents;


    public AdminDTO(List<ServiceApp> services, List<Localisation> localisations, List<Agent> agents) {
        this.services = services;
        this.localisations = localisations;
        this.agents = agents;
    }

    public List<ServiceApp> getServices() {
        return services;
    }

    public void setServices(List<ServiceApp> services) {
        this.services = services;
    }
    public List<Localisation> getLocalisations() {
        return localisations;
    }
    public void setLocalisations(List<Localisation> localisations) {
        this.localisations = localisations;
    }
    public List<Agent> getAgents() {
        return agents;
    }
    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }


}
