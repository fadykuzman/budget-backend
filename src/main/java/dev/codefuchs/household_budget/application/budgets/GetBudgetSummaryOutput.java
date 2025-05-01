package dev.codefuchs.household_budget.application.budgets;

public record GetBudgetSummaryOutput(
        int target,
        int compensation,
        int expenses,
        int balance
) {
    public static GetBudgetSummaryOutput empty() {
        return new GetBudgetSummaryOutput(0, 0, 0, 0);
    }
}
