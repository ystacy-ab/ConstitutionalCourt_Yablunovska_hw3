package org.example.app.item;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RulingRepository extends CrudRepository<Ruling, Integer> {

    @Query("""
        SELECT r.RulingID     AS rulingId,
               r.VerdictDate  AS verdictDate,
               r.VerdictText  AS verdictText,
               c.CaseTitle    AS caseTitle,
               c.Status       AS status
        FROM Ruling r
        JOIN CourtCase c ON r.CaseNumber = c.CaseNumber
        """)
    List<RulingView> findAllWithDetails();

    @Modifying
    @Query("UPDATE CourtCase SET Status = 'CLOSED' WHERE CaseNumber = :caseNumber")
    void closeCaseByCaseNumber(@Param("caseNumber") Integer caseNumber);
}