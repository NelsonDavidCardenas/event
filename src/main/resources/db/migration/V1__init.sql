CREATE TABLE IF NOT EXISTS member(
    id SERIAL,
    fullname VARCHAR (100) NOT NULL,
    email VARCHAR(100),
    age INT NOT NULL,
    PRIMARY KEY (id)
)