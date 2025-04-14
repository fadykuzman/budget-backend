package dev.codefuchs.household_budget.adapters.out.persistence.expenses;

import dev.codefuchs.household_budget.domain.expenses.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExpensesRepository extends JpaRepository<Expense, UUID> {
    List<Expense> findAllByBudgetId(UUID budgetId);
}
