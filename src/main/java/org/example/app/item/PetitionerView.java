package org.example.app.item;

public record PetitionerView(
        Integer petitioner_id,
        String petitioner_name,
        String petitioner_type,
        String passport_id,
        String registration_number
) {}