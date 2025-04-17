package dev.codefuchs.household_budget.adapters.out.persistence.compensations;

import dev.codefuchs.household_budget.domain.compensations.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompensationsRepository extends JpaRepository<Compensation, UUID> {
}
