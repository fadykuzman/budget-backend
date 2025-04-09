package dev.codefuchs.household_budget;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BudgetCategoryServiceTest {

    private final BudgetCategoryService service = new BudgetCategoryService();

    @AfterEach
    void tearDown() {
        service.budgetCategories.clear();
    }

    @Test
    void given_no_budget_category_when_one_is_added_then_should_be_added() {
        var categories = service.add("Groceries");
        assertThat(categories).hasSize(1);
    }

    @Test
    void given_a_budget_category_when_another_one_is_added_then_should_be_added() {
        var groceries = service.add("Groceries");
        var amazon = service.add("Amazon");
        assertThat(amazon).hasSize(2);
    }

    @Test
    void given_a_budget_category_when_another_one_of_same_name_is_added_should_not_be_added_shows_error() {
        var categoryName = "Groceries";
        service.add(categoryName);
        assertThatThrownBy(() -> service.add(categoryName.toLowerCase()))
                .isExactlyInstanceOf(BudgetCategoryAlreadyExists.class)
                .hasMessage("Budget Category '%s' already exists", categoryName.toLowerCase());
    }
}
