CREATE TABLE compensations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    source VARCHAR(200),
    amount INT,
    date DATE DEFAULT CURRENT_DATE,
    budget_id UUID,
    FOREIGN KEY (budget_id) REFERENCES budgets(id)
);