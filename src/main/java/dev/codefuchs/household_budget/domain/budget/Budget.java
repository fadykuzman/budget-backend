package dev.codefuchs.household_budget.domain.budget;

import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;
import dev.codefuchs.household_budget.domain.compensations.Compensation;
import dev.codefuchs.household_budget.domain.expenses.Expense;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "budgets")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int amount;
    private int year;
    private int month;
    @ManyToOne
    @JoinColumn(name = "budget_category_id", nullable = false)
    private BudgetCategory budgetCategory;

    @OneToMany(mappedBy = "budget", cascade = ALL)
    private Set<Expense> expenses;
    @OneToMany(mappedBy = "budget", cascade = ALL)
    private Set<Compensation> compensations;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public void updateTarget(int target) {
        this.amount = target;
    }
}
