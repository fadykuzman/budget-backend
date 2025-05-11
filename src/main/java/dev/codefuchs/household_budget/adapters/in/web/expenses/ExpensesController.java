package dev.codefuchs.household_budget.adapters.in.web.expenses;

import dev.codefuchs.household_budget.application.expenses.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/expenses")
@RequiredArgsConstructor
public class ExpensesController {
    private final ExpensesService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AddExpenseRequest request) {
        var input = request.toInput();
        service.add(input);
    }

    @GetMapping
    public GetExpensesForBudgetResponse getForBudget(
            @RequestParam String budgetId
    ) {
        var id = UUID.fromString(budgetId);
        var output = service.getForBudget(id);
        return new GetExpensesForBudgetResponse(output);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam("id") String id) {
        var expenseId = UUID.fromString(id);
        service.delete(expenseId);
    }

    @PatchMapping("amount")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UpdateExpenseRequest request) {
        var input = request.toInput();
        service.update(input);
    }

    @PatchMapping("date")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void updateDate(@RequestBody UpdateExpenseDateRequest request) {
        var input = request.toInput();
        service.updateDate(input);
    }
}
