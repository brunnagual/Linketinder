CREATE TABLE "candidatos" (
  "id" int PRIMARY KEY,
  "nome" varchar(50) NOT NULL,
  "sobrenome" varchar(50) NOT NULL,
  "email" varchar(50) NOT NULL,
  "cep" varchar(9) NOT NULL,
  "cpf" VARCHAR (14) NOT NULL,
  "pais" varchar (20) NOT NULL,
  "descricao" varchar(500) NOT NULL,
  "senha" varchar (50) NOT NULL
);

CREATE TABLE "competencias" (
  "id" int PRIMARY KEY,
  "nome" varchar(50) NOT NULL
);

CREATE TABLE "candidatos_competencias" (
  "id" int PRIMARY KEY,
  "id_candidatos" int NOT NULL,
  "id_competencias" int NOT NULL
);

CREATE TABLE "empresas" (
  "id" int PRIMARY KEY,
  "nome" varchar(50) NOT NULL,
  "email" varchar(50) NOT NULL,
  "cep" varchar(9) NOT NULL,
  "cnpj" VARCHAR(18) NOT NULL,
  "pais" varchar(20) NOT NULL,
  "descricao" varchar(500) NOT NULL,
  "senha" varchar NOT NULL,
  "vagas" int NOT NULL
);

CREATE TABLE "empresas_competencias" (
  "id" int PRIMARY KEY,
  "id_empresas" int NOT NULL,
  "id_competencias" int NOT NULL
);

CREATE TABLE "vagas" (
  "id" int PRIMARY KEY,
  "nome" varchar(100) NOT NULL,
  "descricao" varchar(500) NOT NULL,
  "salario" int NOT NULL,
  "id_competencias" int NOT NULL
);

CREATE TABLE "matches" (
  "id" int PRIMARY KEY,
  "id_candidatos" int NOT NULL,
  "id_empresas" int NOT NULL,
  "id_vagas" int NOT NULL
);

ALTER TABLE "candidatos_competencias" ADD FOREIGN KEY ("id_candidatos") REFERENCES "candidatos" ("id");

ALTER TABLE "candidatos_competencias" ADD FOREIGN KEY ("id_competencias") REFERENCES "competencias" ("id");

ALTER TABLE "empresas_competencias" ADD FOREIGN KEY ("id_empresas") REFERENCES "empresas" ("id");

ALTER TABLE "empresas_competencias" ADD FOREIGN KEY ("id_competencias") REFERENCES "competencias" ("id");

ALTER TABLE "vagas" ADD FOREIGN KEY ("id_competencias") REFERENCES "competencias" ("id");

ALTER TABLE "matches" ADD FOREIGN KEY ("id_candidatos") REFERENCES "candidatos" ("id");

ALTER TABLE "matches" ADD FOREIGN KEY ("id_empresas") REFERENCES "empresas" ("id");

ALTER TABLE "matches" ADD FOREIGN KEY ("id_vagas") REFERENCES "vagas" ("id");
