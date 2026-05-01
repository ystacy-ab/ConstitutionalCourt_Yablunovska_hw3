package org.example.app.item;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RulingRepository extends CrudRepository<Ruling, Integer> {

    @Query("""
        SELECT r.RulingID     AS ruling_id,
               r.VerdictDate  AS verdict_date,
               r.VerdictText  AS verdict_text,
               c.CaseTitle    AS case_title,
               c.Status       AS status
        FROM Ruling r
        JOIN CourtCase c ON r.CaseNumber = c.CaseNumber
        """)
    List<RulingView> findAllWithDetails();

    @Query("""
    SELECT r.RulingID AS ruling_id,
           r.VerdictDate AS verdict_date,
           c.CaseTitle AS case_title,
           c.Status AS status,
           r.VerdictText AS verdict_text
    FROM Ruling r JOIN CourtCase c ON r.CaseNumber = c.CaseNumber
    """)
    List<RulingReportDTO> getCourtRulingReport();

    @Modifying
    @Query("UPDATE CourtCase SET Status = 'CLOSED' WHERE CaseNumber = :caseNumber")
    void closeCaseByCaseNumber(@Param("caseNumber") Integer caseNumber);
}