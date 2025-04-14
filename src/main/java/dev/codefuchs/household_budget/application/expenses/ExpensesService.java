package dev.codefuchs.household_budget.application.expenses;

import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.adapters.out.persistence.expenses.ExpensesRepository;
import dev.codefuchs.household_budget.application.budgets.BudgetNotFoundException;
import dev.codefuchs.household_budget.domain.budget.Budget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        List<ExpenseOutput> expenseOutputs = repository.findAllByBudgetId(budgetId)
                .stream()
                .map(expense -> new ExpenseOutput(expense.getId(), expense.getPurpose(), expense.getAmount()))
                .toList();
        int totalAmount = expenseOutputs.stream().mapToInt(ExpenseOutput::amount)
                .sum();
        return new GetExpensesOutput(expenseOutputs, totalAmount);
    }
}
