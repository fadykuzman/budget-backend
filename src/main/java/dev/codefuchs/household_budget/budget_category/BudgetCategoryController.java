package dev.codefuchs.household_budget.budget_category;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("budget/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class BudgetCategoryController {
    @GetMapping
    public BudgetCategoriesResponse getAll() {
        return new BudgetCategoriesResponse(List.of(
                new BudgetCategory("Groceries")
        ));
    }
}
