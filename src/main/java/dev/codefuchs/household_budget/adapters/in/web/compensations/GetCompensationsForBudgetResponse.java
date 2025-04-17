package dev.codefuchs.household_budget.adapters.in.web.compensations;

import dev.codefuchs.household_budget.application.compensations.GetCompensationsForBudgetOutput;

import java.util.List;

public record GetCompensationsForBudgetResponse(
        List<CompensationResponse> compensations,
        int totalAmount
) {
    GetCompensationsForBudgetResponse(GetCompensationsForBudgetOutput output) {
        this (
                output.compensations()
                        .stream()
                .map(o -> new CompensationResponse(
                        o.id().toString(),
                        o.source(),
                        o.amount(),
                        o.date().getDayOfMonth())
                ).toList(),
        output.totalAmount());
    }
}
