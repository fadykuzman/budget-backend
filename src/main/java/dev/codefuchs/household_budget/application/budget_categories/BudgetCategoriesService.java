package dev.codefuchs.household_budget.application.budget_categories;

import dev.codefuchs.household_budget.adapters.out.persistence.budget_categories.BudgetCategoriesRepositery;
import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.application.budgets.BudgetNotFoundException;
import dev.codefuchs.household_budget.application.budgets.ChangeBudgetNameInput;
import dev.codefuchs.household_budget.domain.budget.Budget;
import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BudgetCategoriesService {
    private final BudgetCategoriesRepositery repository;
    private final BudgetsRepository budgetsRepository;
    private final ApplicationEventPublisher publisher;

    public void add(AddBudgetCategoryInput input) {
        var now = YearMonth.now();
        var startMonth = input.startDate() == null ? now.getMonthValue() : input.startDate().getMonthValue();
        var startYear = input.startDate() == null ? now.getYear() : input.startDate().getYear();
        var endMonth = input.endDate() == null ? 0 : input.endDate().getMonthValue();
        var endYear = input.endDate() == null ? 0 : input.endDate().getYear();
        var newBudget = BudgetCategory.builder()
                .name(input.name())
                .amount(input.amount())
                .cadence(input.cadence())
                .startMonth(startMonth)
                .startYear(startYear)
                .endMonth(endMonth)
                .endYear(endYear)
                .build();
        var savedBudget = repository.save(newBudget);
        var event = new BudgetCategoryAddedEvent(savedBudget, input.startDate(), input.endDate());
        publisher.publishEvent(event);
    }

    public GetBudgetCategoriesOutput get() {
        var list = repository.findAll().stream()
                .map(BudgetCategoryOutput::new)
                .toList();
        return new GetBudgetCategoriesOutput(list);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public void changeName(ChangeBudgetNameInput input) {
        var budget = budgetsRepository.findById(input.budgetId())
                .orElseThrow(() -> new BudgetNotFoundException(input.budgetId()));
        var category = budget.getBudgetCategory();
        category.changeName(input.name());
    }
}
