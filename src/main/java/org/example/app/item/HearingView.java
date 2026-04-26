package org.example.app.item;

public record HearingView(
        Integer hearingId,
        String hearingDate,
        String location,
        String caseTitle,
        String judgeName,
        String petitionerName
) {}