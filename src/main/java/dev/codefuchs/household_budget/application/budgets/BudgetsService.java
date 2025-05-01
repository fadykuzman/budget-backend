package dev.codefuchs.household_budget.application.budgets;

import dev.codefuchs.household_budget.adapters.in.web.budgets.BudgetsResponse;
import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetDTO;
import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.application.budget_categories.BudgetCategoriesService;
import dev.codefuchs.household_budget.application.compensations.CompensationsService;
import dev.codefuchs.household_budget.application.expenses.ExpensesService;
import dev.codefuchs.household_budget.domain.budget.Budget;
import dev.codefuchs.household_budget.domain.budget_categories.BudgetCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BudgetsService {
    private final BudgetsRepository repository;
    private final ExpensesService expensesService;
    private final CompensationsService compensationsService;
    private final BudgetCategoriesService budgetCategoriesService;

    public void createBudgets(CreateBudgetsInput input) {

        // TODO: should notify user if budgets already exist for any month of the period
        var budgetCategory = input.budgetCategory();
        YearMonth now = YearMonth.now();
        if (input.startDate() == null)
            createBudget(budgetCategory, now);
        else
            createBudgetsRetrospectively(input, now);

    }

    private Budget createBudget(BudgetCategory budgetCategory, YearMonth now) {
        var budget = Budget.builder()
                .amount(budgetCategory.getAmount())
                .year(now.getYear())
                .month(now.getMonthValue())
                .budgetCategory(budgetCategory)
                .build();
        return repository.save(budget);
    }

    private void createBudgetsRetrospectively(CreateBudgetsInput input, YearMonth now) {
        for (YearMonth start = input.startDate(); start.isBefore(now.plusMonths(1)); start = start.plusMonths(1)) {
            createBudget(input.budgetCategory(), start);
        }
    }


    public BudgetsResponse getForMonth(YearMonth yearMonth) {
        var list = repository.findAllByYearAndMonth(yearMonth.getYear(), yearMonth.getMonthValue())
                .stream().map(BudgetDTO::new
                ).toList();
        if (list.isEmpty()) {
            var created = createBudgets(yearMonth);
            return new BudgetsResponse(created);
        }

        return new BudgetsResponse(list);
    }

    private List<BudgetDTO> createBudgets(YearMonth yearMonth) {
        List<BudgetCategory> categories = budgetCategoriesService.findActiveOrEndingOnOrAfter(yearMonth);
        return categories.stream()
                .map(bc -> new BudgetDTO(createBudget(bc, yearMonth)))
                .toList();
    }

    public void delete(UUID budgetId) {
        repository.deleteById(budgetId);
    }

    public GetBudgetSummaryOutput getSummary(UUID budgetId) {
        // TODO refactor. this will cause spaghetti code
        // TODO: not use get()
        int target = repository.findById(budgetId).get().getAmount();
        int expenses = expensesService.getTotalAmount(budgetId);
        int compensation = compensationsService.getTotalAmount(budgetId);
        int balance = compensation - expenses;
        return new GetBudgetSummaryOutput(target, compensation, expenses, balance);
    }


    public void updateTarget(UpdateBudgetTargetInput input) {
        var budget = repository.findById(input.budgetId())
                .orElseThrow(() -> new BudgetNotFoundException(input.budgetId()));
        budget.updateTarget(input.target());
    }
}
