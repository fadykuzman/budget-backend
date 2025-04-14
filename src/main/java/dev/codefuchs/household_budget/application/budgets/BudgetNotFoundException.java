package dev.codefuchs.household_budget.application.budgets;

import java.util.UUID;

public class BudgetNotFoundException extends IllegalArgumentException {
    public BudgetNotFoundException(UUID uuid) {
        super("Budget with id " + uuid + " not found");
    }
}
