package org.example.app.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cases")
public class CourtCaseController {

    private final CourtCaseRepository repository;
    private final PetitionerRepository petitionerRepository;

    public CourtCaseController(CourtCaseRepository repository,
                               PetitionerRepository petitionerRepository) {
        this.repository = repository;
        this.petitionerRepository = petitionerRepository;
    }

    @GetMapping
    public String listCases(Model model) {
        model.addAttribute("cases", repository.findAll());
        return "cases";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("courtCase", new CourtCase());
        model.addAttribute("petitioners", petitionerRepository.findAll());
        return "add-case";
    }

    @PostMapping
    public String saveCase(@ModelAttribute CourtCase courtCase) {
        repository.save(courtCase);
        return "redirect:/cases";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        CourtCase courtCase = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid case id: " + id));
        model.addAttribute("courtCase", courtCase);
        model.addAttribute("petitioners", petitionerRepository.findAll());
        return "edit-case";
    }

    @PostMapping("/update")
    public String updateCase(@ModelAttribute CourtCase courtCase) {
        courtCase.markAsExisting();
        repository.save(courtCase);
        return "redirect:/cases";
    }

    @PostMapping("/delete/{id}")
    public String deleteCase(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/cases";
    }
}