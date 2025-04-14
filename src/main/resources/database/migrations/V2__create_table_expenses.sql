CREATE TABLE expenses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    purpose VARCHAR(200),
    amount INT,
    budget_id UUID,
    FOREIGN KEY (budget_id) REFERENCES budgets(id)
);