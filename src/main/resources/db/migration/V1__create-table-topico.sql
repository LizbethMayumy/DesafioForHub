CREATE TABLE perfil (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correoElectronico VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    perfilId BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_usuario_perfilId FOREIGN KEY (perfilId) REFERENCES perfil(id)
);

CREATE TABLE curso (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    fechaCreacion DATETIME NOT NULL,
    status TINYINT NOT NULL,
    usuarioId BIGINT NOT NULL,
    cursoId BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topicos_usuarioId FOREIGN KEY (usuarioId) REFERENCES usuario(id),
    CONSTRAINT fk_topicos_cursoId FOREIGN KEY (cursoId) REFERENCES curso(id)
);

CREATE TABLE respuesta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(500) NOT NULL,
    topicoId BIGINT NOT NULL,
    fechaCreacion DATETIME NOT NULL,
    usuarioId BIGINT NOT NULL,
    solucion TINYINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_respuesta_topicoId FOREIGN KEY (topicoId) REFERENCES topico(id),
    CONSTRAINT fk_respuesta_usuarioId FOREIGN KEY (usuarioId) REFERENCES usuario(id)
);
