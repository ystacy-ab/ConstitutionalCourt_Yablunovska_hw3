package org.example.app.item;

public record PetitionerReportDTO(
        Integer petitionerId,
        String petitionerName,
        String petitionerType,
        String passportId,
        String registrationNumber
) {}