package org.example.app.item;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CourtCaseRepository extends CrudRepository<CourtCase, Integer> {

    @Query("SELECT c.CaseNumber AS case_number, c.CaseTitle AS case_title, " +
        "c.FilingDate AS filing_date, c.Status AS status, " +
        "p.PetitionerName AS petitioner_name " +
        "FROM CourtCase c JOIN Petitioner p ON c.PetitionerID = p.PetitionerID")
    List<CaseReportDTO> getCaseRegistrationReport();
}