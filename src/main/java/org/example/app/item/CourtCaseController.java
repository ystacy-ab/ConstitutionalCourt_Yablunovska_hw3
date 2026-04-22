package org.example.app.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cases")
public class CourtCaseController {
    private final CourtCaseRepository repository;

    public CourtCaseController(CourtCaseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listCases(Model model) {
        model.addAttribute("cases", repository.findAll());
        return "cases";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("courtCase", new CourtCase());
        return "add-case";
    }

    @PostMapping
    public String saveCase(@ModelAttribute CourtCase courtCase) {
        repository.save(courtCase);
        return "redirect:/cases";
    }
}