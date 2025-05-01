package dev.codefuchs.household_budget.adapters.out.persistence.budgets;

import dev.codefuchs.household_budget.domain.budget.Budget;

public record BudgetDTO(
        String id,
        String category,
        int amount
) {
    public BudgetDTO(Budget budget) {
        this(budget.getId().toString(), budget.getBudgetCategory().getName(), budget.getAmount());
    }
}
