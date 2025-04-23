package dev.codefuchs.household_budget.application.expenses;

import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.adapters.out.persistence.expenses.ExpensesRepository;
import dev.codefuchs.household_budget.application.budgets.BudgetNotFoundException;
import dev.codefuchs.household_budget.domain.expenses.Expense;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        int budgetTotalExpenses = repository.sumAmountByBudgetId(budgetId);
        // Group by purpose and get total amount
        List<Expense> allByBudgetId = repository.findAllByBudgetId(budgetId);
        Map<String, List<Expense>> groupedByPurpose = allByBudgetId.stream()
                .collect(Collectors.groupingBy(
                        Expense::getPurpose
                ));
        List<PurposeOutput> purposeOutputs = groupedByPurpose.entrySet()
                .stream().map(entry -> {
                    int totalAmount = entry.getValue().stream().mapToInt(Expense::getAmount).sum();
                    var entries = entry.getValue().stream().map(e -> new ExpenseEntryOutput(e.getId(), e.getDate().getDayOfMonth(), e.getAmount())).toList();
                    return new PurposeOutput(entry.getKey(), totalAmount, entries);
                }).toList();
        // Group by purpose and get list under purpose
        // list of all purposes with total amount for purpose and list of expenses
        return new GetExpensesOutput(budgetTotalExpenses, purposeOutputs);
    }

    public int getTotalAmount(UUID budgetId) {
        return repository.sumAmountByBudgetId(budgetId);
    }
}
