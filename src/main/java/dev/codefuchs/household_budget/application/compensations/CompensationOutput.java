package dev.codefuchs.household_budget.application.compensations;

import java.time.LocalDate;
import java.util.UUID;

public record CompensationOutput(
        UUID id,
        String source,
        int amount,
        LocalDate date
) {
}
