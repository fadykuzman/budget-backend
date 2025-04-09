package dev.codefuchs.household_budget;

public class BudgetCategoryAlreadyExists extends RuntimeException {
    public BudgetCategoryAlreadyExists(String message) {
        super(message);
    }

    public BudgetCategoryAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public BudgetCategoryAlreadyExists(Throwable cause) {
        super(cause);
    }
}
