package dev.codefuchs.household_budget.domain.expenses;

import dev.codefuchs.household_budget.domain.budget.Budget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@Entity
@Table(name = "expenses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Expense {
    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;

    private String purpose;
    private int amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    private Budget budget;
}
