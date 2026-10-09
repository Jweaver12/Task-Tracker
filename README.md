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
- REST API integration

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

- As a user, I want to send or display task due-date information through an
  external calendar service so that my tasks can work with a scheduling tool
  I already use.

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

## Screen Design

Screen designs and wireframes are documented in the project repository.

```text
Project
   |
   | One-to-Many
   v
Task