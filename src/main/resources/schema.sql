DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE public.users
(
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(100) NOT NULL,
  last_name   VARCHAR(100) NOT NULL,
  email       VARCHAR(70) NOT NULL,
  password    VARCHAR(255) NOT NULL,
  parent_id   INTEGER NULL,
  role        VARCHAR(25) NOT NULL DEFAULT 'ROLE_USER',
  enabled     BOOLEAN NOT NULL DEFAULT TRUE,
  registered  DATE NOT NULL DEFAULT now(),
  modified    DATE NOT NULL DEFAULT now(),
  FOREIGN KEY (parent_id) REFERENCES public.users(id)
);
CREATE UNIQUE INDEX user_uidx_email ON users(email);
CREATE UNIQUE INDEX user_uidx_parent ON users(parent_id);