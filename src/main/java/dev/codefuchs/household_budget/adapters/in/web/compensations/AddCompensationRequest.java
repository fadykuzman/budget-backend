package dev.codefuchs.household_budget.adapters.in.web.compensations;

import dev.codefuchs.household_budget.application.compensations.AddCompensationInput;

import java.time.LocalDate;
import java.util.UUID;

public record AddCompensationRequest(
        String source,
        int amount,
        LocalDate date,
        String budgetId
) {
    public AddCompensationInput toInput() {
        return new AddCompensationInput(source, amount, date, UUID.fromString(budgetId));
    }
}
