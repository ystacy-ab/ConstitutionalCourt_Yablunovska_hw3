package org.example.app.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hearings")
public class HearingController {

    private final HearingRepository hearingRepository;
    private final CourtCaseRepository courtCaseRepository;
    private final JudgeRepository judgeRepository;
    private final PetitionerRepository petitionerRepository;

    public HearingController(HearingRepository hearingRepository,
                             CourtCaseRepository courtCaseRepository,
                             JudgeRepository judgeRepository,
                             PetitionerRepository petitionerRepository) {
        this.hearingRepository = hearingRepository;
        this.courtCaseRepository = courtCaseRepository;
        this.judgeRepository = judgeRepository;
        this.petitionerRepository = petitionerRepository;
    }

    @GetMapping
    public String listHearings(Model model) {
        model.addAttribute("hearings", hearingRepository.findAllWithDetails());
        return "hearings";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("hearing", new Hearing());
        model.addAttribute("cases", courtCaseRepository.findAll());
        model.addAttribute("judges", judgeRepository.findAll());
        model.addAttribute("petitioners", petitionerRepository.findAll());
        return "add-hearing";
    }

    @PostMapping
    public String saveHearing(@ModelAttribute Hearing hearing,
                              @RequestParam Integer judgeId,
                              @RequestParam Integer petitionerId) {
        hearingRepository.save(hearing);
        hearingRepository.insertAttends(hearing.getHearingId(), judgeId, petitionerId);
        return "redirect:/hearings";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Hearing hearing = hearingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hearing id: " + id));
        model.addAttribute("hearing", hearing);
        model.addAttribute("cases", courtCaseRepository.findAll());
        return "edit-hearing";
    }

    @PostMapping("/update")
    public String updateHearing(@ModelAttribute Hearing hearing) {
        hearing.markAsExisting();
        hearingRepository.save(hearing);
        return "redirect:/hearings";
    }

    @PostMapping("/delete/{id}")
    public String deleteHearing(@PathVariable Integer id) {
        hearingRepository.deleteById(id); // cascade deletes Attends
        return "redirect:/hearings";
    }
}