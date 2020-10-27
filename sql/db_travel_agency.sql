DROP DATABASE IF EXISTS travel_agency;
CREATE DATABASE travel_agency;

USE travel_agency;

/*login: unique, maximum 16 characters - minimum 6 characters (letters, digits, underscore)
Latin + At least 1 lowercase letter + At least 1 uppercase letter*/
/*password: maximum 16 characters - minimum 6 characters (letters, digits, underscore),
Latin + At least 1 lowercase letter + At least 1 uppercase letter + At least 1 digit*/
/*Email: first part according to RFC 5322 - then
@ - letters, digits, underscore, dash at least one - dot - letters, digits, underscore from 2 to 6 characters */

/*users*/
CREATE TABLE users
(
    id_user        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    login          VARCHAR(16) UNIQUE                  NOT NULL,
    password       CHAR(89)                            NOT NULL,
    email          VARCHAR(32)                         NOT NULL,
    role           ENUM ('admin', 'moderator', 'user') NOT NULL DEFAULT 'user',
    status         boolean                             NOT NULL DEFAULT true,
    email_approved boolean                                      DEFAULT false
);

INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Admin1',
        'DYdudYtGLlf/Myi1uJz/X3FCgHEBKCkHRuBOhRjca7c=$6BAMuBoQFj8MZ6snefd294e5DIlqCB5zsnHsJ230U88=', 'admin1@mail.trag',
        'admin', true, true);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Admin2',
        '+mvi2V/6XG+dYnGriFVoyY5Jqh5JoTMW82GlcCiRxgY=$aIJD1Gs7pLUF+b+ZKCsRWzKgnx3o0uzTLA1FakBiRko=', 'Admin2@mail.trag',
        'admin', false, false);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Raccoon10',
        '1DKgrSWtCwJT92oN3rsQ7BS1X3idaNZVZuuGHTpSzs4=$BENso21+xVOPWU4DC/UtB2HmAbsH2LBF//nLhR6XuHY=',
        'Raccoon10@mail.trag',
        'user', true, false);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Snake222',
        'XcN+bmeMqJPya1srm5q2jHd15fFxDW6yo+D1+Fb1dDo=$xWciDYJ0Axe8As/+g9A254Grhd9QzzWFjrir+vbX99E=',
        'Snake222@mail.trag',
        'user', false, false);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Hedgehog32',
        'SpbNOJ5Vz7s/LZ8VW1XDZSxqp67L13TIpirpU2nMbpY=$w+B/Ce9LnhnTGp0xkGwxY8Lir5qhah/TNJBL+HMwbIQ=',
        'Hedgehog32@mail.trag',
        'user', true, true);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Sprat32',
        'za73qZTPrMsHruPxFkULpP69GCsnNf2tdrRQL0Y9+Tk=$Vmipk/LHW45MQ5M+zIh0tfuFavohswZ5fVrZU59aUbA=',
        'Sprat32@mail.trag',
        'moderator', true, true);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Woodpecker0',
        'vOP3C1R+2BLhQxx1STf0S7UjniEMbwy9azMMdBCWVfU=$R7GKrwTTzhiGFvISqgMWvWZc+6OYfmozaamUzRWtxBg=',
        'Woodpecker0@mail.trag',
        'moderator', false, false);

/* tours*/
create table tours
(
    id_tour          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    tour_purpose     ENUM ('rest', 'excursion','shopping' )   NOT NULL,
    country          VARCHAR(32)                              NOT NULL,
    hotel_name       VARCHAR(32)                              NOT NULL,
    hotel_stars      ENUM ('hostel', 'three', 'four', 'five') NOT NULL,
    transport        ENUM ('bus','airplane')                  NOT NULL,
    date_start       LONG                                     NOT NULL,
    quantity_of_days SMALLINT(3) UNSIGNED                     NOT NULL,
    price            INT(5) UNSIGNED                          NOT NULL,
    quantity_tours   TINYINT(2) UNSIGNED                      NOT NULL,
    description      TEXT                                     NOT NULL,
    image_path       VARCHAR(64)                              NOT NULL DEFAULT 'default.jpg'
);

INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('rest', 'Belarus', 'Zvezda', 'three', 'bus', 1827648000, 14, 950, 17, 'Healthy rest in sanatorium',
        'default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('excursion', 'Belarus', 'Nyasvizh', 'four', 'bus', 1627648000, 7, 450, 30, 'Medieval beautiful history',
        'default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('shopping', 'Belarus', 'Cosmos', 'hostel', 'bus', 1727648000, 2, 300, 10, 'Shopping tour',
        'default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('rest', 'Chile', 'Remota', 'five', 'airplane', 1927648000, 16, 4500, 21, 'Rest and extremes',
        'default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('excursion', 'Chile', 'Takarua Lodge', 'hostel', 'airplane', 1670648000, 10, 3200, 14, 'Far away history',
        'default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('shopping', 'Chile', 'UGO Hotel', 'four', 'airplane', 1740648000, 6, 2700, 7, 'Shopping tour in the capital',
        'default.jpg');

/*one tour - one tour_hot */
create table tours_hot
(
    id_tour_hot INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_tour_fk  INT UNSIGNED UNIQUE,
    discount    TINYINT(2) UNSIGNED,
    FOREIGN KEY (id_tour_fk) REFERENCES tours (id_tour)
);

INSERT INTO tours_hot(id_tour_fk, discount)
VALUES (2, 10);
INSERT INTO tours_hot(id_tour_fk, discount)
VALUES (4, 7);
INSERT INTO tours_hot(id_tour_fk, discount)
VALUES (6, 5);

/*one user - many passports, into order*/
create table passport
(
    id_passport     INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_user_fk      INT UNSIGNED NOT NULL,
    surname         VARCHAR(32)  NOT NULL,
    name            VARCHAR(32)  NOT NULL,
    birth_date      LONG         NOT NULL,
    passport_number VARCHAR(32)  NOT NULL,
    passport_image  VARCHAR(32),
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user)
);

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number)
VALUES (3, 'Ivanov', 'Ivan', 1600000000, 'HE8975899');

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number)
VALUES (3, 'Ivanova', 'Olga', 1600100000, 'LG1564689');

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number)
VALUES (3, 'Ivanov', 'Igor', 1600200000, 'IK0009998');

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number)
VALUES (4, 'Merier', 'Luis', 1600300000, 'JU8484949');

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number)
VALUES (4, 'Buijuer', 'Anette', 1600400000, 'EW8798546');

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number)
VALUES (5, 'Norris', 'Chuck', 1600500000, 'US9999888');

/*into order*/
create table travel_docs
(
    id_travel_docs INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    voucher        VARCHAR(64),
    insurance      VARCHAR(64),
    ticket         VARCHAR(64)
);

INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('default_voucher.pdf', 'default_insurance.pdf', 'default_ticket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('default_voucher.pdf', 'default_insurance.pdf', 'default_ticket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('default_voucher.pdf', 'default_insurance.pdf', 'default_ticket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('default_voucher.pdf', 'default_insurance.pdf', 'default_ticket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('default_voucher.pdf', 'default_insurance.pdf', 'default_ticket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('default_voucher.pdf', 'default_insurance.pdf', 'default_ticket.pdf');

/*one user - one customers_sheet*/
create table sheet
(
    id_sheet          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_user_fk        INT UNSIGNED UNIQUE NOT NULL,
    sheet_sum         INT(7) UNSIGNED     NOT NULL DEFAULT 0,
    customer_discount ENUM ('0','5','7','10','15') DEFAULT '0',
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user)
);

ALTER TABLE sheet
    AUTO_INCREMENT = 2000;

INSERT INTO sheet(id_user_fk, sheet_sum, customer_discount)
VALUES (3, 500000, '10');
INSERT INTO sheet(id_user_fk, sheet_sum, customer_discount)
VALUES (4, 1500000, '0');
INSERT INTO sheet(id_user_fk, sheet_sum, customer_discount)
VALUES (5, 50000000, '15');

create table operation
(
    id_operation      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_sheet_fk       INT UNSIGNED UNIQUE NOT NULL,
    operation_sum     INT(7) UNSIGNED     NOT NULL DEFAULT 0,
    operation_purpose VARCHAR(64)         NOT NULL DEFAULT 'default Error',
    FOREIGN KEY (id_sheet_fk) REFERENCES sheet (id_sheet)
);


/*one user - one admin_moderator*/
create table staff
(
    id_staff   INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_user_fk INT UNSIGNED UNIQUE NOT NULL,
    surname    VARCHAR(32)         NOT NULL,
    name       VARCHAR(32)         NOT NULL,
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user)
);

INSERT INTO staff(id_user_fk, surname, name)
VALUES (1, 'Sergeev', 'Sergey');
INSERT INTO staff(id_user_fk, surname, name)
VALUES (2, 'Andreeva', 'Ilona');
INSERT INTO staff(id_user_fk, surname, name)
VALUES (6, 'Chubrik', 'Gennadiy');
INSERT INTO staff(id_user_fk, surname, name)
VALUES (7, 'Zayceva', 'Irina');

/*one order - many tours - many passports - one travel_documents*/
create table orders
(
    id_order          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_tour_fk        INT UNSIGNED                                        NOT NULL,
    id_passport_fk    INT UNSIGNED                                        NOT NULL,
    id_travel_docs_fk INT UNSIGNED UNIQUE                                 NOT NULL,
    date_order        LONG                                                NOT NULL,
    state             ENUM ('new','confirm','paid','add_docs','finished') NOT NULL DEFAULT 'new',
    comment           VARCHAR(128),
    FOREIGN KEY (id_tour_fk) REFERENCES tours (id_tour),
    FOREIGN KEY (id_passport_fk) REFERENCES passport (id_passport),
    FOREIGN KEY (id_travel_docs_fk) REFERENCES travel_docs (id_travel_docs)
);



/*may be*/
create table tours_unavailable
(   id_tour          INT UNSIGNED UNIQUE,
    tour_purpose     ENUM ('rest', 'excursion','shopping' )     NOT NULL,
    country          VARCHAR(32)                                NOT NULL,
    hotel_name       VARCHAR(32)                                NOT NULL,
    hotel_stars      ENUM('hostel', 'three', 'four', 'five')    NOT NULL,
    transport        ENUM ('bus','airplane')                    NOT NULL,
    price            INT(5) UNSIGNED                            NOT NULL,
    image_path       VARCHAR(64)                                NOT NULL DEFAULT 'img/tours/default.jpg'
);


select * from users;
select * from sheet;
select * from operation;
select * from staff;
select * from passport;
select * from travel_docs;
select * from orders;
select * from tours;
select * from tours_hot;
