package uam.polytech.dstti.app_file_manager.controller;

import org.springframework.web.bind.annotation.*;
import uam.polytech.dstti.app_file_manager.model.Localisation;
import uam.polytech.dstti.app_file_manager.repository.LocalisationRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/localisations")
public class LocalisationController {

    private final LocalisationRepository localisationRepository;
    public LocalisationController(LocalisationRepository localisationRepository) {
        this.localisationRepository = localisationRepository;
    }
    @GetMapping
    public List<Localisation> getAllLocalisations() {
        return localisationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Localisation> getLocalisationById(@PathVariable Long id) {
        return localisationRepository.findById(id);
    }

//    @DeleteMapping("/{id}")
//    public void deleteLocalisation(@PathVariable Long id) {
//        localisationRepository.deleteById(id);
//    }
}
