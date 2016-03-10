CREATE TABLE ingredienti
(
    id INT(11) PRIMARY KEY NOT NULL,
    nome VARCHAR(50) NOT NULL,
    kcal FLOAT NOT NULL,
    foto VARCHAR(200)
);
CREATE TABLE ricetta
(
    id_ricetta INT(11) PRIMARY KEY NOT NULL,
    nome VARCHAR(200) NOT NULL,
    foto VARCHAR(200)
);
CREATE TABLE ingredienti_ricetta
(
    id_ricetta INT(11) NOT NULL,
    id_ingrediente INT(11) NOT NULL,
    quantita FLOAT NOT NULL,
    um VARCHAR(5) NOT NULL,
    CONSTRAINT ingredienti_ricetta_ingredienti_id_fk FOREIGN KEY (id_ingrediente) REFERENCES ingredienti (id),
    CONSTRAINT ingredienti_ricetta_ricetta_id_ricetta_fk FOREIGN KEY (id_ricetta) REFERENCES ricetta (id_ricetta)
);
CREATE UNIQUE INDEX ingredienti_ricetta_id_ricetta_id_ingrediente_pk ON ingredienti_ricetta (id_ricetta, id_ingrediente);
CREATE INDEX ingredienti_ricetta_ingredienti_id_fk ON ingredienti_ricetta (id_ingrediente);
CREATE TABLE utente
(
    id_utente INT(11) PRIMARY KEY NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cognome VARCHAR(100) NOT NULL,
    phone VARCHAR(100),
    email VARCHAR(100)
);
CREATE TABLE mangia
(
    id INT(11) PRIMARY KEY NOT NULL,
    id_ricetta INT(11) NOT NULL,
    timestamp TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
    CONSTRAINT mangia_ricetta_id_ricetta_fk FOREIGN KEY (id) REFERENCES ricetta (id_ricetta)
);