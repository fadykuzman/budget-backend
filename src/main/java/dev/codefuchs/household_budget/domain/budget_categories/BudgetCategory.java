package dev.codefuchs.household_budget.domain.budget_categories;

import dev.codefuchs.household_budget.domain.budget.Budget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "budget_categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

}
