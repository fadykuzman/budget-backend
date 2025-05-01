package dev.codefuchs.household_budget.domain.budget_categories;

import dev.codefuchs.household_budget.domain.budget.Budget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "budget_categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BudgetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private int amount;
    @Enumerated(STRING)
    private BudgetCadence cadence;
    private int startYear;
    private int startMonth;
    private int endYear;
    private int endMonth;
    @OneToMany(mappedBy = "budgetCategory", cascade = CascadeType.ALL)
    private List<Budget> budgets;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public void changeName(String name) {
        this.name = name;
    }
}
