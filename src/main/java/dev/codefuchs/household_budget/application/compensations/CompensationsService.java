package dev.codefuchs.household_budget.application.compensations;

import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.adapters.out.persistence.compensations.CompensationsRepository;
import dev.codefuchs.household_budget.application.budgets.BudgetNotFoundException;
import dev.codefuchs.household_budget.domain.budget.Budget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompensationsService {
    private final CompensationsRepository repository;
    private final BudgetsRepository budgetsRepository;
    public void add(AddCompensationInput input) {
        var budget = budgetsRepository.findById(input.budgetId())
                .orElseThrow(() -> new BudgetNotFoundException(input.budgetId()));
        var compensation = input.toCompensation(budget);
        repository.save(compensation);
    }
}
