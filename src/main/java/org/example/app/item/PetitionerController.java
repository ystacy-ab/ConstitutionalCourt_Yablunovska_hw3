package org.example.app.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/petitioners")
public class PetitionerController {

    private final PetitionerRepository repository;
    private final IndividualPetitionerRepository individualRepo;
    private final OrganizationPetitionerRepository organizationRepo;

    public PetitionerController(PetitionerRepository repository,
                                IndividualPetitionerRepository individualRepo,
                                OrganizationPetitionerRepository organizationRepo) {
        this.repository = repository;
        this.individualRepo = individualRepo;
        this.organizationRepo = organizationRepo;
    }

    @GetMapping
    public String listPetitioners(Model model) {
        model.addAttribute("petitioners", repository.findAll());
        return "petitioners";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        return "add-petitioner";
    }

    @PostMapping
    public String savePetitioner(
            @RequestParam Integer petitionerId,
            @RequestParam String petitionerName,
            @RequestParam String petitionerType,
            @RequestParam(required = false) String passportId,
            @RequestParam(required = false) String registrationNumber) {

        Petitioner petitioner = new Petitioner();
        petitioner.setPetitionerId(petitionerId);
        petitioner.setPetitionerName(petitionerName);
        petitioner.setPetitionerType(petitionerType);
        repository.save(petitioner);

        if ("Individual".equals(petitionerType) && passportId != null && !passportId.isBlank()) {
            IndividualPetitioner ind = new IndividualPetitioner();
            ind.setPetitionerId(petitionerId);
            ind.setPassportId(passportId);
            individualRepo.save(ind);
        } else if ("Organization".equals(petitionerType) && registrationNumber != null && !registrationNumber.isBlank()) {
            OrganizationPetitioner org = new OrganizationPetitioner();
            org.setPetitionerId(petitionerId);
            org.setRegistrationNumber(registrationNumber);
            organizationRepo.save(org);
        }

        return "redirect:/petitioners";
    }

    @PostMapping("/delete/{id}")
    public String deletePetitioner(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/petitioners";
    }
}