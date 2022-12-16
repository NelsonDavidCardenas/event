CREATE TABLE IF NOT EXISTS event(
    id SERIAL NOT NULL,
    description VARCHAR (100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_attendees INT NOT NULL,
    city VARCHAR (100) NOT NULL,
    PRIMARY KEY (id)
)