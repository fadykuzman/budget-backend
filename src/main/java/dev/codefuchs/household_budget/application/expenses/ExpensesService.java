package dev.codefuchs.household_budget.application.expenses;

import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.adapters.out.persistence.expenses.ExpensesRepository;
import dev.codefuchs.household_budget.application.budgets.BudgetNotFoundException;
import dev.codefuchs.household_budget.domain.expenses.Expense;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpensesService {
    private final ExpensesRepository repository;
    private final BudgetsRepository budgetsRepository;

    public void add(AddExpenseInput input) {
        var budget = budgetsRepository.findById(input.budgetId())
                .orElseThrow(() -> new BudgetNotFoundException(input.budgetId()));
        var expense = input.toExpense(budget);
        repository.save(expense);
    }

    public GetExpensesOutput getForBudget(UUID budgetId) {
        var expenseOutputs = repository.findAllByBudgetId(budgetId)
                .stream()
                .collect(Collectors.groupingBy(
                        expense -> Map.entry(expense.getDate(), expense.getPurpose().toLowerCase().trim()),
                        Collectors.summingInt(Expense::getAmount)))
                .entrySet()
                .stream()
                .map(entry -> {
                    var key = entry.getKey();
                    var date = key.getKey();
                    var purpose = key.getValue();
                    var summedAmount = entry.getValue();
                    return new ExpenseOutput(purpose, summedAmount, date);
                })
                .sorted(Comparator.comparing(ExpenseOutput::date))
                .toList();
        int totalAmount = expenseOutputs.stream().mapToInt(ExpenseOutput::amount)
                .sum();
        return new GetExpensesOutput(expenseOutputs, totalAmount);
    }

    public int getTotalAmount(UUID budgetId) {
        return repository.sumAmountByBudgetId(budgetId);
    }
}
