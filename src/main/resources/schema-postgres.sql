-- Corrigido para PostgreSQL
CREATE TABLE materias (
    id SERIAL PRIMARY KEY, 
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE estudos (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    assunto TEXT NOT NULL,
    duracao INTEGER NOT NULL,
    materia_id INTEGER NOT NULL,
    FOREIGN KEY (materia_id) REFERENCES materias(id)
);

CREATE TABLE anotacoes (  
    id SERIAL PRIMARY KEY,
    texto TEXT NOT NULL,
    estudo_id INTEGER NOT NULL,
    FOREIGN KEY (estudo_id) REFERENCES estudos(id)
);