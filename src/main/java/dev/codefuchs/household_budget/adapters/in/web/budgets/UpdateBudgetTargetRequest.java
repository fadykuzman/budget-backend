package dev.codefuchs.household_budget.adapters.in.web.budgets;

import dev.codefuchs.household_budget.application.budgets.UpdateBudgetTargetInput;

import java.util.UUID;

public record UpdateBudgetTargetRequest(
        String budgetId,
        int target
) {
    public UpdateBudgetTargetInput toInput() {
        return new UpdateBudgetTargetInput(
                UUID.fromString(budgetId),
                target
        );
    }
}
