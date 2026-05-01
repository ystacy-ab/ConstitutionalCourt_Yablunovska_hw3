package org.example.app.item;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface HearingRepository extends CrudRepository<Hearing, Integer> {

    @Query("""
        SELECT h.HearingID        AS hearing_id,
               h.HearingDate      AS hearing_date,
               h.Location         AS location,
               c.CaseTitle        AS case_title,
               CONCAT(j.FirstName, ' ', j.LastName) AS judge_name,
               p.PetitionerName   AS petitioner_name
        FROM Hearing h
        JOIN CourtCase c  ON h.CaseNumber         = c.CaseNumber
        JOIN Attends a    ON h.HearingID           = a.Hearing_HearingID
        JOIN Judge j      ON a.Judge_JudgeID       = j.JudgeID
        JOIN Petitioner p ON a.PetitionerID        = p.PetitionerID
        """)
    List<HearingView> findAllWithDetails();

    @Query("SELECT h.HearingID AS hearing_id, " +
            "h.HearingDate AS hearing_date, " +
            "h.Location AS location, c.CaseTitle AS case_title, " +
            "CONCAT(j.FirstName, ' ', j.LastName) AS judge_name, " +
            "p.PetitionerName AS petitioner_name " +
            "FROM Hearing h " +
            "JOIN CourtCase c ON h.CaseNumber = c.CaseNumber " +
            "LEFT JOIN Attends a ON h.HearingID = a.Hearing_HearingID " +
            "LEFT JOIN Judge j ON a.Judge_JudgeID = j.JudgeID " +
            "LEFT JOIN Petitioner p ON a.PetitionerID = p.PetitionerID")
    List<HearingReportDTO> getHearingNoticeReport();

    @Modifying
    @Query("""
        INSERT INTO Attends (Judge_JudgeID, Hearing_HearingID, PetitionerID)
        VALUES (:judgeId, :hearingId, :petitionerId)
        """)
    void insertAttends(@Param("hearingId") Integer hearingId,
                       @Param("judgeId") Integer judgeId,
                       @Param("petitionerId") Integer petitionerId);
}