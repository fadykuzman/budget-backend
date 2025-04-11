package dev.codefuchs.household_budget.budget;

import java.util.List;

public record BudgetsResponse(
        List<Budget> budgets
) {
}
