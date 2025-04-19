package dev.codefuchs.household_budget.adapters.in.web.budgets;

import dev.codefuchs.household_budget.application.budgets.GetBudgetSummaryOutput;

public record BudgetSummaryResponse(
        int target,
        int compensations,
        int expenses,
        int balance
) {
    public BudgetSummaryResponse(GetBudgetSummaryOutput output) {
        this(
                output.target(),
                output.compensation(),
                output.expenses(),
                output.balance()
        );
    }
}
