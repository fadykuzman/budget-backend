package dev.codefuchs.household_budget.application.expenses;

import java.util.UUID;

public record UpdateExpenseInput(
        UUID id,
        int amount,
        String currency
) {
}
