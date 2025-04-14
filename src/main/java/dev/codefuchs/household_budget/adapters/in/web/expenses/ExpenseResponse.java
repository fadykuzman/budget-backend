package dev.codefuchs.household_budget.adapters.in.web.expenses;

public record ExpenseResponse(
        String id,
        String purpose,
        int amount
) {
}
