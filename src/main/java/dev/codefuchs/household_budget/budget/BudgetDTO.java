package dev.codefuchs.household_budget.budget;

import java.time.YearMonth;
import java.util.UUID;

public record BudgetDTO(
        String id,
        String category,
        int amount
) {
}
