DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE project (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255),
                         user_id INT NOT NULL,
                         CONSTRAINT fk_project_user
                             FOREIGN KEY (user_id)
                                 REFERENCES `user`(id)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE
);

CREATE TABLE category (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255)
);

CREATE TABLE task (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255),
                      description VARCHAR(255),
                      due_date DATE,
                      completed BOOLEAN,
                      project_id INT,
                      category_id INT,
                      user_id INT,
                      CONSTRAINT fk_task_project
                          FOREIGN KEY (project_id)
                              REFERENCES project(id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE,
                      CONSTRAINT fk_task_category
                          FOREIGN KEY (category_id)
                              REFERENCES category(id),
                      CONSTRAINT fk_task_user
                          FOREIGN KEY (user_id)
                              REFERENCES `user`(id)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE
);