package dev.codefuchs.household_budget.application.expenses;

import java.util.UUID;

public record ExpenseEntryOutput(
        UUID id,
        int dayOfMonth,
        int amount
) {
}
