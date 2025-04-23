package dev.codefuchs.household_budget.adapters.in.web.expenses;

import dev.codefuchs.household_budget.application.expenses.ExpenseEntryOutput;

import java.time.LocalDate;

public record ExpenseEntryResponse(
        String id,
        int dayOfMonth,
        int amount
) {
    public static ExpenseEntryResponse fromInput(ExpenseEntryOutput o) {
        return new ExpenseEntryResponse(o.id().toString(), o.dayOfMonth(), o.amount());
    }
}
