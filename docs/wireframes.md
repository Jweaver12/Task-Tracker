# Task Tracker Wireframes

These are low-fidelity wireframes for the Task Tracker MVP.

## Login

```text
+----------------------------------------+
|              TASK TRACKER              |
+----------------------------------------+

Email:
[____________________________]

Password:
[____________________________]

[ Log In ]

[ Create Account ]   [ Forgot Password ]




+----------------------------------------+
|            CREATE ACCOUNT              |
+----------------------------------------+

Email:
[____________________________]

Password:
[____________________________]

Confirm Password:
[____________________________]

[ Create Account ]

[ Back to Login ]




+----------------------------------------+
|            RESET PASSWORD              |
+----------------------------------------+

Email:
[____________________________]

[ Send Reset Instructions ]

[ Back to Login ]



+------------------------------------------------------+
| TASK TRACKER                         [ Log Out ]      |
+------------------------------------------------------+

[ Add Task ]   [ Projects ]   [ Categories ]

Active Tasks
--------------------------------------------------------
Task        Project      Category      Due Date
--------------------------------------------------------
Homework    School       Class         10/15/2026
                                    [Edit] [Complete]
                                    [Delete]
--------------------------------------------------------

Completed Tasks
--------------------------------------------------------
Study Java  School       Class         Completed
--------------------------------------------------------




+----------------------------------------+
|               ADD TASK                 |
+----------------------------------------+

Task Name:
[____________________________]

Description:
[____________________________]
[____________________________]

Due Date:
[____________________________]

Project:
[ Select Project v ]

Category:
[ Select Category v ]

[ Save Task ]   [ Cancel ]




+----------------------------------------+
|              EDIT TASK                 |
+----------------------------------------+

Task Name:
[ Homework___________________ ]

Description:
[ Finish Enterprise Java_____ ]
[ assignment_________________ ]

Due Date:
[ 10/15/2026 ]

Project:
[ School v ]

Category:
[ Class v ]

[ Save Changes ]   [ Cancel ]




+----------------------------------------+
|              PROJECTS                  |
+----------------------------------------+

New Project:
[____________________________]

[ Add Project ]

Existing Projects
------------------------------------------
School                         [Edit]
Personal                       [Edit]
Work                           [Edit]
------------------------------------------

[ Back to Dashboard ]




+----------------------------------------+
|              CATEGORIES                |
+----------------------------------------+

New Category:
[____________________________]

[ Add Category ]

Existing Categories
------------------------------------------
Class                          [Edit]
Appointment                    [Edit]
Personal                       [Edit]
------------------------------------------

[ Back to Dashboard ]




Create Account
      |
      v
    Login
      |
      v
Task Dashboard
   /    |     \
  v     v      v
Add   Projects Categories
Task
  |
  v
Dashboard
  |
  +--> Edit Task
  |
  +--> Complete Task
  |
  +--> Delete Task
  |
  v
Log Out