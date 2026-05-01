package org.example.app.item;

public record RulingView(
        Integer ruling_id,
        String verdict_date,
        String verdict_text,
        String case_title,
        String status
) {}