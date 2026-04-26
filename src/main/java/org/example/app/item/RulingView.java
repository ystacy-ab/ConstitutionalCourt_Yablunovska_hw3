package org.example.app.item;

public record RulingView(
        Integer rulingId,
        String verdictDate,
        String verdictText,
        String caseTitle,
        String status
) {}