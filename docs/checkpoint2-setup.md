# Checkpoint 2 Local Setup

The Java/Hibernate portion of Checkpoint 2 is included in the project.
Before running the DAO tests, complete the local MySQL setup.

## Database Expected by Hibernate

Database name:

```text
task_tracker
```

Tables expected by the entities:

```text
project
category
task
```

The table columns should match `docs/erd.md` and the Hibernate entity
annotations in `src/main/java/edu/matc/entity`.

## Hibernate Credentials

Open:

```text
src/main/resources/hibernate.cfg.xml
```

Replace only this placeholder with the local MySQL password:

```text
CHANGE_ME
```

Do not commit a real password to GitHub. Before committing, change the value
back to `CHANGE_ME`.

## Test Command

After the database and tables exist and the local Hibernate credentials are
set, run:

```text
mvn test
```

The DAO tests clear their own test records before each test. They do not
create the database or tables.
