package dev.codefuchs.household_budget.budget.list_budgets;

import dev.codefuchs.household_budget.budget.BudgetDTO;

import java.util.List;

public record BudgetsResponse(
        List<BudgetDTO> budgets
) {
}
