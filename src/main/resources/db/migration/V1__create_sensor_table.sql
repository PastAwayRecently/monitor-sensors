CREATE TABLE sensors
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(30) NOT NULL,
    model       VARCHAR(15) NOT NULL,
    range_from  INTEGER NOT NULL,
    range_to    INTEGER NOT NULL,
    type        VARCHAR(50) NOT NULL,
    unit        VARCHAR(50),
    location    VARCHAR(40),
    description VARCHAR(200)
);