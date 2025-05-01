package dev.codefuchs.household_budget.adapters.in.web.budgets;

import dev.codefuchs.household_budget.application.budgets.BudgetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/budgets")
@RequiredArgsConstructor
@Slf4j
public class BudgetsController {
    private final BudgetsService service;

    @GetMapping
    public BudgetsResponse getForMonth(@RequestParam("month") String month) {
        var yearMonth = YearMonth.parse(month);
        return service.getForMonth(yearMonth);
    }

    // TODO refactor to a summary controller
    @GetMapping(value = "summary", params = "id")
    public BudgetSummaryResponse getBudgetSummaryById(@RequestParam("id") String id) {
        UUID budgetId = UUID.fromString(id);
        var output = service.getSummary(budgetId);
        return new BudgetSummaryResponse(output);
    }

    @GetMapping(value = "summary", params = "month")
    public BudgetSummaryResponse getBudgetSummaryByMonth(@RequestParam("month") String month) {
        var yearMonth = YearMonth.parse(month);
        var output = service.getSummaryByMonth(yearMonth);
        return new BudgetSummaryResponse(output);
    }

    @DeleteMapping
    public void delete(@RequestParam("budgetId") String budgetId) {
        var id = UUID.fromString(budgetId);
        log.info("Deleting budget with id: {}", budgetId);
        service.delete(id);
    }

    @PatchMapping("target")
    public void updateTarget(@RequestBody UpdateBudgetTargetRequest request) {
        var input = request.toInput();
        service.updateTarget(input);
    }

}
