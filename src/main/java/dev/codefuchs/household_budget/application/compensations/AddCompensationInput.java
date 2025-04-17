package dev.codefuchs.household_budget.application.compensations;

import dev.codefuchs.household_budget.domain.budget.Budget;
import dev.codefuchs.household_budget.domain.compensations.Compensation;

import java.time.LocalDate;
import java.util.UUID;

public record AddCompensationInput(
        String source,
        int amount,
        LocalDate date,
        UUID budgetId
) {
    public Compensation toCompensation(Budget budget) {
        return Compensation.builder()
                .source(source)
                .amount(amount)
                .date(date)
                .budget(budget)
                .build();
    }
}
