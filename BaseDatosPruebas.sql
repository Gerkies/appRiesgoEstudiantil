-- SCRIPT DE DATOS DE EJEMPLO PARA TODOS LOS MÓDULOS DEL SISTEMA

USE db_seguimiento_escolar;

-- 1. MÓDULO: GRUPOS ACADÉMICOS
INSERT INTO grupo (idGrupo, codigoGrupo, anoAcademico, nivel, grado, carrera, seccion, jornada, idDocenteEnc, cantidadEstudiantes) VALUES 
(1, 'G-4D-CS-A', 2026, 'Diversificado', '4to', 'Bachillerato en Computacion', 'A', 'Matutina', 2, 30),
(2, 'G-5D-CS-B', 2026, 'Diversificado', '5to', 'Bachillerato en Computacion', 'B', 'Vespertina', 3, 28),
(3, 'G-UMG-IS-3', 2026, 'Universidad', '3er Semestre', 'Ingeniería en Sistemas', 'A', 'Sabatina', 2, 25),
(4, 'G-UMG-IS-4', 2026, 'Universidad', '4to Semestre', 'Ingeniería en Sistemas', 'A', 'Plan Diario', 2, 30)
ON DUPLICATE KEY UPDATE codigoGrupo = VALUES(codigoGrupo);

-- 2. MÓDULO: ESTUDIANTES
INSERT INTO estudiante (idEstudiante, carne, nombres, apellidos, fechaNacimiento, sexo, direccion, comunidad, telefono, fechaInscripcion, idGrupo, estadoAcademico, fotografia, observaciones) VALUES
(1, '0903-23-11111', 'Cristian Alejandro', 'Gómez Bolaños', '2008-05-12', 'Masculino', '12 Calle 4-50 Zona 1', 'Guatemala', '5432-1098', '2026-01-10', 1, 'Activo', '', 'Estudiante regular'),
(4, '0903-25-55555', 'José Julián', 'Suram De León', '1994-10-11', 'Masculino', 'Zona 4, San Marcos', 'Ladino', '55551234', '2024-03-02', 2, 'Activo', '', 'Caso de seguimiento activo'),
(5, '0903-25-11001', 'Luis Gilmar', 'Estrada Chun', '2007-02-14', 'Masculino', 'Zona 2, San Pedro', 'San Marcos', '4444-9988', '2026-01-15', 1, 'Activo', '', 'Estudiante de reingreso')
ON DUPLICATE KEY UPDATE nombres = VALUES(nombres);

-- 3. MÓDULO: PADRES Y TUTORES
INSERT INTO responsable (idResponsable, codigoResponsable, dpi, nombreCompleto, parentesco, direccion, telefono, correo, ocupacion) VALUES
(1, 'RESP-001', '2589 12345 0101', 'Rosa Perez Garcia', 'Madre', '12 Calle 4-50 Zona 1', '5432-1098', 'rperez@gmail.com', 'Comerciante'),
(2, 'RESP-002', '2788 70897 1201', 'Gloria Nineth Fuentes', 'Abuela', 'Zona 4, San Marcos', '5432-1098', 'gfuentes@gmail.com', 'Ama de casa'),
(3, 'RESP-003', '2109 45678 1201', 'Carlos René Morales', 'Padre', 'Caserío El Recreo, San Marcos', '5123-9988', 'cmorales@gmail.com', 'Agricultor')
ON DUPLICATE KEY UPDATE nombreCompleto = VALUES(nombreCompleto);

-- Vínculo Estudiante - Responsable
INSERT INTO estudiante_responsable (idEstudiante, idResponsable, esPrincipal) VALUES
(1, 1, 'SI'),
(4, 1, 'SI'),
(5, 2, 'SI')
ON DUPLICATE KEY UPDATE esPrincipal = VALUES(esPrincipal);

-- 4. MÓDULO: ASISTENCIA DIARIA (Control de Clases y Faltas)
INSERT IGNORE INTO asistencia (fecha, idCurso, idGrupo, idEstudiante, estadoAsistencia, motivo, idUsuarioDocente) VALUES
('2026-10-26', 1, 1, 1, 'Presente', NULL, 2),
('2026-10-27', 1, 1, 1, 'Presente', NULL, 2),
('2026-10-28', 1, 1, 1, 'Presente', NULL, 2),
('2026-10-26', 1, 2, 4, 'Ausente', 'Problemas de transporte desde San Marcos', 2),
('2026-10-27', 1, 2, 4, 'Ausente', 'Quebrantos de salud', 2),
('2026-10-28', 1, 2, 4, 'Ausencia justificada', 'Constancia médica presentada por la madre', 2),
('2026-10-29', 1, 2, 4, 'Llegada tarde', 'Retraso en carretera interdepartamental', 2),
('2026-10-26', 1, 1, 5, 'Presente', NULL, 2),
('2026-10-27', 1, 1, 5, 'Presente', NULL, 2);

-- 5. MÓDULO: RENDIMIENTO ACADÉMICO
INSERT INTO rendimiento_academico (idEstudiante, idCurso, idUsuarioDocente, periodo, notaObtenida, notaMinima, fecha, observaciones) VALUES
(1, 1, 2, 'Unidad 1', 88.00, 60.00, '2026-10-15', 'Buen desempeño en Programación II'),
(1, 2, 2, 'Unidad 1', 92.50, 60.00, '2026-10-16', 'Excelente dominio de SQL y Modelo Relacional'),
(4, 1, 2, 'Unidad 1', 48.00, 60.00, '2026-10-15', 'Dificultad en clases abstractas y herencia'),
(4, 2, 2, 'Unidad 1', 62.00, 60.00, '2026-10-16', 'Aprobado en zona mínima'),
(5, 1, 2, 'Unidad 1', 75.00, 60.00, '2026-10-15', 'Rendimiento promedio constante');

-- 6. MÓDULO: ALERTAS TEMPRANAS (Monitoreo de Riesgo)
INSERT INTO alerta (numeroAlerta, idEstudiante, idUsuarioResp, fecha, tipoAlerta, motivo, prioridad, origenInformacion, estado, observaciones) VALUES
('ALT-2026-001', 4, 3, '2026-10-28', 'Inasistencia', 'Acumulación de 3 inasistencias en la misma semana', 'Media', 'Docente / Asistencia', 'Confirmada', 'Se requiere citación a padres'),
('ALT-2026-002', 4, 3, '2026-10-29', 'Bajo Rendimiento', 'Nota obtenida de 48 pts en Unidad 1 de Programación II', 'Alta', 'Calificaciones docentes', 'Confirmada', 'Requiere tutoría pedagógica y seguimiento')
ON DUPLICATE KEY UPDATE motivo = VALUES(motivo);

-- 7. MÓDULO: PLANES DE SEGUIMIENTO
INSERT INTO plan_seguimiento (idPlan, idEstudiante, idAlerta, idCoordinador, situacionIdentificada, objetivos, accionesPrevistas, fechaInicio, fechaRevision, estado, observaciones) VALUES
(1, 4, 1, 4, 'Inasistencias y bajo rendimiento académico en Programación II', '1. Garantizar asistencia al 100% de clases.\n2. Nivelar conocimientos en programación orientada a objetos.\n3. Monitoreo semanal con la madre.', 'Tutoría académica martes y jueves, entrevista con madre', '2026-11-01', '2026-11-20', 'En ejecucion', 'Caso en seguimiento activo por Orientación')
ON DUPLICATE KEY UPDATE situacionIdentificada = VALUES(situacionIdentificada);

-- Acciones de Apoyo
INSERT INTO accion_apoyo (idPlan, idUsuarioResp, fecha, tipoAccion, descripcion, resultado, compromisos, proximaFecha) VALUES
(1, 3, '2026-11-02', 'Entrevista', 'Entrevista con estudiante sobre motivos de ausencias', 'Estudiante muestra apertura y compromiso a mejorar', 'Asistir puntualmente a tutorías académicas', '2026-11-10'),
(1, 2, '2026-11-04', 'Tutoria', 'Repaso de POO, encapsulamiento y herencia en Java', 'Comprendió conceptos de clases y objetos', 'Completar ejercicios prácticos de laboratorio', '2026-11-12');

-- 8. MÓDULO: REUNIÓN CON PADRES
INSERT INTO reunion_responsable (idReunion, idEstudiante, idResponsable, idUsuarioConvoca, fechaHora, motivo, participantes, situacionPresentada, acuerdos, responsablesAcuerdos, fechaRevision, estadoAcuerdos, observaciones) VALUES
(1, 4, 1, 3, '2026-10-31 10:00:00', 'Seguimiento por inasistencias y bajo rendimiento', 'Licda. Marta Gómez (Orientadora), Sra. Rosa Pérez (Madre), Estudiante José Julián', 'El alumno presenta 2 ausencias injustificadas y nota de 48 pts en Unidad 1', '1. Supervisión de tareas diarias en casa.\n2. Asistencia obligatoria a tutorías los martes y jueves.\n3. Presentar justificantes médicos oportunos en caso de salud.', 'Madre de familia y Orientación Escolar', '2026-11-20', 'Cumplido', 'Acta formal firmada por los comparecientes')
ON DUPLICATE KEY UPDATE motivo = VALUES(motivo);
