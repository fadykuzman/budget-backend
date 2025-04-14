package dev.codefuchs.household_budget.adapters.in.web.budget_categories;

import dev.codefuchs.household_budget.application.budget_categories.BudgetCategoriesService;
import dev.codefuchs.household_budget.application.budget_categories.AddBudgetCategoryInput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/budgets/categories")
@RequiredArgsConstructor
public class BudgetCategoriesController {
    private final BudgetCategoriesService service;

    @PostMapping
    public void add(@RequestBody AddBudgetCategoryRequest request) {
        var input = new AddBudgetCategoryInput(
                request.name(), request.amount(), request.cadence(), request.startDate(), request.endDate());
        service.add(input);
    }
}
