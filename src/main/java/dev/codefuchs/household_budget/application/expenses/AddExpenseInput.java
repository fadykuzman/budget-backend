package dev.codefuchs.household_budget.application.expenses;

import dev.codefuchs.household_budget.domain.budget.Budget;
import dev.codefuchs.household_budget.domain.expenses.Expense;

import java.util.UUID;

public record AddExpenseInput(
        UUID budgetId,
        String purpose,
        int amount
) {
    public Expense toExpense(Budget budget) {
        return Expense.builder()
                .purpose(purpose)
                .amount(amount)
                .budget(budget)
                .build();
    }
}
