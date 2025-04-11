package dev.codefuchs.household_budget.budget;

import dev.codefuchs.household_budget.budget.add_budget.AddBudgetInput;
import dev.codefuchs.household_budget.budget.add_budget.AddBudgetRequest;
import dev.codefuchs.household_budget.budget.list_budgets.BudgetsResponse;
import dev.codefuchs.household_budget.budget.list_budgets.GetBudgetsOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("v1/api/budgets")
@RequiredArgsConstructor
@Slf4j
public class BudgetsController {
    private final BudgetsService service;

    @GetMapping
    public BudgetsResponse getForMonth(@RequestParam("month") String month) {
        GetBudgetsOutput output = service.getForMonth(YearMonth.parse(month));
        return new BudgetsResponse(output.budgets());
    }

    @PostMapping
    public void add(@RequestBody AddBudgetRequest request) {
        var startDate = YearMonth.parse(request.startDate());
        var endDate = YearMonth.parse(request.endDate());
        var input = new AddBudgetInput(request.category(), request.amount(), startDate, endDate);
        service.add(input);
    }
}
