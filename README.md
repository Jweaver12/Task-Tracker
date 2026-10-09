# Task Tracker

## Problem Statement

People often keep track of tasks in several different places, such as
calendars, notes, reminder applications, paper lists, and project management
tools. Using multiple systems can make it difficult to see what still needs
to be completed, which tasks are most important, and which tasks belong to
a specific project.

Task Tracker will provide one focused location where users can organize
their work into projects and categories, track due dates, update task
information, and separate active work from completed work. The goal is to
provide the useful features of a task management application without
requiring the complexity of a larger team project management system.

## Project Goal

The goal of this project is to create an Enterprise Java web application
that allows authenticated users to organize and manage their tasks. Users
will be able to create projects, organize tasks into categories, add and
update tasks, track completion, and view their current work.

The application will use a relational database, Hibernate for database
access, AWS Cognito for authentication, and an external API for an
additional task-related feature.

## Technologies

Planned and currently used technologies include:

- Java
- Maven
- Hibernate / JPA
- MySQL
- JSP
- Servlets
- JUnit
- Log4J
- Tomcat
- Git and GitHub
- AWS
- AWS Cognito
- Google Calendar API
- REST API integration

## Chosen Web Service / API

Task Tracker will use the Google Calendar API.

The planned feature will allow a user to add a task and its due date to
their Google Calendar. This will allow Task Tracker to work with a calendar
service that users may already use for scheduling.

The API integration will be implemented later in the project when REST API
consumption is covered in the course.

## User Stories

### MVP User Stories

#### Authentication

- As a new user, I want to create an account so that I can save my own task
  information.
- As a returning user, I want to log in so that I can access my tasks.
- As a user, I want to log out so that my account remains secure.
- As a user, I want to reset my password if I forget it so that I can regain
  access to my account.

#### Projects and Categories

- As a user, I want to create a project so that I can group related tasks.
- As a user, I want to view my projects so that I can see how my work is
  organized.
- As a user, I want to assign a task to a project so that related tasks stay
  together.
- As a user, I want to assign a category to a task so that I can organize
  tasks by type.

#### Tasks

- As a user, I want to add a task so that I can keep track of work that needs
  to be completed.
- As a user, I want to give a task a description so that I know what needs to
  be done.
- As a user, I want to add a due date to a task so that I know when it should
  be completed.
- As a user, I want to edit a task so that I can update its information.
- As a user, I want to delete a task that I no longer need.
- As a user, I want to mark a task as complete so that I can track my
  progress.
- As a user, I want to view active and completed tasks separately so that I
  can focus on unfinished work.

#### External API

- As a user, I want to add a task and its due date to Google Calendar so that
  I can see my tasks alongside my existing schedule.

### Future User Stories

- As a user, I want to filter tasks by project or category so that I can
  quickly find specific work.
- As a user, I want to sort tasks by due date so that upcoming work appears
  first.
- As a user, I want to assign a priority to a task so that I can identify
  important work quickly.

## Database Relationships

The planned database will contain multiple one-to-many relationships.

### Project to Task

One project can contain many tasks.

```text
Project
   |
   | One-to-Many
   v
Task
```

### Category to Task

One category can be assigned to many tasks.

```text
Category
   |
   | One-to-Many
   v
Task
```

## Screen Design

The low-fidelity screen designs and application flow are documented in
[Task Tracker Wireframes](docs/wireframes.md).

## Database Design

The planned database relationships are documented in the
[Task Tracker ERD](docs/erd.md).

## Application Flow

1. A new user creates an account or an existing user logs in.
2. The user reaches the Task Tracker dashboard.
3. The dashboard displays the user's active and completed tasks.
4. The user can create projects and categories to organize tasks.
5. The user can add a task with a description, due date, project, and
   category.
6. The new task appears in the active task list.
7. The user can edit, complete, or delete an active task.
8. Completed tasks move to the completed task view.
9. The user can add task due-date information to Google Calendar.
10. The user can log out when finished.

## Project Plan

### Week 4 - Planning and Checkpoint 1

- Define the application problem.
- Identify MVP and future user stories.
- Create initial screen designs.
- Create the application flow.
- Create the initial project schedule.
- Submit Checkpoint 1 for instructor feedback.

### Week 5 - Hibernate Practice and Project Design

- Practice Hibernate One-to-Many relationships.
- Review how entities, DAOs, and unit tests work together.
- Continue refining the Task Tracker database design.
- Review Checkpoint 1 requirements.

### Week 6 - Checkpoint Revisions and Peer Review

- Review instructor Checkpoint 1 feedback.
- Expand the problem statement.
- Add all required project user stories.
- Add planned technologies to the README.
- Add multiple One-to-Many relationships.
- Select the Google Calendar API.
- Expand the project plan into detailed development tasks.
- Create project wireframes.
- Create the database ERD.
- Update the course time log.
- Complete the first peer review.
- Document feedback received during the review.

### Week 7 - Database, Hibernate, and Authentication

#### Project

- Create the project database table.
- Create the Project entity.
- Configure Hibernate mapping for Project.
- Use the course Generic DAO for database access.
- Create unit tests for Project database operations.

#### Category

- Create the category database table.
- Create the Category entity.
- Configure Hibernate mapping for Category.
- Use the course Generic DAO for database access.
- Create unit tests for Category database operations.

#### Task

- Create the task database table.
- Add foreign keys for Project and Category.
- Create the Task entity.
- Configure the One-to-Many relationships.
- Use the course Generic DAO for Task CRUD operations.
- Create unit tests for Task database operations.
- Test the entity relationships.
- Add Log4J logging.

#### Authentication

- Configure AWS Cognito.
- Implement account registration.
- Implement login.
- Implement logout.
- Implement password reset.

### Week 8 - REST API

- Review the Google Calendar API documentation.
- Test the Google Calendar API using the tools introduced in class.
- Create Java code that consumes the Google Calendar API.
- Connect task due-date information to the calendar feature.
- Test adding a task due date to Google Calendar.

### Week 9 - Web Interface and AWS

#### View Tasks

- Create a controller that retrieves task data.
- Create a JSP that displays tasks.
- Display active tasks.
- Display completed tasks.

#### Add Tasks

- Create the add-task form.
- Create the controller logic for adding a task.
- Connect the form to Hibernate.
- Verify the new task displays after it is created.

#### Edit and Complete Tasks

- Create the edit-task form.
- Create controller logic for updating tasks.
- Add the ability to mark a task complete.
- Verify changes are saved to the database.

#### Delete Tasks

- Add the delete-task action.
- Connect delete functionality to Hibernate.
- Verify deleted tasks are removed from the database.

#### Deployment

- Deploy the application to AWS.
- Connect the deployed application to its database.
- Verify authentication works.
- Verify database information displays in JSP pages.

### Week 10

- Add project management screens.
- Add category management screens.
- Connect project and category selection to task forms.
- Test the complete One-to-Many relationships.

### Week 11

- Continue implementing remaining MVP stories.
- Improve task filtering and organization.
- Add unit tests for new functionality.

### Week 12

- Continue application testing.
- Correct problems discovered during testing.
- Review application usability and navigation.

### Week 13

- Complete remaining MVP development.
- Verify Google Calendar API integration.
- Verify authentication and authorization.
- Review application logging and error handling.

### Week 14

- Complete the second peer code review.
- Document feedback received.
- Make changes based on peer and instructor feedback.

### Week 15

- Complete final application testing.
- Generate JavaDoc documentation.
- Update the README with final application information.
- Verify the deployed application is working correctly.
- Prepare the final presentation and demonstration.

### Week 16

- Complete final corrections.
- Present the finished application.
- Record and publish the final application demonstration.