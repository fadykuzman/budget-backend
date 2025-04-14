package dev.codefuchs.household_budget.application.budget_categories;

import dev.codefuchs.household_budget.domain.budget_categories.BudgetCadence;

import java.time.YearMonth;

public record AddBudgetCategoryInput(
        String name,
        int amount,
        BudgetCadence cadence,
        YearMonth startDate,
        YearMonth endDate
) {
}
