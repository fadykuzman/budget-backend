package dev.codefuchs.household_budget.application.compensations;

import java.util.UUID;

public class CompensationNotFoundException extends IllegalArgumentException {
    public CompensationNotFoundException(UUID uuid) {
        super("Compensation with id " + uuid + " not found");
    }
}
