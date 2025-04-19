package dev.codefuchs.household_budget.application.budget_categories;

import java.util.List;

public record GetBudgetCategoriesOutput(
        List<BudgetCategoryOutput> categories
) {
}
