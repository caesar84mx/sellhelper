DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS providers CASCADE;
DROP TABLE IF EXISTS goods CASCADE;
DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS addresses CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS order_items CASCADE;
DROP TABLE IF EXISTS stock_items CASCADE;
DROP SEQUENCE IF EXISTS hibernate_sequence;
DROP SEQUENCE IF EXISTS addresses_id_seq;
DROP SEQUENCE IF EXISTS clients_id_seq;
DROP SEQUENCE IF EXISTS goods_id_seq;
DROP SEQUENCE IF EXISTS orders_id_seq;
DROP SEQUENCE IF EXISTS providers_id_seq;
DROP SEQUENCE IF EXISTS stock_items_id_seq;
DROP SEQUENCE IF EXISTS users_id_seq;

CREATE SEQUENCE hibernate_sequence START 2;

CREATE TABLE users
(
  id          SERIAL PRIMARY KEY,
  name        VARCHAR NOT NULL,
  middle_name VARCHAR NOT NULL,
  last_name   VARCHAR NOT NULL,
  email       VARCHAR NOT NULL,
  password    VARCHAR NOT NULL,
  parent_id   INTEGER DEFAULT NULL,
  role        VARCHAR NOT NULL DEFAULT 'ROLE_USER',
  enabled     BOOLEAN NOT NULL DEFAULT TRUE,
  registered  DATE NOT NULL DEFAULT now(),
  modified    DATE NOT NULL DEFAULT now()
);
CREATE INDEX user_idx ON users(name, last_name, registered, modified, parent_id, role);
CREATE UNIQUE INDEX user_uidx ON users(email);

CREATE TABLE providers
(
  id          SERIAL PRIMARY KEY,
  name        VARCHAR NOT NULL,
  contacts    VARCHAR NOT NULL,
  user_id     INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE INDEX providers_idx_name ON providers(name, contacts);

CREATE TABLE goods
(
  id           SERIAL PRIMARY KEY,
  name         VARCHAR NOT NULL,
  serial_no    VARCHAR NOT NULL,
  description  VARCHAR NOT NULL,
  user_id      INTEGER NOT NULL,
  provider_id  INTEGER NOT NULL,
  price        INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (provider_id) REFERENCES providers(id)
);
CREATE INDEX goods_idx ON goods(name, serial_no, user_id, provider_id, price);

CREATE TABLE clients
(
  id            SERIAL PRIMARY KEY,
  name          VARCHAR NOT NULL,
  middle_name   VARCHAR NOT NULL,
  last_name     VARCHAR NOT NULL,
  contacts      VARCHAR NOT NULL,
  user_id       INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
CREATE INDEX clients_idx ON clients(name, last_name, user_id);

CREATE TABLE addresses
(
  id            SERIAL PRIMARY KEY,
  country       VARCHAR NOT NULL,
  region        VARCHAR NOT NULL,
  location      VARCHAR NOT NULL,
  details       VARCHAR NOT NULL,
  user_id       INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
CREATE INDEX addresses_idx ON addresses(country, region, location, details);

CREATE TABLE orders
(
  id            SERIAL PRIMARY KEY,
  client_id     INTEGER NOT NULL,
  address_id    INTEGER NOT NULL,
  user_id       INTEGER NOT NULL,
  created       TIMESTAMP NOT NULL DEFAULT now(),
  modified      TIMESTAMP NOT NULL DEFAULT now(),
  status        VARCHAR NOT NULL DEFAULT 'PENDING',
  FOREIGN KEY (client_id) REFERENCES clients(id),
  FOREIGN KEY (address_id) REFERENCES addresses(id),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
CREATE INDEX orders_idx ON orders(client_id, user_id, created, modified, status);

CREATE TABLE order_items
(
  order_id      INTEGER NOT NULL,
  good_id       INTEGER NOT NULL,
  quantity      INTEGER NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (good_id) REFERENCES goods(id)
);
CREATE INDEX order_items_idx ON order_items(order_id, good_id);

CREATE TABLE stock_items
(
  id            SERIAL PRIMARY KEY,
  good_id       INTEGER NOT NULL,
  quantity      INTEGER NOT NULL,
  user_id       INTEGER NOT NULL,
  FOREIGN KEY (good_id) REFERENCES goods(id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
CREATE INDEX stock_items_idx ON stock_items(id, good_id, user_id);