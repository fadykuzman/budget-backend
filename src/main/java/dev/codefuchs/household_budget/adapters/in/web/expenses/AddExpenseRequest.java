package dev.codefuchs.household_budget.adapters.in.web.expenses;

import dev.codefuchs.household_budget.application.expenses.AddExpenseInput;

import java.util.UUID;

public record AddExpenseRequest(
        String budgetId,
        String purpose,
        int amount
) {
    AddExpenseInput toInput() {
        return new AddExpenseInput(UUID.fromString(budgetId), purpose, amount);
    }
}
