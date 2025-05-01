package dev.codefuchs.household_budget.adapters.in.events;

import dev.codefuchs.household_budget.application.budgets.BudgetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BudgetsListener {
    private final BudgetsService service;

    @EventListener
    public void onEvent(BudgetCategoryAddedEvent event) {
        var input = event.toInput();
        service.createBudgets(input);
    }
}
