package org.example.app.item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Controller
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private CourtCaseRepository courtCaseRepository;
    @Autowired
    private PetitionerRepository PetitionerRepository;
    @Autowired
    private RulingRepository RulingRepository;
    @Autowired
    private HearingRepository HearingRepository;

    @GetMapping("/case-registration")
    public String getCaseRegistrationReport(Model model) {
        List<CaseReportDTO> reportData = courtCaseRepository.getCaseRegistrationReport();
        model.addAttribute("reportTitle", "Case Registration Report");

        model.addAttribute("cases", reportData);
        return "case-report";
    }
    @GetMapping("/hearings")
    public String getHearingReport(Model model) {
        model.addAttribute("reportTitle", "Hearing Notice Report");
        model.addAttribute("hearings", HearingRepository.getHearingNoticeReport());
        return "hearing-report";
    }

    @GetMapping("/rulings")
    public String getRulingReport(Model model) {
        model.addAttribute("reportTitle", "Court Ruling Report");
        model.addAttribute("rulings", RulingRepository.getCourtRulingReport());
        return "ruling-report";
    }

    @GetMapping("/petitioners")
    public String getPetitionersReport(Model model) {
        model.addAttribute("reportTitle", "Official Petitioners Registry");
        model.addAttribute("petitioners", PetitionerRepository.getPetitionersReport());
        return "petitioners-report";
    }
}