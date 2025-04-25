package dev.codefuchs.household_budget.adapters.in.web.expenses;

import dev.codefuchs.household_budget.application.expenses.UpdateExpenseInput;

import java.util.UUID;

public record UpdateExpenseRequest(
        String id,
        int amount,
        String currency
) {
    public UpdateExpenseInput toInput() {
        return new UpdateExpenseInput(
                UUID.fromString(id),
                amount,
                currency
        );
    }
}
