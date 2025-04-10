package dev.codefuchs.household_budget.budget_category;

import java.util.List;

public record BudgetCategoriesResponse(
        List<BudgetCategory> categories
) {
}
