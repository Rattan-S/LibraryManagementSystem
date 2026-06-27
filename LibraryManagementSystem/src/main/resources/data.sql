
-- Admin Password is now '1234'
-- User Password is now '1111'

INSERT INTO books (title, author) VALUES ('The 7 Habits of Highly Effective People', 'Stephen R. Covey');
INSERT INTO books (title, author) VALUES ('The Martian', 'Andy Weir');

INSERT INTO reviews (text, bookId) VALUES ('An older book, but still a very good read for principle-centered leadership.', 1);
INSERT INTO reviews (text, bookId) VALUES ('A great science fiction book about an astronaut stranded on Mars', 2);


INSERT INTO roles (rolename) VALUES ('ROLE_ADMIN');
INSERT INTO roles (rolename) VALUES ('ROLE_USER');


INSERT INTO users (email, encryptedpassword, enabled) 
VALUES ('sin19685@sheridancollege.ca', '$2a$10$vzpUjabrpKi6A6ehsqj6EO7GUlXfasns4T2eQCtiyQZDV15Y5ug7S', 1);

INSERT INTO users (email, encryptedpassword, enabled) 
VALUES ('dilpreet@sheridancollege.ca', '$2a$10$ldiIEaS37V3C8YLc3lHi2O6L0pvzwciQNw6txELmo0KL4M19Pcs8O', 1);


INSERT INTO user_role (userid, roleid) VALUES (1, 1);
INSERT INTO user_role (userid, roleid) VALUES (2, 2);