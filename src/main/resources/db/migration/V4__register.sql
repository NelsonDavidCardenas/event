CREATE TABLE IF NOT EXISTS register(
    id SERIAL NOT NULL,
    member_id INT NOT NULL,
    conference_id INT NOT NULL,
    code VARCHAR(100) NOT NULL,
    registered_at  VARCHAR(100) NOT NULL,
    assited BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (conference_id) REFERENCES conference(id),
    UNIQUE (code)

)

