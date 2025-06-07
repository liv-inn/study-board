CREATE TABLE materias (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL
);

CREATE TABLE estudos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    data DATE NOT NULL,
    assunto TEXT NOT NULL,
    duracao INTEGER NOT NULL, 
    materia_id INTEGER NOT NULL,
    FOREIGN KEY (materia_id) REFERENCES materias(id)
);

CREATE TABLE anotacoes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    texto TEXT NOT NULL,
    estudo_id INTEGER NOT NULL,
    FOREIGN KEY (estudo_id) REFERENCES estudos(id)
);