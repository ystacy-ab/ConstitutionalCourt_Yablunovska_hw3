package org.example.app.item;

import java.time.LocalDate;

public record HearingReportDTO(
        Integer hearingId,
        LocalDate hearingDate,
        String location,
        String caseTitle,
        String judgeName,
        String petitionerName
) {}