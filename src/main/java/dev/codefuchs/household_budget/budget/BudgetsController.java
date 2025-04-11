package dev.codefuchs.household_budget.budget;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/budgets")

public class BudgetsController {
    @GetMapping
    public BudgetsResponse getAll() {
        return new BudgetsResponse(
                List.of(
                        new Budget(1, "Groceries", 700),
                        new Budget(2, "Amazon", 100)
                )
        );
    }
}
