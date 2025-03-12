package uam.polytech.dstti.app_file_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameAdmin;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNameAdmin() {
        return nameAdmin;
    }
    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

}
