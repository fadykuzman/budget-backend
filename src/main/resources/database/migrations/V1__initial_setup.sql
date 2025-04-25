CREATE TABLE budget_categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50),
    amount INT,
    amount NUMERIC(19,2),
    currency VARCHAR(3) DEFAULT 'EUR',
    cadence VARCHAR(50),
    start_month INT,
    start_year INT,
    end_month INT,
    end_year INT
);

CREATE TABLE budgets (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount INT,
    amount NUMERIC(19,2),
    currency VARCHAR(3) DEFAULT 'EUR',
    year INT,
    month INT,
    budget_category_id UUID,
    FOREIGN KEY (budget_category_id) REFERENCES budget_categories(id)
);

CREATE TABLE expenses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    purpose VARCHAR(200),
    date DATE DEFAULT CURRENT_DATE,
    amount INT,
    amount NUMERIC(19,2),
    currency VARCHAR(3) DEFAULT 'EUR',
    budget_id UUID,
    FOREIGN KEY (budget_id) REFERENCES budgets(id)
);


CREATE TABLE compensations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    source VARCHAR(200),
    amount INT,
    amount NUMERIC(19,2),
    currency VARCHAR(3) DEFAULT 'EUR',
    date DATE DEFAULT CURRENT_DATE,
    budget_id UUID,
    FOREIGN KEY (budget_id) REFERENCES budgets(id)
);
