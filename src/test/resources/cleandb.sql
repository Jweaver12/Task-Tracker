DELETE FROM task;
DELETE FROM project;
DELETE FROM category;
DELETE FROM `user`;

INSERT INTO `user` (id, username)
VALUES
    (1, 'jweaver'),
    (2, 'testuser'),
    (3, 'deleteuser');

INSERT INTO project (id, name, user_id)
VALUES
    (1, 'School', 1),
    (2, 'Personal', 2),
    (3, 'Temporary', 3);

INSERT INTO category (id, name)
VALUES
    (1, 'Homework'),
    (2, 'General'),
    (3, 'Temporary');

INSERT INTO task
(id, name, description, due_date, completed,
 project_id, category_id, user_id)
VALUES
    (1, 'Java Assignment', 'Finish Enterprise Java work',
     '2026-10-20', false, 1, 1, 1),
    (2, 'Personal Task', 'Complete personal task',
     '2026-10-25', false, 2, 2, 2);