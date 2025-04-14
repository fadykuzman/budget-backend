package dev.codefuchs.household_budget.adapters.out.persistence.budgets;

import dev.codefuchs.household_budget.domain.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BudgetsRepository extends JpaRepository<Budget, UUID> {
    List<Budget> findAllByYearAndMonth(int year, int month);
}
