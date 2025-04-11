package dev.codefuchs.household_budget.budget.add_budget;

import java.time.YearMonth;

public record AddBudgetInput(
        String category,
        int amount,
        YearMonth startDate,
        YearMonth endDate
) {
}
