package dev.codefuchs.household_budget.application.expenses;

import java.util.List;

public record GetExpensesOutput(
        int totalAmount,
        List<PurposeOutput> expenses

) {
}
