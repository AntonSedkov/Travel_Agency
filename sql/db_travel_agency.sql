DROP DATABASE IF EXISTS travel_agency;
CREATE DATABASE travel_agency;

USE travel_agency;

/*login: unique, maximum 16 characters - minimum 6 characters (letters, digits, underscore)
Latin + At least 1 lowercase letter + At least 1 uppercase letter*/
/*password: maximum 16 characters - minimum 6 characters (letters, digits, underscore),
Latin + At least 1 lowercase letter + At least 1 uppercase letter + At least 1 digit*/
/*Email: first part according to RFC 5322 - then
@ - letters, digits, underscore, dash at least one - dot - letters, digits, underscore from 2 to 6 characters */

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

/*date starts from 01.01.2020: 30.11.2020 = 28944000 sec; 15.11.2020 = 27648000 sec*/
create table tours
(
    id_tour          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    tour_purpose     ENUM ('rest', 'excursion','shopping' ) NOT NULL,
    country          VARCHAR(32)                            NOT NULL,
    hotel_name       VARCHAR(32)                            NOT NULL,
    hotel_stars      ENUM ('hostel','3','4','5')            NOT NULL,
    travel_by        ENUM ('bus','airplane')                NOT NULL,
    date_start_sec   LONG                                   NOT NULL,
    quantity_of_days SMALLINT(3) UNSIGNED                   NOT NULL,
    price            INT(5) UNSIGNED                        NOT NULL,
    quantity_tours   TINYINT(2) UNSIGNED                    NOT NULL,
    description      TEXT                                   NOT NULL,
    image_path       VARCHAR(64)                            NOT NULL DEFAULT 'img/tours/default.jpg'
);

INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('rest', 'Belarus', 'Zvezda', '3', 'bus', 28944000, 14, 950, 17, 'Healthy rest in sanatorium',
        'img/tours/default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('excursion', 'Belarus', 'Nyasvizh', '4', 'bus', 27648000, 7, 450, 30, 'Medieval beautiful history',
        'img/tours/default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('shopping', 'Belarus', 'Cosmos', 'hostel', 'bus', 28944000, 2, 300, 10, 'Shopping tour',
        'img/tours/default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('rest', 'Chile', 'Remota', '5', 'airplane', 27648000, 16, 4500, 21, 'Rest and extremes',
        'img/tours/default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('excursion', 'Chile', 'Takarua Lodge', 'hostel', 'airplane', 28944000, 10, 3200, 14, 'Far away history',
        'img/tours/default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('shopping', 'Chile', 'UGO Hotel', '4', 'airplane', 27648000, 6, 2700, 7, 'Shopping tour in the capital',
        'img/tours/default.jpg');

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

/*one user - many passports*/
create table customers_passport
(
    id_customer_passport         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_user_fk                   INT UNSIGNED            NOT NULL,
    surname                      VARCHAR(32)             NOT NULL,
    name                         VARCHAR(32)             NOT NULL,
    date_of_birth_days           LONG                    NOT NULL,
    country                      VARCHAR(32)             NOT NULL,
    sex                          ENUM ('male', 'female') NOT NULL,
    identification_number        VARCHAR(14)             NOT NULL,
    passport_series              VARCHAR(2)              NOT NULL,
    passport_number              INT(7)                  NOT NULL,
    passport_date_of_issue_days  LONG                    NOT NULL,
    passport_date_of_expiry_days LONG                    NOT NULL,
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user)
);

INSERT INTO customers_passport (id_user_fk, surname, name, date_of_birth_days, country, sex,
                                identification_number, passport_series, passport_number, passport_date_of_issue_days,
                                passport_date_of_expiry_days)
VALUES (3, 'Ivanov', 'Ivan', 727021, 'Belarus', 'male', 'HE8975899PB683', 'PM', 7856789, 738021, 747021);

INSERT INTO customers_passport (id_user_fk, surname, name, date_of_birth_days, country, sex,
                                identification_number, passport_series, passport_number, passport_date_of_issue_days,
                                passport_date_of_expiry_days)
VALUES (3, 'Ivanova', 'Olga', 728021, 'Belarus', 'female', 'LG1564689JK898', 'MD', 3452092, 738421, 747421);

INSERT INTO customers_passport (id_user_fk, surname, name, date_of_birth_days, country, sex,
                                identification_number, passport_series, passport_number, passport_date_of_issue_days,
                                passport_date_of_expiry_days)
VALUES (3, 'Ivanov', 'Igor', 733021, 'BLR', 'male', 'IK0009998IE543', 'RR', 2342839, 739021, 749021);

INSERT INTO customers_passport (id_user_fk, surname, name, date_of_birth_days, country, sex,
                                identification_number, passport_series, passport_number, passport_date_of_issue_days,
                                passport_date_of_expiry_days)
VALUES (4, 'Merier', 'Luis', 717021, 'France', 'male', 'JU84849493030', 'FV', 8478922, 736021, 745021);

INSERT INTO customers_passport (id_user_fk, surname, name, date_of_birth_days, country, sex,
                                identification_number, passport_series, passport_number, passport_date_of_issue_days,
                                passport_date_of_expiry_days)
VALUES (4, 'Buijuer', 'Anette', 727021, 'Belgium', 'female', 'EW879854612323', 'BS', 2235681, 737021, 747021);

INSERT INTO customers_passport (id_user_fk, surname, name, date_of_birth_days, country, sex,
                                identification_number, passport_series, passport_number, passport_date_of_issue_days,
                                passport_date_of_expiry_days)
VALUES (5, 'Norris', 'Chuck', 707021, 'USA', 'male', 'US999988887766', 'JC', 8899776, 734021, 744021);

/*many passport - many tour*/
create table customers_travel_documents
(
    id_travel_documents     INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_customer_passport_fk INT UNSIGNED NOT NULL,
    id_tour_fk              INT UNSIGNED NOT NULL,
    customer_visa           BOOLEAN      NOT NULL DEFAULT false,
    tourist_voucher         VARCHAR(64),
    insurance_policy        VARCHAR(64),
    ticket_flight           VARCHAR(64),
    ticket_bus              VARCHAR(64),
    FOREIGN KEY (id_customer_passport_fk) REFERENCES customers_passport (id_customer_passport),
    FOREIGN KEY (id_tour_fk) REFERENCES tours (id_tour)
);

INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (1, 1, false, 'Generated Vaucher11', null, null, 'bus ticket Number11');
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (2, 1, false, 'Generated Vaucher11', null, null, 'bus ticket Number22');
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (3, 1, false, 'Generated Vaucher11', null, null, 'bus ticket Number33');
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (1, 5, true, 'Generated Vaucher22', 'Policy Number1', 'flight ticket Number11', null);
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (2, 5, true, 'Generated Vaucher22', 'Policy Number2', 'flight ticket Number22', null);
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (3, 5, true, 'Generated Vaucher22', 'Policy Number3', 'flight ticket Number33', null);

INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (4, 2, true, 'Generated Vaucher33', 'Policy Number4', null, 'bus ticket Number44');
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (5, 2, true, 'Generated Vaucher33', 'Policy Number5', null, 'bus ticket Number55');
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (4, 4, true, 'Generated Vaucher44', 'Policy Number6', 'flight ticket Number44', null);
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (5, 4, true, 'Generated Vaucher55', 'Policy Number7', 'flight ticket Number55', null);

INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (6, 3, true, 'Generated Vaucher66', 'Policy Number8', 'flight ticket Number66', null);
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (6, 6, false, 'Generated Vaucher77', 'Policy Number9', 'flight ticket Number77', null);
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (6, 1, true, 'Generated Vaucher88', 'Policy Number10', 'flight ticket Number88', null);
INSERT INTO customers_travel_documents (id_customer_passport_fk, id_tour_fk, customer_visa, tourist_voucher,
                                        insurance_policy, ticket_flight, ticket_bus)
VALUES (6, 5, false, 'Generated Vaucher99', 'Policy Number11', 'flight ticket Number99', null);

/*one user - one customers_sheet*/
create table customers_sheet
(
    id_customer_sheet INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_user_fk        INT UNSIGNED UNIQUE NOT NULL,
    sheet_sum         INT(7) UNSIGNED     NOT NULL DEFAULT 0,
    customer_discount ENUM ('0','5','7','10','15') DEFAULT '0',
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user)
);

INSERT INTO customers_sheet(id_user_fk, sheet_sum, customer_discount)
VALUES (3, 500000, '10');
INSERT INTO customers_sheet(id_user_fk, sheet_sum, customer_discount)
VALUES (4, 1500000, '0');
INSERT INTO customers_sheet(id_user_fk, sheet_sum, customer_discount)
VALUES (5, 50000000, '15');

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

/*many user - many tours*/
create table orders
(
    id_order       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_user_fk     INT UNSIGNED NOT NULL,
    id_tour_fk     INT UNSIGNED NOT NULL,
    date_order_sec LONG         NOT NULL,
    quantity       INT UNSIGNED NOT NULL,
    tour_paid      BOOLEAN      NOT NULL DEFAULT false,
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user),
    FOREIGN KEY (id_tour_fk) REFERENCES tours (id_tour)
);

INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (3, 1, 320787, 3, true);
INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (3, 5, 340787, 3, true);
INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (3, 3, 340787, 3, false);

INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (4, 2, 330500, 2, true);
INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (4, 4, 335500, 2, true);
INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (4, 6, 340500, 2, false);

INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (5, 3, 320000, 1, true);
INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (5, 6, 325000, 1, true);
INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (5, 1, 330000, 1, true);
INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (5, 5, 335000, 1, true);
INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (5, 2, 340000, 1, false);
INSERT INTO orders (id_user_fk, id_tour_fk, date_order_sec, quantity, tour_paid)
VALUES (5, 4, 345000, 1, false);

select * from users;
select * from staff;
select * from customers_sheet;
select * from customers_passport;
select * from customers_travel_documents;
select * from orders;
select * from tours;
select * from tours_hot;
