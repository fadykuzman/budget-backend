package dev.codefuchs.household_budget.budget_category.expenses;

import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.adapters.out.persistence.expenses.ExpensesRepository;
import dev.codefuchs.household_budget.domain.expenses.Expense;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ExpensesServiceTest {
    @MockitoBean
    private ExpensesRepository repository;
    @MockitoBean
    private BudgetsRepository budgetsRepository;

    @Test
    void gets_out_the_correct_output() {
        List.of(
                Expense.builder()
                        .id(UUID.randomUUID())
                        .date(LocalDate.now())
        );
    }
}
