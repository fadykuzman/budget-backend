package dev.codefuchs.household_budget.budget;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BudgetsRepository extends JpaRepository<Budget, UUID> {
}
