package uam.polytech.dstti.app_file_manager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ServiceApp {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameService;

    public List<Localisation> getLocalisation() {
        return localisation;
    }

    public void setLocalisation(List<Localisation> localisation) {
        this.localisation = localisation;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "ServiceWithLocalisation",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "localisation_id")
    )
    private List<Localisation> localisation ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

//    @OneToOne(mappedBy = "serviceApp")
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
