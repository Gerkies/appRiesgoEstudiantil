# Sistema de Seguimiento de Estudiantes con Riesgo de Abandono Escolar

## Descripción del Proyecto

Este software es una aplicación de escritorio desarrollada en Java con interfaz gráfica y base de datos relacional MySQL. Su propósito principal es ayudar a las instituciones educativas a identificar oportunamente a aquellos estudiantes que presentan dificultades en su asistencia o bajas calificaciones, con el fin de coordinar apoyo pedagógico temprano y evitar que abandonen sus estudios.

El sistema le permite al personal educativo centralizar la información general de los estudiantes, llevar el registro diario de asistencia, ingresar calificaciones por unidad, emitir alertas cuando un alumno se encuentra en riesgo, documentar acuerdos en reuniones con los padres de familia y generar reportes oficiales en formato PDF.

<img width="1615" height="601" alt="PROGRA2-01" src="https://github.com/user-attachments/assets/059ddae2-1bb5-43af-a713-22cda4a94100" />

## Requisitos Previos

Para que usted pueda ejecutar este proyecto en su computadora, asegúrese de contar con los siguientes programas instalados:

- Java Development Kit (JDK): Versión 17 o superior.
- Servidor de Base de Datos: MySQL Server versión 8.0 o superior (o el servicio MySQL a través de XAMPP).
- Entorno de Desarrollo (IDE): Apache NetBeans IDE (versión 17 o superior recomendada).

## Pasos para Configurar y Ejecutar el Proyecto

### Paso 1: Obtener el Proyecto

Usted puede descargar los archivos del proyecto o clonar el repositorio mediante el siguiente comando:

> ``` git clone https://github.com/Gerkies/appRiesgoEstudiantil.git ```

### Paso 2: Crear y Cargar la Base de Datos

- Inicie el servicio de MySQL en su computadora.
- Abra su herramienta de administración de bases de datos (por ejemplo, MySQL Workbench).
- Abra y ejecute el archivo BaseDatos.sql ubicado en la carpeta del proyecto. Este archivo creará la base de datos db_seguimiento_escolar junto con todas sus tablas.
- Para disponer de datos de prueba y poder ingresar al sistema de inmediato, ejecute a continuación el archivo BaseDatosPruebas.sql.

### Paso 3: Configurar la Conexión a la Base de Datos

Abra el archivo de conexión ubicado en la siguiente ruta:
src/main/java/umg/DAO/ConexionDB.java

Verifique que el usuario y la contraseña coincidan con los de su instalación local de MySQL:

```
private static final String URL
        = "jdbc:mysql://localhost:3306/db_seguimiento_escolar"
        + "?useSSL=false"
        + "&allowPublicKeyRetrieval=true"
        + "&serverTimezone=America/Guatemala";

private static final String USER = "root";
private static final String PASS = "SuClaveDeMySQL"; <-== Ingrese aquí la contraseña de su servidor MySQL
```

### Paso 4: Abrir y Ejecutar el Proyecto en NetBeans

- Inicie Apache NetBeans IDE.
- En el menú superior, diríjase a File, seleccione Open Project... y elija la carpeta del proyecto (appRiesgoEstudiantil).
- En el panel izquierdo de proyectos, haga clic derecho sobre el proyecto y seleccione Clean and Build.
- Presione la tecla F6 o haga clic en el botón Run Project para iniciar la aplicación.

## Usuarios y Contraseñas para Pruebas

Para que usted pueda evaluar el sistema desde las distintas perspectivas y permisos de acceso, puede utilizar cualquiera de las siguientes cuentas predeterminadas:

<table>
  <thead>
    <tr>
      <th>Rol de Usuario</th>
      <th>Nombre de Usuario</th>
      <th>Contraseña</th>
      <th>Acceso Permitido</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Administrador</td>
      <td><code>admin</code></td>
      <td><code>admin123</code></td>
      <td>Control total del sistema, catálogo de usuarios y todos los módulos.</td>
    </tr>
    <tr>
      <td>Docente</td>
      <td><code>docente1</code></td>
      <td><code>docente123</code></td>
      <td>Registro de asistencias diarias y calificaciones por materia.</td>
    </tr>
    <tr>
      <td>Orientador</td>
      <td><code>orientador1</code></td>
      <td><code>orienta123</code></td>
      <td>Detección de alertas, planes de apoyo y actas de reunión.</td>
    </tr>
    <tr>
      <td>Coordinador</td>
      <td><code>coordinador1</code></td>
      <td><code>coord123</code></td>
      <td>Supervisión de salones y seguimiento académico general.</td>
    </tr>
    <tr>
      <td>Director</td>
      <td><code>director1</code></td>
      <td><code>director123</code></td>
      <td>Consulta de reportes y visualización general de la institución.</td>
    </tr>
  </tbody>
</table>

## Módulos Principales del Sistema

El sistema ofrece los siguientes módulos de trabajo:

- Control de Usuarios y Roles: Permite el ingreso de usuarios según su cargo dentro del centro educativo (Administrador, Docente, Orientador, Coordinador y Director).
- Gestión de Estudiantes: Registro de datos personales, información de contacto, grupo o salón asignado y estado del estudiante.
- Padres y Tutores: Registro de los responsables familiares vinculados a cada estudiante para citaciones y seguimiento conjunto.
- Control de Asistencia: Registro diario de asistencia a clases (presente, ausente o justificado), con validación para no duplicar registros en la misma fecha y materia.
- Calificaciones: Ingreso de notas por unidad académica sobre una escala de 0 a 100 puntos y control de notas mínimas de aprobación.
- Alertas Tempranas: Identificación de situaciones de riesgo por acumulación de inasistencias o materias reprobadas.
- Planes de Apoyo: Formulación de metas de nivelación, compromisos y asignación de tutorías para respaldar al estudiante.
- Generación de Reportes en PDF: Creación de la Ficha de Seguimiento individual del estudiante y el Acta de Compromisos firmada con los tutores.

## Nota

Proyecto desarrollado con fines académicos para el curso de Programación II, Facultad de Ingeniería en Sistemas de Información y Ciencias de la Computación, Universidad Mariano Gálvez de Guatemala.
