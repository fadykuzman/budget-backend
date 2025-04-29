package dev.codefuchs.household_budget.adapters.in.web.compensations;

import dev.codefuchs.household_budget.application.compensations.CompensationOutput;
import dev.codefuchs.household_budget.application.compensations.GetCompensationsForBudgetOutput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                        o.date().toString())
                ).toList(),
        output.totalAmount());
    }

    private static String formatDate(LocalDate date) {
        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }
}
