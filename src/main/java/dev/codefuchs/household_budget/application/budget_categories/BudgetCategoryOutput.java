package dev.codefuchs.household_budget.application.budget_categories;

import dev.codefuchs.household_budget.domain.budget_categories.BudgetCadence;
import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public record BudgetCategoryOutput(
        UUID id,
        String name,
        BudgetCadence cadence,
        int amount,
        YearMonth startDate,
        YearMonth endDate
) {
    public BudgetCategoryOutput(BudgetCategory entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getCadence(),
                entity.getAmount(),
                YearMonth.of(entity.getStartYear(), entity.getStartMonth()),
                entity.getEndMonth() == 0 ? null : YearMonth.of(entity.getEndYear(), entity.getEndMonth())
        );
    }
}
