package dev.codefuchs.household_budget.adapters.out.persistence.compensations;

import dev.codefuchs.household_budget.domain.compensations.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CompensationsRepository extends JpaRepository<Compensation, UUID> {
    List<Compensation> findAllByBudgetId(UUID budgetId);

    @Query("SELECT COALESCE(SUM(c.amount), 0) FROM Compensation c WHERE c.budget.id = :budgetId")
    int findTotalCompensationById(@Param("budgetId")UUID id);
}
