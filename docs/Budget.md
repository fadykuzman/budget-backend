# Budget Design

```mermaid
classDiagram
    class Budget {
        id
        category
        amount
    }
    class Expense {
        id
        category
        amount
    }
    class Balance {
        category
        amout
    }
    class BudgetCategory {
        id
        name
    }
```