package dev.codefuchs.household_budget.adapters.in.web.expenses;

import dev.codefuchs.household_budget.application.expenses.ExpenseEntryOutput;
import dev.codefuchs.household_budget.application.expenses.PurposeOutput;

import java.util.List;

public record ExpensesResponse(
        String purpose,
        int totalAmount,
        List<ExpenseEntryResponse> expenseEntries
) {

    public static ExpensesResponse fromInput(PurposeOutput o) {
        return new ExpensesResponse(
                o.purpose(),
                o.totalAmount(),
                o.expenseEntries().stream().map(e -> ExpenseEntryResponse.fromInput(e)).toList()
        );
    }
}
