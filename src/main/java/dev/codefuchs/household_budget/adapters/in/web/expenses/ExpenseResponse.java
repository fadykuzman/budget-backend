package dev.codefuchs.household_budget.adapters.in.web.expenses;

import java.time.LocalDate;

public record ExpenseResponse(
        String purpose,
        int amount,
        String date
) {
}
