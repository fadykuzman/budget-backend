package dev.codefuchs.household_budget.budget_category;

import org.junit.jupiter.api.Test;

import java.time.YearMonth;

public class DummyTests {
    @Test
    void iterate_yearmonth() {
        var start = YearMonth.of(2023, 1);
        var end = YearMonth.of(2024, 1);
        for (var yearMonth = start; yearMonth.isBefore(yearMonth) || yearMonth.equals(yearMonth) ;  yearMonth = yearMonth.plusMonths(1)) {
            System.out.println(yearMonth);
        }
    }
}
