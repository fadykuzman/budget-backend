package dev.codefuchs.household_budget.application.expenses;

import java.util.List;

public record PurposeOutput(
        String purpose,
        int totalAmount,
        List<ExpenseEntryOutput> expenseEntries
) {
}
