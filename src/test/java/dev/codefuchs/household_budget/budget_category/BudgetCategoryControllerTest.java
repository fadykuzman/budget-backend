package dev.codefuchs.household_budget.budget_category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BudgetCategoryController.class)
public class BudgetCategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void given_no_budget_categories_when_called_return_object_with_empty_list() throws Exception {
        mvc.perform(get("/budget/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        """
                                {
                                  "categories": []
                                }
                                """
                ));
    }
}
