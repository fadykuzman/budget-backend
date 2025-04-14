CREATE TABLE budget_categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50),
    amount INT,
    cadence VARCHAR(50),
    start_month INT,
    start_year INT,
    end_month INT,
    end_year INT
);

CREATE TABLE budgets (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount INT,
    year INT,
    month INT,
    budget_category_id UUID,
    FOREIGN KEY (budget_category_id) REFERENCES budget_categories(id)
);