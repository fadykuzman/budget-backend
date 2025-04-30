package dev.codefuchs.household_budget.adapters.in.web.compensations;

import dev.codefuchs.household_budget.application.compensations.UpdateCompensationDateInput;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

public record UpdateCompensationDateRequest(
        String id,
        String date
) {
    public UpdateCompensationDateInput toInput() {
        return new UpdateCompensationDateInput(
                UUID.fromString(id),
                Instant.parse(date).atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }
}
