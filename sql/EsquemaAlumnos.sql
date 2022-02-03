-- test.Alumnos definition

CREATE TABLE Alumnos (
  id int NOT NULL,
  Nombre varchar(25) DEFAULT NULL,
  Apellido varchar(25) DEFAULT NULL,
  PRIMARY KEY (id)
)


-- test.Clases definition

CREATE TABLE Clases (
  id int NOT NULL,
  curso int NOT NULL,
  letra varchar(1) NOT NULL,
  PRIMARY KEY (id)
)


-- test.Cursos definition

CREATE TABLE Cursos (
  id int NOT NULL,
  anio_inicio int NOT NULL,
  anio_fin int DEFAULT NULL,
  PRIMARY KEY (id)
)


-- test.Profesores definition

CREATE TABLE Profesores (
  id int NOT NULL,
  Nombre varchar(25) DEFAULT NULL,
  Apellido varchar(25) DEFAULT NULL,
  PRIMARY KEY (id)
)



-- test.Clases_Curso definition

CREATE TABLE Clases_Curso (
  id int NOT NULL,
  id_curso int NOT NULL,
  id_clase int NOT NULL,
  id_tutor int NOT NULL,
  PRIMARY KEY (id),
  KEY Clases_Curso_FK (id_tutor),
  KEY Clases_Curso_FK_1 (id_curso),
  KEY Clases_Curso_FK_2 (id_clase),
  CONSTRAINT Clases_Curso_FK FOREIGN KEY (id_tutor) REFERENCES Profesores (id),
  CONSTRAINT Clases_Curso_FK_1 FOREIGN KEY (id_curso) REFERENCES Cursos (id),
  CONSTRAINT Clases_Curso_FK_2 FOREIGN KEY (id_clase) REFERENCES Clases (id)
)



-- test.Alumnos_Clase_Curso definition

CREATE TABLE Alumnos_Clase_Curso (
  id_alumno int NOT NULL,
  id_clase_curso int NOT NULL,
  PRIMARY KEY (id_alumno,id_clase_curso),
  KEY Alumnos_Clase_Curso_FK_1 (id_clase_curso),
  CONSTRAINT Alumnos_Clase_Curso_FK FOREIGN KEY (id_alumno) REFERENCES Alumnos (id),
  CONSTRAINT Alumnos_Clase_Curso_FK_1 FOREIGN KEY (id_clase_curso) REFERENCES Clases_Curso (id)
)