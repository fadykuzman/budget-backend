package dev.codefuchs.household_budget.budget;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.Date;
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
    private String category;
    private int amount;
    private int startMonth;
    private int startYear;
    private int endMonth;
    private int endYear;
}
