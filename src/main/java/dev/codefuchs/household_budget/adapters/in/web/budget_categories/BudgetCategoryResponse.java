package dev.codefuchs.household_budget.adapters.in.web.budget_categories;

import dev.codefuchs.household_budget.application.budget_categories.BudgetCategoryOutput;
import dev.codefuchs.household_budget.domain.budget_categories.BudgetCadence;

public record BudgetCategoryResponse(
        String id,
        String name,
        BudgetCadence cadence,
        int amount,
        String startDate,
        String endDate
) {
    public BudgetCategoryResponse(BudgetCategoryOutput o) {
        this(
                o.id().toString(),
                o.name(),
                o.cadence(),
                o.amount(),
                o.startDate().toString(),
                o.endDate() == null ? null : o.endDate().toString()
        );
    }
}
