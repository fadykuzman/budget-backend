package dev.codefuchs.household_budget.adapters.in.web.compensations;

import dev.codefuchs.household_budget.application.compensations.CompensationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/compensations")
@RequiredArgsConstructor
public class CompensationsController {
    private final CompensationsService service;

    @PostMapping
    public void add(@RequestBody AddCompensationRequest request) {
        var input = request.toInput();
        service.add(input);
        System.out.println("Compensation added: " + request);
    }
}
