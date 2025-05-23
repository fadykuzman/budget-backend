package dev.codefuchs.household_budget.adapters.in.web.budget_categories;

import dev.codefuchs.household_budget.adapters.in.web.budgets.ChangeBudgetNameRequest;
import dev.codefuchs.household_budget.application.budget_categories.BudgetCategoriesService;
import dev.codefuchs.household_budget.application.budget_categories.AddBudgetCategoryInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/budgets/categories")
@RequiredArgsConstructor
public class BudgetCategoriesController {
    private final BudgetCategoriesService service;

    @GetMapping
    public GetBudgetCategoriesResponse get() {
        var output = service.get();
        return new GetBudgetCategoriesResponse(output);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AddBudgetCategoryRequest request) {
        var input = new AddBudgetCategoryInput(
                request.name(), request.amount(), request.cadence(), request.startDate(), request.endDate());
        service.add(input);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam("id") String id) {
        service.delete(UUID.fromString(id));
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeName(@RequestBody ChangeBudgetNameRequest request) {
        var input = request.toInput();
        service.changeName(input);
    }
}
