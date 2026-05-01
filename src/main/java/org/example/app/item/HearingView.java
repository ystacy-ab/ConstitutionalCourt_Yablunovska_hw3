package org.example.app.item;

public record HearingView(
        Integer hearing_id,
        String hearing_date,
        String location,
        String case_title,
        String judge_name,
        String petitioner_name
) {}