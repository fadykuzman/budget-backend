package dev.codefuchs.household_budget.application.budgets;

import dev.codefuchs.household_budget.adapters.in.web.budgets.BudgetsResponse;
import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetDTO;
import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.application.budget_categories.BudgetCategoryAddedEvent;
import dev.codefuchs.household_budget.domain.budget.Budget;
import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class BudgetsService {

    private final BudgetsRepository repository;

    @EventListener
    public void createBudgets(BudgetCategoryAddedEvent event) {

        // TODO: should notify user if budgets already exist for any month of the period

        var budgetCategory = event.budgetCategory();
        if (event.startDate() == null) {
            YearMonth now = YearMonth.now();
            createBudget(budgetCategory, now);
        } else if (event.endDate() == null) {
            YearMonth now = YearMonth.now();
            createBudgetsRetrospectively(event, now);
        } else {
            createBudgetsRetrospectively(event, event.endDate());
        }
    }

    private void createBudget(BudgetCategory budgetCategory, YearMonth now) {
        var budget = Budget.builder()
                .amount(budgetCategory.getAmount())
                .year(now.getYear())
                .month(now.getMonthValue())
                .budgetCategory(budgetCategory)
                .build();
        repository.save(budget);
    }

    private void createBudgetsRetrospectively(BudgetCategoryAddedEvent event, YearMonth now) {
        for (YearMonth start = event.startDate(); start.isBefore(now.plusMonths(1)); start = start.plusMonths(1)) {
            createBudget(event.budgetCategory(), start);
        }
    }


    public BudgetsResponse getForMonth(YearMonth yearMonth) {
        var list = repository.findAllByYearAndMonth(yearMonth.getYear(), yearMonth.getMonthValue())
                .stream().map(budget ->
                    new BudgetDTO(budget.getId().toString(), budget.getBudgetCategory().getName(), budget.getAmount())
                ).toList();
        return new BudgetsResponse(list);
    }
}
