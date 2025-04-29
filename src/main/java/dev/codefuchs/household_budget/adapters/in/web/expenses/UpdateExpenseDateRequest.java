package dev.codefuchs.household_budget.adapters.in.web.expenses;

import dev.codefuchs.household_budget.application.expenses.UpdateExpenseDateInput;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record UpdateExpenseDateRequest(
        String id,
        String date
) {
    public UpdateExpenseDateInput toInput() {
        return new UpdateExpenseDateInput(
                UUID.fromString(id),
                Instant.parse(date).atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }
}
