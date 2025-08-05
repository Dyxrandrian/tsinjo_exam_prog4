CREATE TABLE donor (
                       id SERIAL PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE payment (
                         id SERIAL PRIMARY KEY,
                         payment_method VARCHAR(50) NOT NULL,
                         reference VARCHAR(255) NOT NULL,
                         amount NUMERIC(10,2) NOT NULL,
                         payment_date TIMESTAMP NOT NULL
);

CREATE TABLE donation (
                          id SERIAL PRIMARY KEY,
                          donor_id INTEGER NOT NULL REFERENCES donor(id),
                          payment_id INTEGER NOT NULL UNIQUE REFERENCES payment(id)
);

CREATE TABLE aid (
                     id SERIAL PRIMARY KEY,
                     amount NUMERIC(10,2) NOT NULL,
                     purpose VARCHAR(255) NOT NULL,
                     aid_date TIMESTAMP NOT NULL
);
