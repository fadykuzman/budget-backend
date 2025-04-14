package dev.codefuchs.household_budget.application.budgets;

import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetDTO;

import java.util.List;

public record GetBudgetsOutput(
        List<BudgetDTO> budgets
) {
}
