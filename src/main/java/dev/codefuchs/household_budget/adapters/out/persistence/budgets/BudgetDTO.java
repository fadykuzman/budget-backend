package dev.codefuchs.household_budget.adapters.out.persistence.budgets;

public record BudgetDTO(
        String id,
        String category,
        int amount
) {
}
