package dev.codefuchs.household_budget.budget_category;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetCategoryService {

    public List<BudgetCategory> budgetCategories = new ArrayList<>();

    public List<BudgetCategory> add(String categoryName) {
        var lowerCaseName = categoryName.toLowerCase();
        var category = new BudgetCategory(lowerCaseName);
        if (budgetCategories.contains(category)) {
            throw new BudgetCategoryAlreadyExists(String.format("Budget Category '%s' already exists", lowerCaseName));
        }
        budgetCategories.add(category);
        return budgetCategories;
    }
}
