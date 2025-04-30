package dev.codefuchs.household_budget.domain.compensations;

import dev.codefuchs.household_budget.domain.budget.Budget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "compensations")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String source;
    private int amount;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    private Budget budget;

    public void update(int amount) {
        this.amount = amount;
    }

    public void updateDate(LocalDate date) {
        this.date = date;
    }
}
