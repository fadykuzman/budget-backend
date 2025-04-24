package dev.codefuchs.household_budget.application.compensations;

import java.util.UUID;

public record UpdateCompensationInput(
        UUID id,
        int amount,
        String currency
) {
}
