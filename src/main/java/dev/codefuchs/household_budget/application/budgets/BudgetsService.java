package dev.codefuchs.household_budget.application.budgets;

import dev.codefuchs.household_budget.adapters.in.web.budgets.BudgetsResponse;
import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetDTO;
import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.application.budget_categories.BudgetCategoryAddedEvent;
import dev.codefuchs.household_budget.domain.budget.Budget;
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
        // for monthly budgets
        // get all budgets for the category and time span
        // create new budgets for each month
        // save all

        // if startdate doesn't exist - create only for current month
        var budgetCategory = event.budgetCategory();
        if (event.startDate() == null) {
            YearMonth now = YearMonth.now();
            var budget = Budget.builder()
                    .amount(budgetCategory.getAmount())
                    .year(now.getYear())
                    .month(now.getMonthValue())
                    .budgetCategory(budgetCategory)
                    .build();
            repository.save(budget);
        }

        // if startdate exists and enddate doesn't - create for all months from startdate to now
        else if (event.endDate() == null) {
            YearMonth now = YearMonth.now();
            // loop from startdate to now
            for (YearMonth start = event.startDate(); start.isBefore(now.plusMonths(1)); start = start.plusMonths(1)) {
                var budget = Budget.builder()
                        .amount(budgetCategory.getAmount())
                        .year(start.getYear())
                        .month(start.getMonthValue())
                        .budgetCategory(budgetCategory)
                        .build();
                repository.save(budget);
            }
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
