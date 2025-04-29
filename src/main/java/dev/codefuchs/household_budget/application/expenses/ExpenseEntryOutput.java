package dev.codefuchs.household_budget.application.expenses;

import java.util.UUID;

public record ExpenseEntryOutput(
        UUID id,
        String date,
        int amount
) {
}
