package org.example.app.item;
import java.time.LocalDate;

public class CaseReportDTO {
    private Integer caseNumber;
    private String caseTitle;
    private LocalDate filingDate;
    private String status;
    private String petitionerName;
    public CaseReportDTO(Integer caseNumber, String caseTitle, LocalDate filingDate,
                         String status, String petitionerName) {
        this.caseNumber = caseNumber;
        this.caseTitle = caseTitle;
        this.filingDate = filingDate;
        this.status = status;
        this.petitionerName = petitionerName;
    }
    public Integer getCaseNumber() { return caseNumber; }
    public void setCaseNumber(Integer caseNumber) { this.caseNumber = caseNumber; }
    public String getCaseTitle() { return caseTitle; }
    public void setCaseTitle(String caseTitle) { this.caseTitle = caseTitle; }
    public LocalDate getFilingDate() { return filingDate; }
    public void setFilingDate(LocalDate filingDate) { this.filingDate = filingDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPetitionerName() { return petitionerName; }
    public void setPetitionerName(String petitionerName) { this.petitionerName =
            petitionerName;
    }
}