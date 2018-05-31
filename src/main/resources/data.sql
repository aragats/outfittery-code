/**
 * CREATE Script for init of DB
 */


-- Create Stylists
INSERT INTO stylist (id, name, state)
VALUES (1, 'Anna', 'READY_TO_STYLE');

INSERT INTO stylist (id, name, state)
VALUES (2, 'Max', 'READY_TO_STYLE');

INSERT INTO stylist (id, name, state)
VALUES (3, 'John', 'ROOKIE');

-- Create 2 stylists with reservations

INSERT INTO stylist (id, name, state)
VALUES (4, 'Lisa', 'READY_TO_STYLE');





-- Create 3 Reservations

INSERT INTO reservation (id, date_created, comment, customer, from_time, to_time, stylist_id)
VALUES (1, now(), 'Comment 1', 'Customer 1', now(), now() + 10, 4);


