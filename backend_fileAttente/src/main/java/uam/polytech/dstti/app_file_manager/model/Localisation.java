package uam.polytech.dstti.app_file_manager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Localisation {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) pour des soucis de persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomLocalisation;

    @ManyToMany(mappedBy = "localisation", cascade = CascadeType.PERSIST)
    private List<ServiceApp> services;

    public List<ServiceApp> getServices() {
        return services;
    }

    public void setServices(List<ServiceApp> services) {
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomLocalisation() {
        return nomLocalisation;
    }

    public void setNomLocalisation(String nomLocalisation) {
        this.nomLocalisation = nomLocalisation;
    }

//    @OneToOne(mappedBy = "localisation")
//    private Agent agent;
//
//    public Agent getAgent() {
//        return agent;
//    }
//
//    public void setAgent(Agent agent) {
//        this.agent = agent;
//    }
}
