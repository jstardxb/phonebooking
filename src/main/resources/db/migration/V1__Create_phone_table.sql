CREATE TABLE phone (
    id bigint not null AUTO_INCREMENT PRIMARY KEY,
    booked_at timestamp(6),
    booked_by varchar(255),
    model varchar(255),
    primary key (id));

INSERT INTO phone (id, model, booked_at, booked_by)
VALUES (1, 'Samsung Galaxy S9', NULL, NULL),
       (2, '2x Samsung Galaxy S8', NULL, NULL),
       (3, 'Motorola Nexus 6', NULL, NULL),
       (4, 'Oneplus 9', NULL, NULL),
       (5, 'Apple iPhone 13', NULL, NULL),
       (6, 'Apple iPhone 12', NULL, NULL),
       (7, 'Apple iPhone 11', NULL, NULL),
       (8, 'iPhone X', NULL, NULL),
       (9, 'Nokia 3310', NULL, NULL);