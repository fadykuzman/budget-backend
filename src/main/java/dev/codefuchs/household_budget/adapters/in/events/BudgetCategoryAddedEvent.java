package dev.codefuchs.household_budget.adapters.in.events;

import dev.codefuchs.household_budget.application.budgets.CreateBudgetsInput;
import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;

import java.time.YearMonth;

public record BudgetCategoryAddedEvent(
        BudgetCategory budgetCategory,
        YearMonth startDate,
        YearMonth endDate
) {

    public CreateBudgetsInput toInput() {
        return new CreateBudgetsInput(
                budgetCategory, startDate, endDate
        );
    }
}
