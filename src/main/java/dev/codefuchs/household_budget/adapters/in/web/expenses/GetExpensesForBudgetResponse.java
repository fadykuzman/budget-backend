package dev.codefuchs.household_budget.adapters.in.web.expenses;

import dev.codefuchs.household_budget.application.expenses.GetExpensesOutput;

import java.util.List;

public record GetExpensesForBudgetResponse(
        List<ExpensesResponse> expenses,
        int totalAmount
) {
    GetExpensesForBudgetResponse(GetExpensesOutput output) {
        this(
            output.expenses().stream().map(ExpensesResponse::fromInput).toList(),
                output.totalAmount()
        );
    }
}
