package dev.codefuchs.household_budget.adapters.in.web.budget_categories;

import dev.codefuchs.household_budget.domain.budget_categories.BudgetCadence;

import java.time.YearMonth;

public record AddBudgetCategoryRequest(
        String name,
        int amount,
        BudgetCadence cadence,
        YearMonth startDate,
        YearMonth endDate
) {
}
