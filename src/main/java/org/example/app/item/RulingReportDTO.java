package org.example.app.item;

import java.time.LocalDate;

public record RulingReportDTO(
        Integer rulingId,
        LocalDate verdictDate,
        String caseTitle,
        String status,
        String verdictText
) {}