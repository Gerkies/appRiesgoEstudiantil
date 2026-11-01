DROP DATABASE IF EXISTS db_seguimiento_escolar;
CREATE DATABASE db_seguimiento_escolar CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE db_seguimiento_escolar;

-- 1. TABLA: centro_educativo
CREATE TABLE centro_educativo (
idCentro INT AUTO_INCREMENT PRIMARY KEY,
codigoInstitucional VARCHAR(30) NOT NULL UNIQUE,
nombreCentro VARCHAR(120) NOT NULL,
direccion VARCHAR(150) NOT NULL,
telefono VARCHAR(20) NOT NULL,
correo VARCHAR(80) NOT NULL,
nombreDirector VARCHAR(100) NOT NULL,
nivelesAtendidos VARCHAR(150) NOT NULL,
logotipo VARCHAR(255) NULL,
anoVigente INT NOT NULL
) ENGINE=InnoDB;

-- 2. TABLA: usuario
CREATE TABLE usuario (
idUsuario INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50) NOT NULL UNIQUE,
clave VARCHAR(100) NOT NULL,
nombreCompleto VARCHAR(120) NOT NULL,
rol VARCHAR(30) NOT NULL, -- Administrador, Docente, Orientador, Coordinador academico, Director
correo VARCHAR(80) NOT NULL,
telefono VARCHAR(20) NULL,
estado VARCHAR(20) NOT NULL DEFAULT 'Activo'
) ENGINE=InnoDB;

-- 3. TABLA: grupo (Estructura academica)
CREATE TABLE grupo (
idGrupo INT AUTO_INCREMENT PRIMARY KEY,
codigoGrupo VARCHAR(30) NOT NULL UNIQUE,
anoAcademico INT NOT NULL,
nivel VARCHAR(50) NOT NULL,
grado VARCHAR(50) NOT NULL,
carrera VARCHAR(100) NULL,
seccion VARCHAR(10) NOT NULL,
jornada VARCHAR(20) NOT NULL,
idDocenteEnc INT NOT NULL, -- FK hacia usuario
cantidadEstudiantes INT NOT NULL DEFAULT 0,
estado VARCHAR(20) NOT NULL DEFAULT 'Activo',
observaciones VARCHAR(255) NULL,
CONSTRAINT fk_grupo_docente FOREIGN KEY (idDocenteEnc) REFERENCES usuario(idUsuario)
) ENGINE=InnoDB;

-- 4. TABLA: estudiante
CREATE TABLE estudiante (
idEstudiante INT AUTO_INCREMENT PRIMARY KEY,
carne VARCHAR(30) NOT NULL UNIQUE,
nombres VARCHAR(60) NOT NULL,
apellidos VARCHAR(60) NOT NULL,
fechaNacimiento DATE NOT NULL,
sexo VARCHAR(15) NOT NULL,
direccion VARCHAR(150) NOT NULL,
comunidad VARCHAR(80) NOT NULL,
telefono VARCHAR(20) NULL,
fechaInscripcion DATE NOT NULL,
idGrupo INT NOT NULL,
estadoAcademico VARCHAR(30) NOT NULL DEFAULT 'Activo', -- Activo, En seguimiento, Trasladado, Retirado, Graduado, Inactivo
fotografia VARCHAR(255) NULL,
observaciones VARCHAR(255) NULL,
CONSTRAINT fk_estudiante_grupo FOREIGN KEY (idGrupo) REFERENCES grupo(idGrupo)
) ENGINE=InnoDB;

-- 5. TABLA: responsable
CREATE TABLE responsable (
idResponsable INT AUTO_INCREMENT PRIMARY KEY,
codigoResponsable VARCHAR(30) NOT NULL UNIQUE,
dpi VARCHAR(25) NOT NULL UNIQUE,
nombreCompleto VARCHAR(120) NOT NULL,
parentesco VARCHAR(30) NOT NULL,
direccion VARCHAR(150) NOT NULL,
telefono VARCHAR(20) NOT NULL,
correo VARCHAR(80) NULL,
ocupacion VARCHAR(80) NULL,
observaciones VARCHAR(255) NULL
) ENGINE=InnoDB;

-- 6. TABLA: estudiante_responsable (Descomposicion de relacion N:M)
CREATE TABLE estudiante_responsable (
idEstResp INT AUTO_INCREMENT PRIMARY KEY,
idEstudiante INT NOT NULL,
idResponsable INT NOT NULL,
esPrincipal VARCHAR(5) NOT NULL DEFAULT 'SI',
CONSTRAINT fk_er_estudiante FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante),
CONSTRAINT fk_er_responsable FOREIGN KEY (idResponsable) REFERENCES responsable(idResponsable),
CONSTRAINT uq_estudiante_responsable UNIQUE (idEstudiante, idResponsable)
) ENGINE=InnoDB;

-- 7. TABLA: curso
CREATE TABLE curso (
idCurso INT AUTO_INCREMENT PRIMARY KEY,
codigoCurso VARCHAR(30) NOT NULL UNIQUE,
nombreCurso VARCHAR(100) NOT NULL,
area VARCHAR(50) NOT NULL,
estado VARCHAR(20) NOT NULL DEFAULT 'Activo'
) ENGINE=InnoDB;

-- 8. TABLA: asignacion_docente_curso
CREATE TABLE asignacion_docente_curso (
idAsignacion INT AUTO_INCREMENT PRIMARY KEY,
idUsuario INT NOT NULL,
idCurso INT NOT NULL,
idGrupo INT NOT NULL,
anoAcademico INT NOT NULL,
fechaInicio DATE NOT NULL,
fechaFin DATE NOT NULL,
estado VARCHAR(20) NOT NULL DEFAULT 'Activo',
CONSTRAINT fk_adc_usuario FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario),
CONSTRAINT fk_adc_curso FOREIGN KEY (idCurso) REFERENCES curso(idCurso),
CONSTRAINT fk_adc_grupo FOREIGN KEY (idGrupo) REFERENCES grupo(idGrupo)
) ENGINE=InnoDB;

-- 9. TABLA: asistencia
CREATE TABLE asistencia (
idAsistencia INT AUTO_INCREMENT PRIMARY KEY,
fecha DATE NOT NULL,
idCurso INT NOT NULL,
idGrupo INT NOT NULL,
idEstudiante INT NOT NULL,
idUsuarioDocente INT NOT NULL,

estadoAsistencia VARCHAR(30) NOT NULL, -- Presente, Ausente, Ausencia justificada, Llegada tarde, Retiro anticipado
motivo VARCHAR(150) NULL,
observaciones VARCHAR(255) NULL,
CONSTRAINT fk_asistencia_curso FOREIGN KEY (idCurso) REFERENCES curso(idCurso),
CONSTRAINT fk_asistencia_grupo FOREIGN KEY (idGrupo) REFERENCES grupo(idGrupo),
CONSTRAINT fk_asistencia_estudiante FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante),
CONSTRAINT fk_asistencia_docente FOREIGN KEY (idUsuarioDocente) REFERENCES usuario(idUsuario),
CONSTRAINT uq_asistencia_estudiante_curso_fecha UNIQUE (idEstudiante, idCurso, fecha)
) ENGINE=InnoDB;

-- 10. TABLA: justificacion_ausencia
CREATE TABLE justificacion_ausencia (
idJustificacion INT AUTO_INCREMENT PRIMARY KEY,
idEstudiante INT NOT NULL,
idResponsable INT NOT NULL,
idUsuarioAutoriza INT NOT NULL,
fechaAusencia DATE NOT NULL,
motivo VARCHAR(200) NOT NULL,
fechaPresentacion DATE NOT NULL,
documentoAdjunto VARCHAR(255) NULL,
observaciones VARCHAR(255) NULL,
CONSTRAINT fk_just_estudiante FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante),
CONSTRAINT fk_just_responsable FOREIGN KEY (idResponsable) REFERENCES responsable(idResponsable),
CONSTRAINT fk_just_usuario FOREIGN KEY (idUsuarioAutoriza) REFERENCES usuario(idUsuario)
) ENGINE=InnoDB;

-- 11. TABLA: rendimiento_academico (Calificaciones)
CREATE TABLE rendimiento_academico (
idRendimiento INT AUTO_INCREMENT PRIMARY KEY,
idEstudiante INT NOT NULL,
idCurso INT NOT NULL,
idUsuarioDocente INT NOT NULL,
periodo VARCHAR(30) NOT NULL,
notaObtenida DOUBLE NOT NULL,
notaMinima DOUBLE NOT NULL DEFAULT 60.0,
fecha DATE NOT NULL,
observaciones VARCHAR(255) NULL,
CONSTRAINT fk_rend_estudiante FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante),
CONSTRAINT fk_rend_curso FOREIGN KEY (idCurso) REFERENCES curso(idCurso),
CONSTRAINT fk_rend_docente FOREIGN KEY (idUsuarioDocente) REFERENCES usuario(idUsuario)
) ENGINE=InnoDB;

-- 12. TABLA: configuracion_alerta
CREATE TABLE configuracion_alerta (
idConfiguracion INT AUTO_INCREMENT PRIMARY KEY,
nombreParametro VARCHAR(60) NOT NULL UNIQUE,
valorLimite DOUBLE NOT NULL,
descripcion VARCHAR(150) NOT NULL
) ENGINE=InnoDB;

-- 13. TABLA: alerta
CREATE TABLE alerta (
idAlerta INT AUTO_INCREMENT PRIMARY KEY,
numeroAlerta VARCHAR(30) NOT NULL UNIQUE,
idEstudiante INT NOT NULL,
idUsuarioResp INT NOT NULL,
fecha DATE NOT NULL,
tipoAlerta VARCHAR(50) NOT NULL,
motivo VARCHAR(200) NOT NULL,
prioridad VARCHAR(20) NOT NULL,
origenInformacion VARCHAR(200) NOT NULL,
estado VARCHAR(30) NOT NULL DEFAULT 'Pendiente de revision',
observaciones VARCHAR(255) NULL,
CONSTRAINT fk_alerta_estudiante FOREIGN KEY (idEstudiante) REFERENCES estudiante (idEstudiante),
CONSTRAINT fk_alerta_usuario FOREIGN KEY (idUsuarioResp) REFERENCES usuario (idUsuario)
)  ENGINE=INNODB;

-- 14. TABLA: factor_riesgo (Datos sensibles confidenciales)
CREATE TABLE factor_riesgo (
idFactor INT AUTO_INCREMENT PRIMARY KEY,
idEstudiante INT NOT NULL,
tipoFactor VARCHAR(60) NOT NULL, -- Economico, Transporte, Trabajo infantil, Salud, Familiar, Acoso
descripcion VARCHAR(255) NOT NULL,
severidad VARCHAR(20) NOT NULL,
fechaRegistro DATE NOT NULL,
confidencial VARCHAR(5) NOT NULL DEFAULT 'SI',
CONSTRAINT fk_factor_estudiante FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante)
) ENGINE=InnoDB;

-- 15. TABLA: plan_seguimiento
CREATE TABLE plan_seguimiento (
idPlan INT AUTO_INCREMENT PRIMARY KEY,
idEstudiante INT NOT NULL,
idAlerta INT NOT NULL,
idCoordinador INT NOT NULL,
situacionIdentificada VARCHAR(255) NOT NULL,
objetivos VARCHAR(255) NOT NULL,
accionesPrevistas VARCHAR(255) NOT NULL,
fechaInicio DATE NOT NULL,
fechaRevision DATE NOT NULL,
estado VARCHAR(30) NOT NULL DEFAULT 'En ejecucion',
observaciones VARCHAR(255) NULL,
CONSTRAINT fk_plan_estudiante FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante),
CONSTRAINT fk_plan_alerta FOREIGN KEY (idAlerta) REFERENCES alerta(idAlerta),
CONSTRAINT fk_plan_coordinador FOREIGN KEY (idCoordinador) REFERENCES usuario(idUsuario)
) ENGINE=InnoDB;

-- 16. TABLA: accion_apoyo (Acciones dentro de un plan)
CREATE TABLE accion_apoyo (
idAccion INT AUTO_INCREMENT PRIMARY KEY,
idPlan INT NOT NULL,
idUsuarioResp INT NOT NULL,
fecha DATE NOT NULL,
tipoAccion VARCHAR(60) NOT NULL, -- Entrevista, Tutoria, Visita, Apoyo materiales, Orientacion
descripcion VARCHAR(255) NOT NULL,
resultado VARCHAR(255) NOT NULL,
compromisos VARCHAR(255) NOT NULL,
proximaFecha DATE NULL,
evidencia VARCHAR(255) NULL,
CONSTRAINT fk_accion_plan FOREIGN KEY (idPlan) REFERENCES plan_seguimiento(idPlan),
CONSTRAINT fk_accion_usuario FOREIGN KEY (idUsuarioResp) REFERENCES usuario(idUsuario)
) ENGINE=InnoDB;

-- 17. TABLA: reunion_responsable (Actas de reunion con responsables)
CREATE TABLE reunion_responsable (
idReunion INT AUTO_INCREMENT PRIMARY KEY,
idEstudiante INT NOT NULL,
idResponsable INT NOT NULL,
idUsuarioConvoca INT NOT NULL,
fechaHora DATETIME NOT NULL,
motivo VARCHAR(200) NOT NULL,
participantes VARCHAR(200) NOT NULL,
situacionPresentada VARCHAR(255) NOT NULL,
acuerdos VARCHAR(255) NOT NULL,
responsablesAcuerdos VARCHAR(150) NOT NULL,
fechaRevision DATE NOT NULL,
estadoAcuerdos VARCHAR(20) NOT NULL DEFAULT 'Pendiente', -- Pendiente, Cumplido, Incumplido
observaciones VARCHAR(255) NULL,
CONSTRAINT fk_reunion_estudiante FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante),
CONSTRAINT fk_reunion_responsable FOREIGN KEY (idResponsable) REFERENCES responsable(idResponsable),
CONSTRAINT fk_reunion_usuario FOREIGN KEY (idUsuarioConvoca) REFERENCES usuario(idUsuario)
) ENGINE=InnoDB;

-- 18. TABLA: visita_domiciliaria (Visitas de campo)
CREATE TABLE visita_domiciliaria (
idVisita INT AUTO_INCREMENT PRIMARY KEY,
idEstudiante INT NOT NULL,
idVisitador INT NOT NULL,
familiaVisitada VARCHAR(100) NOT NULL,
fechaHora DATETIME NOT NULL,
motivo VARCHAR(200) NOT NULL,
situacionEncontrada VARCHAR(255) NOT NULL,
accionesRealizadas VARCHAR(255) NOT NULL,
compromisos VARCHAR(255) NOT NULL,
proximaVisita DATE NULL,
observaciones VARCHAR(255) NULL,
CONSTRAINT fk_visita_estudiante FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante),
CONSTRAINT fk_visita_usuario FOREIGN KEY (idVisitador) REFERENCES usuario(idUsuario)
) ENGINE=InnoDB;

-- 19. TABLA: cierre_seguimiento
CREATE TABLE cierre_seguimiento (
idCierre INT AUTO_INCREMENT PRIMARY KEY,
idPlan INT NOT NULL,
idUsuarioCierra INT NOT NULL,
fechaCierre DATE NOT NULL,
motivoCierre VARCHAR(100) NOT NULL, -- Mejora asistencia, Mejora rendimiento, Acuerdos cumplidos, Traslado, Retiro, Descarte
resultado VARCHAR(255) NOT NULL,
observaciones VARCHAR(255) NULL,
CONSTRAINT fk_cierre_plan FOREIGN KEY (idPlan) REFERENCES plan_seguimiento(idPlan),
CONSTRAINT fk_cierre_usuario FOREIGN KEY (idUsuarioCierra) REFERENCES usuario(idUsuario)
) ENGINE=InnoDB;