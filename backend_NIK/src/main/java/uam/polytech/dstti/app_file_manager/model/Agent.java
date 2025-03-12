package uam.polytech.dstti.app_file_manager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Agent {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameAgent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAgent() {
        return nameAgent;
    }
    public void setNameAgent(String nameAgent) {
        this.nameAgent = nameAgent;
    }

    // chaque agent a son propre service avec sa localisation

    // donc on doit avoir une relation avec ServiceApp et une relation avec Localisation

    // 1. Relation avec ServiceApp
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceApp serviceApp;
    // 2. Relation avec localisation
    @ManyToOne
    @JoinColumn(name = "localisation_id")
    private Localisation localisation;

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public ServiceApp getServiceApp() {
        return serviceApp;
    }

    public void setServiceApp(ServiceApp serviceApp) {
        this.serviceApp = serviceApp;
    }




}

