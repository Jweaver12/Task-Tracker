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
user
project
category
task
```

The table columns and relationships should match `docs/erd.md`,
`src/main/resources/dump.sql`, and the Hibernate entity annotations in
`src/main/java/edu/matc/entity`.

## Local Database Configuration

The following files contain local database configuration and are ignored
by Git:

```text
src/main/resources/hibernate.cfg.xml
src/test/resources/database.properties
```

Do not commit database passwords to GitHub.

## Test Database Setup

The DAO tests use:

```text
src/test/resources/cleandb.sql
```

to reset the database to a known state before each test.

This keeps the tests from depending on another DAO method to create the
records needed for testing.

## Test Command

After the database and tables exist and the local database credentials
are configured, run the Maven tests.

The tests require the local MySQL service to be running.