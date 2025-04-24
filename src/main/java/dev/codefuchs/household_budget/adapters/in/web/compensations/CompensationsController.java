package dev.codefuchs.household_budget.adapters.in.web.compensations;

import dev.codefuchs.household_budget.application.compensations.CompensationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/compensations")
@RequiredArgsConstructor
public class CompensationsController {
    private final CompensationsService service;

    @GetMapping
    public GetCompensationsForBudgetResponse getForBudget(
            @RequestParam String budgetId
    ) {
        var id = UUID.fromString(budgetId);
        var output = service.getForBudget(id);
        return new GetCompensationsForBudgetResponse(output);
    }

    @PostMapping
    public void add(@RequestBody AddCompensationRequest request) {
        var input = request.toInput();
        service.add(input);
        System.out.println("Compensation added: " + request);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") String id) {
        var compensationId = UUID.fromString(id);
        service.delete(compensationId);
    }

    @PatchMapping
    public void update(@RequestBody UpdateCompensationRequest request) {
        var input = request.toInput();
        service.update(input);
    }
}
