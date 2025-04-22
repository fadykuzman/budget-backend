package dev.codefuchs.household_budget.application.expenses;

import dev.codefuchs.household_budget.domain.budget.Budget;
import dev.codefuchs.household_budget.domain.expenses.Expense;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record AddExpenseInput(
        UUID budgetId,
        String purpose,
        int amount,
        LocalDate date
) {
    public Expense toExpense(Budget budget) {
        return Expense.builder()
                .purpose(purpose.toLowerCase().trim())
                .amount(amount)
                .date(date)
                .budget(budget)
                .build();
    }
}
