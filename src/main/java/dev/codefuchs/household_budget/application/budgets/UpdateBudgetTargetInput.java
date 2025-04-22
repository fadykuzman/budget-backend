package dev.codefuchs.household_budget.application.budgets;

import java.util.UUID;

public record UpdateBudgetTargetInput(
        UUID budgetId,
        int target
) {
}
