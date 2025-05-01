package dev.codefuchs.household_budget.application.expenses;

import dev.codefuchs.household_budget.adapters.out.persistence.budgets.BudgetsRepository;
import dev.codefuchs.household_budget.adapters.out.persistence.expenses.ExpensesRepository;
import dev.codefuchs.household_budget.application.budgets.BudgetNotFoundException;
import dev.codefuchs.household_budget.domain.budget.Budget;
import dev.codefuchs.household_budget.domain.expenses.Expense;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
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
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
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
                    var entries = entry.getValue()
                            .stream()
                            .map(e -> new ExpenseEntryOutput(
                                    e.getId(),
                                    e.getDate().toString(),
                                    e.getAmount()))
                            .sorted(Comparator.comparing(ExpenseEntryOutput::date))
                            .toList();
                    return new PurposeOutput(entry.getKey(), totalAmount, entries);
                }).toList();
        // Group by purpose and get list under purpose
        // list of all purposes with total amount for purpose and list of expenses
        return new GetExpensesOutput(budgetTotalExpenses, purposeOutputs);
    }

    public int getTotalAmount(UUID budgetId) {
        return repository.sumAmountByBudgetId(budgetId);
    }

    public void delete(UUID expenseId) {
        repository.deleteById(expenseId);
    }

    public void update(UpdateExpenseInput input) {
        Expense expense = repository.findById(input.id())
                .orElseThrow(() -> new ExpenseNotFound(input.id()));
        expense.update(input.amount());
    }

    public void updateDate(UpdateExpenseDateInput input) {
        var expense = repository.findById(input.id())
                .orElseThrow(() -> new ExpenseNotFound(input.id()));
        expense.updateDate(input.date());
    }

    public int getTotalAmount(List<UUID> budgetIds) {
        return repository.sumAmountByBudgetIds(budgetIds);
    }
}
