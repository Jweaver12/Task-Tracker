DELETE FROM task;
DELETE FROM project;
DELETE FROM category;
DELETE FROM `user`;

INSERT INTO `user`
VALUES
    (1, 'jweaver'),
    (2, 'testuser'),
    (3, 'deleteuser');

INSERT INTO project
VALUES
    (1, 'School'),
    (2, 'Personal'),
    (3, 'Temporary');

INSERT INTO category
VALUES
    (1, 'Homework'),
    (2, 'General'),
    (3, 'Temporary');

INSERT INTO task
VALUES
    (1, 'Java Assignment', 'Finish Enterprise Java work',
     '2026-10-20', false, 1, 1, 1),
    (2, 'Personal Task', 'Complete personal task',
     '2026-10-25', false, 2, 2, 2);