package dev.codefuchs.household_budget.application.expenses;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseDateInput(
        UUID id,
        LocalDate date
) {
}
