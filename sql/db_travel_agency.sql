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
VALUES ('Hedgehog32',
        'SpbNOJ5Vz7s/LZ8VW1XDZSxqp67L13TIpirpU2nMbpY=$w+B/Ce9LnhnTGp0xkGwxY8Lir5qhah/TNJBL+HMwbIQ=',
        'Hedgehog32@mail.trag',
        'user', true, true);
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
VALUES ('Sprat32',
        'za73qZTPrMsHruPxFkULpP69GCsnNf2tdrRQL0Y9+Tk=$Vmipk/LHW45MQ5M+zIh0tfuFavohswZ5fVrZU59aUbA=',
        'Sprat32@mail.trag',
        'moderator', true, true);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Woodpecker0',
        'vOP3C1R+2BLhQxx1STf0S7UjniEMbwy9azMMdBCWVfU=$R7GKrwTTzhiGFvISqgMWvWZc+6OYfmozaamUzRWtxBg=',
        'Woodpecker0@mail.trag',
        'moderator', false, false);

create table country
(
    id_country     INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    country        VARCHAR(32) NOT NULL,
    visa_necessary BOOLEAN     NOT NULL,
    description    TEXT        NOT NULL
);

INSERT INTO country (country, visa_necessary, description)
VALUES ('Belarus', false,
        'Beautiful landscapes, medieval castles, sneaky-good art and cafe scene, and hospitable locals.');
INSERT INTO country (country, visa_necessary, description)
VALUES ('Chile', true,
        'Chile is nature on a colossal scale, but travel here is surprisingly easy if you don''t rush it.');


/*date starts from 01.01.2020: 30.11.2020 = 28944000 sec; 15.11.2020 = 27648000 sec*/
/*one country - many tours*/
create table tours
(
    id_tour          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_country_fk    INT UNSIGNED                           NOT NULL,
    tour_purpose     ENUM ('rest', 'excursion','shopping' ) NOT NULL,
    hotel_name       VARCHAR(32)                            NOT NULL,
    hotel_stars      ENUM ('hostel','3','4','5')            NOT NULL,
    travel_by        ENUM ('bus','airplane')                NOT NULL,
    date_start_sec   LONG                                   NOT NULL,
    quantity_of_days SMALLINT(3) UNSIGNED                   NOT NULL,
    price            INT(5) UNSIGNED                        NOT NULL,
    quantity_tours   TINYINT(2) UNSIGNED                    NOT NULL,
    description      TEXT                                   NOT NULL,
    image_path       VARCHAR(64)                            NOT NULL DEFAULT 'img/tours/default.jpg',
    FOREIGN KEY (id_country_fk) REFERENCES country (id_country)
);

INSERT INTO tours(id_country_fk, tour_purpose, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES (1, 'rest', 'Zvezda', '3', 'bus', 28944000, 14, 950, 17, 'Healthy rest in sanatorium', 'img/tours/default.jpg');
INSERT INTO tours(id_country_fk, tour_purpose, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES (1, 'excursion', 'Nyasvizh', '4', 'bus', 27648000, 7, 450, 30, 'Medieval beautiful history',
        'img/tours/default.jpg');
INSERT INTO tours(id_country_fk, tour_purpose, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES (1, 'shopping', 'Cosmos', 'hostel', 'bus', 28944000, 2, 300, 10, 'Shopping tour', 'img/tours/default.jpg');
INSERT INTO tours(id_country_fk, tour_purpose, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES (2, 'rest', 'Remota', '5', 'airplane', 27648000, 16, 4500, 21, 'Rest and extremes', 'img/tours/default.jpg');
INSERT INTO tours(id_country_fk, tour_purpose, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES (2, 'excursion', 'Takarua Lodge', 'hostel', 'airplane', 28944000, 10, 3200, 14, 'Far away history',
        'img/tours/default.jpg');
INSERT INTO tours(id_country_fk, tour_purpose, hotel_name, hotel_stars, travel_by, date_start_sec, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES (2, 'shopping', 'UGO Hotel', '4', 'airplane', 27648000, 6, 2700, 7, 'Shopping tour in the capital',
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
    id_customer_passport        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_user_fk                  INT UNSIGNED            NOT NULL,
    surname                     VARCHAR(32)             NOT NULL,
    name                        VARCHAR(32)             NOT NULL,
    date_of_birth_sec           LONG                    NOT NULL,
    country                     VARCHAR(32)             NOT NULL,
    nationality                 VARCHAR(3)              NOT NULL,
    sex                         ENUM ('male', 'female') NOT NULL,
    identification_number       VARCHAR(14)             NOT NULL,
    passport_series             VARCHAR(2)              NOT NULL,
    passport_number             INT(7)                  NOT NULL,
    passport_date_of_issue_sec  LONG                    NOT NULL,
    passport_date_of_expiry_sec LONG                    NOT NULL,
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user)
);


/*one passport - many visas ? not needed
create table customers_visa
(
    id_customer_visa        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_customer_passport_fk INT UNSIGNED             NOT NULL,
    issuing_embassy         VARCHAR(32)              NOT NULL,
    valid_from              LONG                     NOT NULL,
    valid_until             LONG                     NOT NULL,
    number_of_entries       ENUM ('single', 'multi') NOT NULL,
    duration_of_stay        SMALLINT(3) UNSIGNED     NOT NULL,
    type_of_visa            VARCHAR(3)               NOT NULL,
    issued_in               VARCHAR(32)              NOT NULL,
    FOREIGN KEY (id_customer_passport_fk) REFERENCES customers_passport (id_customer_passport)
);*/


/*many passport - many tour*/
create table customers_travel_documents
(
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


/*one user - one customers_sheet*/
create table customers_sheet
(
    id_customer_sheet INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_user_fk        INT UNSIGNED UNIQUE NOT NULL,
    sheet_sum         INT(7) UNSIGNED     NOT NULL DEFAULT 0,
    customer_discount ENUM ('0','5','7','10','15') DEFAULT '0',
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user)
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


/*many user - many tours*/
create table orders
(
    id_user_fk     INT UNSIGNED NOT NULL,
    id_tour_fk     INT UNSIGNED NOT NULL,
    date_order_sec LONG         NOT NULL,
    quantity       INT UNSIGNED NOT NULL,
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user),
    FOREIGN KEY (id_tour_fk) REFERENCES tours (id_tour)
);

