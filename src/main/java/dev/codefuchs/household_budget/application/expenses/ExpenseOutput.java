package dev.codefuchs.household_budget.application.expenses;

import java.util.UUID;

public record ExpenseOutput(
        UUID id,
        String purpose,
        int amount
) {
}
