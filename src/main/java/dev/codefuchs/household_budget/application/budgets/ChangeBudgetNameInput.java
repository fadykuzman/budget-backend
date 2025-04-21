package dev.codefuchs.household_budget.application.budgets;

import java.util.UUID;

public record ChangeBudgetNameInput(
        UUID budgetId,
        String name
) {
}
