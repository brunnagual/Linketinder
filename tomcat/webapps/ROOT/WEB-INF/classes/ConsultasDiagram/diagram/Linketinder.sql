CREATE TABLE  candidatos  (
    id  serial PRIMARY KEY,
    nome  character varying(50) NOT NULL,
    sobrenome  character varying(50) NOT NULL,
    email  character varying(50) NOT NULL,
    cep   character varying(9),
    cpf   character varying (14) NOT NULL,
    pais  character varying (20),
    descricao  character varying(500) NOT NULL,
    senha  character varying (50)
);

CREATE TABLE  competencias  (
    id  serial PRIMARY KEY,
    nome  character varying(50) NOT NULL
);

CREATE TABLE  candidatos_competencias  (
    id  serial PRIMARY KEY,
    id_candidatos  int NOT NULL,
    id_competencias  int NOT NULL,
    FOREIGN KEY (id_candidatos) REFERENCES  candidatos(id),
    FOREIGN KEY (id_competencias) REFERENCES  competencias(id)
);

CREATE TABLE  empresas  (
    id  serial PRIMARY KEY,
    nome   character varying(50) NOT NULL,
    email   character varying(50) NOT NULL,
    cep   character varying(9),
    cnpj   character varying(18) NOT NULL,
    pais   character varying(20),
    descricao   character varying(500) NOT NULL,
    senha   character varying (100),
    vagas  int
);

CREATE TABLE  empresas_competencias  (
    id  serial PRIMARY KEY,
    id_empresas  int NOT NULL,
    id_competencias  int NOT NULL,
    FOREIGN KEY (id_empresas) REFERENCES empresas(id),
    FOREIGN KEY (id_competencias) REFERENCES  competencias(id)
);

CREATE TABLE  vagas  (
    id  serial PRIMARY KEY,
    nome   character varying(100) NOT NULL,
    descricao   character varying(500) NOT NULL,
    salario  decimal NOT NULL,
    id_empresa int,
    FOREIGN KEY (id_empresa) REFERENCES  empresas(id),
);

CREATE TABLE vagas_competencias (
    id serial PRIMARY KEY,
    id_vagas INT,
    id_competencias INT,
    FOREIGN KEY (id_vagas) REFERENCES vagas(id),
    FOREIGN KEY (id_competencias) REFERENCES competencias(id)
);

CREATE TABLE  curtidas  (
    id  serial PRIMARY KEY,
    id_candidatos  int NOT NULL,
    id_empresas  int NOT NULL,
    id_vagas  int NOT NULL,
    FOREIGN KEY (id_candidatos) REFERENCES  candidatos(id),
    FOREIGN KEY (id_empresas) REFERENCES  empresas(id),
    FOREIGN KEY (id_vagas) REFERENCES  vagas(id)
);