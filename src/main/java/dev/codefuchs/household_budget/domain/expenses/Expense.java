package dev.codefuchs.household_budget.domain.expenses;

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
@EntityListeners(AuditingEntityListener.class)
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

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public void update(int amount) {
        this.amount = amount;
    }

    public void updateDate(LocalDate date) {
        this.date = date;
    }
}
