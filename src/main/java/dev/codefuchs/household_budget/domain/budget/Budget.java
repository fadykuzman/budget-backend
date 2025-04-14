package dev.codefuchs.household_budget.domain.budget;

import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

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

}
