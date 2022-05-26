CREATE TABLE car
(
    id    SERIAL PRIMARY KEY,
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    price MONEY
);


CREATE TABLE people
(
    id     SERIAL PRIMARY KEY,
    name   TEXT NOT NULL,
    age    INTEGER,
    right  BOOLEAN DEFAULT "true",
    car_id SERIAL REFERENCES car (id)
);

