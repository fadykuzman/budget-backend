package dev.codefuchs.household_budget.application.budget_categories;

import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;

import java.time.YearMonth;

public record BudgetCategoryAddedEvent(
        BudgetCategory budgetCategory,
        YearMonth startDate,
        YearMonth endDate
) {

}
