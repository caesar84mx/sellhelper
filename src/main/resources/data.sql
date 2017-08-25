-- password: "password."
INSERT INTO users (name, middle_name, last_name, email, password, parent_id, role)
VALUES ('Julius', 'Gaius', 'Caesar', 'caesar.84@mail.ru',
        '$2a$10$Tuv71UT5hKQIpaMkdEvZ9e8HVzrkd.WwSqV41jzZS32KhQS3Uvp4y',
        NULL, 'ROLE_ADMIN');

-- password: "passcode"
INSERT INTO users (name, middle_name, last_name, email, password, parent_id, role)
VALUES ('Marcus', 'Junius', 'Brutus', 'brutus.84@word.ir',
        '$2a$10$EkudTF0DANlCMzBpU/t7xuJ2TxfuuAQRyTlUJY7xobXOI4dtNhiqW',
        1, 'ROLE_USER');

INSERT INTO providers (name, contacts, user_id)
VALUES ('Рога & Копыта, Инк.', 'тел.: +7(988)000-00-00', 2);

INSERT INTO clients (name, middle_name, last_name, contacts, user_id)
VALUES ('Васисуалий', 'Модестович', 'Пупкин', 'skype: pupkin-vas-mod', 2);

INSERT INTO addresses (country, region, location, details, user_id)
VALUES ('Россия', 'Ставропольский край', 'г.Пятигорск', 'ул.Фучика 10, кв. 1', 2);

INSERT INTO goods (name, serial_no, description, user_id, provider_id, price)
VALUES ('Пятикрылый семихуй', '001234', 'Пятикрылый семихуй, сиреневый, сборный', 2, 1, 50000);

INSERT INTO stock_items (good_id, quantity, user_id)
VALUES (1, 10, 2);

INSERT INTO orders (client_id, address_id, user_id, created, modified)
VALUES (1, 1, 2, '2017-08-23 17:18:35.464353', '2017-08-23 17:18:35.464353');

INSERT INTO order_items (order_id, good_id, quantity)
VALUES (1, 1, 2);