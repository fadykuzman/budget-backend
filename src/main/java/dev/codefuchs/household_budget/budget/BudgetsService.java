package dev.codefuchs.household_budget.budget;

import dev.codefuchs.household_budget.budget.add_budget.AddBudgetInput;
import dev.codefuchs.household_budget.budget.list_budgets.GetBudgetsOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetsService {

    private final BudgetsRepository repository;

    public GetBudgetsOutput getForMonth(YearMonth month) {

        List<BudgetDTO> list = repository.findAll()
                .stream()
                .filter(b -> {
                    var startDate = YearMonth.of(b.getStartYear(), b.getStartMonth());
                    var endDate = YearMonth.of(b.getEndYear(), b.getEndMonth());
                    return (startDate.isBefore(month) || startDate.equals(month)) && (endDate.isAfter(month) || endDate.equals(month));
                })
                .map(entity -> new BudgetDTO(
                        entity.getId().toString(),
                        entity.getCategory(),
                        entity.getAmount()))
                .toList();
        return new GetBudgetsOutput(list);
    }

    public void add(AddBudgetInput input) {
        var newBudget = Budget.builder()
                .category(input.category())
                .amount(input.amount())
                .startMonth(input.startDate().getMonthValue())
                .startYear(input.startDate().getYear())
                .endMonth(input.endDate().getMonthValue())
                .endYear(input.endDate().getYear())
                .build();
        repository.save(newBudget);
    }
}
