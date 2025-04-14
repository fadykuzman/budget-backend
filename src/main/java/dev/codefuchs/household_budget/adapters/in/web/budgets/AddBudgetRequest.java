package dev.codefuchs.household_budget.adapters.in.web.budgets;

public record AddBudgetRequest(
        String category,
        int amount,
        String startDate,
        String endDate
) {
}
