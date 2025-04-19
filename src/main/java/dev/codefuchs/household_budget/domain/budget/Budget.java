package dev.codefuchs.household_budget.domain.budget;

import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;
import dev.codefuchs.household_budget.domain.compensations.Compensation;
import dev.codefuchs.household_budget.domain.expenses.Expense;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "budgets")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
