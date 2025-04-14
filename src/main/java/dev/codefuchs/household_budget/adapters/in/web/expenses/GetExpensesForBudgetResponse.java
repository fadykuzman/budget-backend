package dev.codefuchs.household_budget.adapters.in.web.expenses;

import dev.codefuchs.household_budget.application.expenses.GetExpensesOutput;

import java.util.List;

public record GetExpensesForBudgetResponse(
        List<ExpenseResponse> expenses,
        int totalAmount
) {
    GetExpensesForBudgetResponse(GetExpensesOutput output) {
        this(
                output.expenses()
                        .stream()
                        .map(o ->
                                new ExpenseResponse(o.id().toString(), o.purpose(), o.amount())
                ).toList(),
                output.totalAmount()
        );
    }
}
