package dev.codefuchs.household_budget.adapters.in.web.compensations;

public record CompensationResponse(
        String id,
        String source,
        int amount,
        String date
) {
}
