package dev.codefuchs.household_budget.adapters.out.persistence.expenses;

import dev.codefuchs.household_budget.domain.expenses.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ExpensesRepository extends JpaRepository<Expense, UUID> {
    List<Expense> findAllByBudgetId(UUID budgetId);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.budget.id = :budgetId")
    int sumAmountByBudgetId(@Param("budgetId") UUID budgetId);

    @Query("""
            SELECT COALESCE(SUM(e.amount), 0)
            FROM Expense e
            WHERE e.budget.id in (:budgetIds)
            """)
    Integer sumAmountByBudgetIds(@Param("budgetIds") List<UUID> budgetIds);
}
