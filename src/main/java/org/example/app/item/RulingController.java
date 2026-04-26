package org.example.app.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rulings")
public class RulingController {

    private final RulingRepository rulingRepository;
    private final CourtCaseRepository courtCaseRepository;

    public RulingController(RulingRepository rulingRepository,
                            CourtCaseRepository courtCaseRepository) {
        this.rulingRepository = rulingRepository;
        this.courtCaseRepository = courtCaseRepository;
    }

    @GetMapping
    public String listRulings(Model model) {
        model.addAttribute("rulings", rulingRepository.findAllWithDetails());
        return "rulings";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("ruling", new Ruling());
        model.addAttribute("cases", courtCaseRepository.findAll());
        return "add-ruling";
    }

    @PostMapping
    public String saveRuling(@ModelAttribute Ruling ruling) {
        rulingRepository.save(ruling);
        rulingRepository.closeCaseByCaseNumber(ruling.getCaseNumber());
        return "redirect:/rulings";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Ruling ruling = rulingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ruling id: " + id));
        model.addAttribute("ruling", ruling);
        model.addAttribute("cases", courtCaseRepository.findAll());
        return "edit-ruling";
    }

    @PostMapping("/update")
    public String updateRuling(@ModelAttribute Ruling ruling) {
        ruling.markAsExisting();
        rulingRepository.save(ruling);
        return "redirect:/rulings";
    }

    @PostMapping("/delete/{id}")
    public String deleteRuling(@PathVariable Integer id) {
        rulingRepository.deleteById(id);
        return "redirect:/rulings";
    }
}