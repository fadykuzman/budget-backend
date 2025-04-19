package dev.codefuchs.household_budget.adapters.in.web.budget_categories;

import dev.codefuchs.household_budget.application.budget_categories.GetBudgetCategoriesOutput;

import java.util.List;

public record GetBudgetCategoriesResponse(
        List<BudgetCategoryResponse> categories
) {

    public GetBudgetCategoriesResponse(GetBudgetCategoriesOutput output) {
        this(
                output.categories().stream()
                        .map(BudgetCategoryResponse::new)
                        .toList()
        );
    }
}
