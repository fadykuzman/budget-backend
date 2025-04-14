package dev.codefuchs.household_budget.adapters.out.persistence.budget_categories;

import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BudgetCategoriesRepositery extends JpaRepository<BudgetCategory, UUID> {
}
