CREATE TABLE category (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product (
    id INTEGER NOT NULL AUTO_INCREMENT,
    id_category INTEGER,
    name VARCHAR (25),
    price FLOAT NOT NULL,
    weight FLOAT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_category) REFERENCES category(id)


);


