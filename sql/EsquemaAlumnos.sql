-- Alumnos definition

CREATE TABLE Alumnos (
  id int NOT NULL AUTO_INCREMENT,
  Nombre varchar(25) DEFAULT NULL,
  Apellido varchar(25) DEFAULT NULL,
  PRIMARY KEY (id)
)


-- Clases definition

CREATE TABLE Clases (
  id int NOT NULL AUTO_INCREMENT,
  curso int NOT NULL,
  letra varchar(1) NOT NULL,
  PRIMARY KEY (id)
)


-- Cursos definition

CREATE TABLE Cursos (
  id int NOT NULL AUTO_INCREMENT,
  anio_inicio int NOT NULL,
  anio_fin int DEFAULT NULL,
  PRIMARY KEY (id)
)


-- Profesores definition

CREATE TABLE Profesores (
  id int NOT NULL AUTO_INCREMENT,
  Nombre varchar(25) DEFAULT NULL,
  Apellido varchar(25) DEFAULT NULL,
  PRIMARY KEY (id)
)



-- Clases_Curso definition

CREATE TABLE Clases_Curso (
  id int NOT NULL AUTO_INCREMENT,
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



-- Alumnos_Clase_Curso definition

CREATE TABLE Alumnos_Clase_Curso (
  id_alumno int NOT NULL,
  id_clase_curso int NOT NULL,
  PRIMARY KEY (id_alumno,id_clase_curso),
  KEY Alumnos_Clase_Curso_FK_1 (id_clase_curso),
  CONSTRAINT Alumnos_Clase_Curso_FK FOREIGN KEY (id_alumno) REFERENCES Alumnos (id),
  CONSTRAINT Alumnos_Clase_Curso_FK_1 FOREIGN KEY (id_clase_curso) REFERENCES Clases_Curso (id)
)


INSERT INTO Clases (id, curso, letra) VALUES(1, 1, 'A');
INSERT INTO Clases (id, curso, letra) VALUES(2, 1, 'B');
INSERT INTO Clases (id, curso, letra) VALUES(3, 2, 'A');
INSERT INTO Clases (id, curso, letra) VALUES(4, 2, 'B');

INSERT INTO Cursos (id, anio_inicio, anio_fin) VALUES(1, 2019, 2020);
INSERT INTO Cursos (id, anio_inicio, anio_fin) VALUES(2, 2020, 2021);
INSERT INTO Cursos (id, anio_inicio, anio_fin) VALUES(3, 2021, 2022);

INSERT INTO Profesores (id, Nombre, Apellido) VALUES(1, 'Jorge', 'Suárez');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(2, 'Marcos', 'Costales');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(3, 'Joaquín', 'González');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(4, 'Ana', 'Rosa');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(5, 'Ganadería', 'Cellero');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(6, 'Juan', 'Vázquez');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(7, 'Pablo', 'Cadierno');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(8, 'Carlos', 'Fernández');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(9, 'Santiago', 'Llera');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(10, 'Andrés', 'Rodriguez');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(11, 'Tomás', 'Gonzalez');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(12, 'Vicente', 'Suárez');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(13, 'Javier', 'Fernández');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(14, 'Dorita', 'Blanco');
INSERT INTO Profesores (id, Nombre, Apellido) VALUES(15, 'Nicolás', 'Lozano');


INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(1, 'José Vicente', 'Álvarez');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(2, 'Roberto', 'Suárez');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(3, 'Rafael', 'Urgel');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(4, 'Felipe', 'García');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(5, 'Pilar', 'Lombán');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(6, 'Banesa', 'Del llano');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(7, 'Sonia', 'Martínez');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(8, 'Alejandro', 'Suárez');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(9, 'Ana', 'Amieva');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(10, 'Justa', 'Núñez');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(11, 'Mari', 'Nieves');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(12, 'Ismael', 'Diego');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(13, 'José', 'Manuel');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(14, 'Eduardo', 'Pérez');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(15, 'Noelia', 'Tuero');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(16, 'Emilio', 'Fernández');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(17, 'María', 'Del');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(18, 'Pablo', 'Alonso');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(19, 'Diego', 'Chacón');
INSERT INTO Alumnos (id, Nombre, Apellido) VALUES(20, 'Jairo', 'García');

INSERT INTO Clases_Curso (id, id_curso, id_clase, id_tutor) VALUES(1, 1, 1, 14);
INSERT INTO Clases_Curso (id, id_curso, id_clase, id_tutor) VALUES(2, 2, 3, 14);
INSERT INTO Clases_Curso (id, id_curso, id_clase, id_tutor) VALUES(3, 2, 1, 9);
INSERT INTO Clases_Curso (id, id_curso, id_clase, id_tutor) VALUES(4, 2, 2, 12);


INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(3, 1);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(5, 1);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(10, 1);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(11, 1);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(19, 1);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(3, 2);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(5, 2);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(10, 2);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(15, 2);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(16, 2);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(19, 2);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(4, 3);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(7, 3);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(11, 3);
INSERT INTO Alumnos_Clase_Curso (id_alumno, id_clase_curso) VALUES(20, 3);
