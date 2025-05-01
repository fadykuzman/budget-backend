package dev.codefuchs.household_budget.adapters.out.persistence.budget_categories;

import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BudgetCategoriesRepository extends JpaRepository<BudgetCategory, UUID> {
    @Query("""
            SELECT bc
            FROM BudgetCategory bc
            WHERE bc.endMonth = 0
            OR ( bc.endMonth >= :endMonth
                AND bc.endYear >= :endYear
            )
            """)
    List<BudgetCategory> findActiveOrEndingOnOrAfter(@Param("endYear") int endYear, @Param("endMonth") int endMonth);
}
