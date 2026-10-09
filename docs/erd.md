# Task Tracker ERD

This ERD shows the planned database structure for the Task Tracker
application.

```mermaid
erDiagram
    PROJECT ||--o{ TASK : contains
    CATEGORY ||--o{ TASK : categorizes

    PROJECT {
        int project_id PK
        string name
    }

    CATEGORY {
        int category_id PK
        string name
    }

    TASK {
        int task_id PK
        int project_id FK
        int category_id FK
        string name
        string description
        date due_date
        boolean completed
    }
```

## Relationships

### Project to Task

One project can contain many tasks.

A task belongs to one project.

### Category to Task

One category can be assigned to many tasks.

A task belongs to one category.

These relationships provide the multiple one-to-many relationships
required for the individual project.