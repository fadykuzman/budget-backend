package dev.codefuchs.household_budget.adapters.in.web.budgets;

import dev.codefuchs.household_budget.application.budgets.ChangeBudgetNameInput;

import java.util.UUID;

public record ChangeBudgetNameRequest(
        String budgetId,
        String name
) {
    public ChangeBudgetNameInput toInput() {
        return new ChangeBudgetNameInput(
                UUID.fromString(budgetId),
                name
        );
    }
}
