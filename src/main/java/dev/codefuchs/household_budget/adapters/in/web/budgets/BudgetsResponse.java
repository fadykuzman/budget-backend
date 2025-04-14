package dev.codefuchs.household_budget.adapters.in.web.budgets;

import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetDTO;

import java.util.List;

public record BudgetsResponse(
        List<BudgetDTO> budgets
) {
}
