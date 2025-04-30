package dev.codefuchs.household_budget.application.compensations;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateCompensationDateInput(
        UUID id,
        LocalDate date
) {
}
