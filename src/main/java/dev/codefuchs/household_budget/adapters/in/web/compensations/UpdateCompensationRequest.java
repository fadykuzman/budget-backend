package dev.codefuchs.household_budget.adapters.in.web.compensations;

import dev.codefuchs.household_budget.application.compensations.UpdateCompensationInput;

import java.util.UUID;

public record UpdateCompensationRequest(
        String id,
        int amount,
        String currency
) {
    public UpdateCompensationInput toInput() {
        return new UpdateCompensationInput(
                UUID.fromString(id),
                amount,
                currency
        );
    }
}
