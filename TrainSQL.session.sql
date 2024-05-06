
-- CREATE TABLE user_db(
--     national_id varchar(100) not null, 
--     user_name_ varchar(100) primary key not null, 
--     user_password varchar(100) not null,
--     user_email varchar(100) not null,
--     user_phone varchar(100) not null,
--     gender varchar(100) not null
-- );

-- CREATE TABLE admin_db(
--     user_name_ varchar(100) primary key not null, 
--     user_password varchar(100) not null
-- );

-- CREATE TABLE train(
--     train_id int primary key not null,
--     seats_number int not null,
--     start_station varchar(100),
--     end_station varchar(100),
--     occupied_seats int not null,
--     remaining_seats int not null
-- );

-- CREATE TABLE seat(
--     seat_id int,
--     train_id int not NULL REFERENCES train(train_id),
--     visited BOOLEAN
-- );

-- CREATE TABLE ticket(
--     ticket_id varchar(100) primary key not null,
--     train_id int not null references train(train_id),
--     start_station varchar(100) not null,
--     end_station varchar(100) not null,
--     start_time TIMESTAMP,
--     expected_end_time TIMESTAMP,
--     cost int not null,
--     seat_id int not null REFERENCES seat(seat_id)
-- );

-- CREATE TABLE booked_tickets(
--     ticket_id int not null references ticket(ticket_id),
--     user_name_ int not null references user_db(user_name_)
-- );