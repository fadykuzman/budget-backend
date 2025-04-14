package dev.codefuchs.household_budget.adapters.in.web.budgets;

import dev.codefuchs.household_budget.application.budgets.BudgetsService;
import dev.codefuchs.household_budget.application.budgets.GetBudgetsOutput;
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
        var yearMonth = YearMonth.parse(month);
        return service.getForMonth(yearMonth);
    }
}
