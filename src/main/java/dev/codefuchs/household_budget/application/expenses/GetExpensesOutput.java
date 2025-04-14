package dev.codefuchs.household_budget.application.expenses;

import java.util.List;
import java.util.UUID;

public record GetExpensesOutput(
        List<ExpenseOutput> expenses,
        int totalAmount
) {
}
