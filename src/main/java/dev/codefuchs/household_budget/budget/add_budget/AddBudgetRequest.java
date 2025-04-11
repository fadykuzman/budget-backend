package dev.codefuchs.household_budget.budget.add_budget;

public record AddBudgetRequest(
        String category,
        int amount,
        String startDate,
        String endDate
) {
}
