
CREATE TABLE user_db(
    national_id int primary key not null, 
    user_name_ varchar(100) not null, 
    user_password varchar(100) not null,
    user_email varchar(100) not null,
    user_phone varchar(100) not null
);

CREATE TABLE admin_db(
    user_name_ varchar(100) primary key not null, 
    user_password varchar(100) not null
);

CREATE TABLE train(
    train_id int primary key not null,
    seats_number int not null,
    travel_line varchar(100),
    start_station varchar(100),
    end_station varchar(100),
    occupied_seats int not null,
    remaining_seats int not null
);

CREATE TABLE ticket(
    ticket_id int primary key not null,
    train_id int not null references train(train_id),
    start_station varchar(100) not null,
    end_station varchar(100) not null,
    start_time varchar(100),
    expected_end_time varchar(100),
    cost int not null,
    train_seat_number int not null
);

CREATE TABLE booked_tickets(
    ticket_id int not null references ticket(ticket_id),
    national_id int not null references user_db(national_id)
);