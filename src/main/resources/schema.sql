CREATE TABLE if not exists categories (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE if not exists products (
    id INTEGER NOT NULL AUTO_INCREMENT,
    id_category INTEGER,
    name VARCHAR (25),
    description VARCHAR (100),
    price FLOAT NOT NULL,
    weight FLOAT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_category) REFERENCES categories(id)


);


