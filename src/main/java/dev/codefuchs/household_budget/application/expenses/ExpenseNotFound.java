package dev.codefuchs.household_budget.application.expenses;

import java.util.UUID;

public class ExpenseNotFound extends IllegalArgumentException {
    public ExpenseNotFound(UUID id) {
        super("Expense with id " + id + " not found");
    }
}
