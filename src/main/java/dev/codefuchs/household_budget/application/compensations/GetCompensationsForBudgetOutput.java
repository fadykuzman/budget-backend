package dev.codefuchs.household_budget.application.compensations;

import java.util.List;

public record GetCompensationsForBudgetOutput(
        List<CompensationOutput> compensations,
        int totalAmount
) {
}
