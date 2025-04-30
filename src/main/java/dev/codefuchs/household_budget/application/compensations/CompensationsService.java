package dev.codefuchs.household_budget.application.compensations;

import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.adapters.out.persistence.compensations.CompensationsRepository;
import dev.codefuchs.household_budget.application.budgets.BudgetNotFoundException;
import dev.codefuchs.household_budget.domain.budget.Budget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
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

    public GetCompensationsForBudgetOutput getForBudget(UUID budgetId) {
        var compensationOutputs = repository.findAllByBudgetId(budgetId)
                .stream()
                .map(compensation -> new CompensationOutput(
                        compensation.getId(),
                        compensation.getSource(),
                        compensation.getAmount(),
                        compensation.getDate()))
                .toList();
        int totalAmount = compensationOutputs.stream().mapToInt(CompensationOutput::amount).sum();
        return new GetCompensationsForBudgetOutput(compensationOutputs, totalAmount);
    }

    public int getTotalAmount(UUID budgetId) {
        return repository.findTotalCompensationById(budgetId);
    }

    public void delete(UUID compensationId) {
        repository.deleteById(compensationId);
    }

    public void update(UpdateCompensationInput input) {
        var compensation = repository.findById(input.id())
                .orElseThrow(() -> new CompensationNotFoundException(input.id()));
        compensation.update(input.amount());
    }

    public void updateDate(UpdateCompensationDateInput input) {
        var compensation = repository.findById(input.id())
                .orElseThrow(() -> new CompensationNotFoundException(input.id()));
        compensation.updateDate(input.date());
    }
}
