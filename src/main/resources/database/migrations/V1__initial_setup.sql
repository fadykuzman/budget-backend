CREATE TABLE budgets (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category VARCHAR(50),
    amount INT,
    start_month INT,
    start_year INT,
    end_month INT,
    end_year INT
);