package dev.codefuchs.household_budget.application.budgets;

import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;

import java.time.YearMonth;

public record CreateBudgetsInput(
        BudgetCategory budgetCategory,
        YearMonth startDate,
        YearMonth endDate
) {
}
