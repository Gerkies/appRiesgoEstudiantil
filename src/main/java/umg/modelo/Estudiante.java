/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class Estudiante extends Persona {

    private String carne;
    private String fechaNacimiento;
    private String sexo;
    private String comunidad;
    private String fechaInscripcion;
    private int idGrupo;
    private String estadoAcademico;
    private String fotografia;

    public Estudiante() {
        super();
    }

    public Estudiante(int idEstudiante, String carne, String nombres, String apellidos, String fechaNacimiento, String sexo, String direccion, String comunidad, String telefono, String fechaInscripcion, int idGrupo, String estadoAcademico, String fotografia, String observaciones) {
        super(idEstudiante, nombres, apellidos, direccion, telefono, observaciones);
        this.carne = carne;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.comunidad = comunidad;
        this.fechaInscripcion = fechaInscripcion;
        this.idGrupo = idGrupo;
        this.estadoAcademico = estadoAcademico;
        this.fotografia = fotografia;
    }

    @Override
    public String mostrarDetalle() {
        return "Estudiante: " + carne + " - " + getNombreCompleto() + " (" + estadoAcademico + ")";
    }

    public int getIdEstudiante() {
        return idPersona;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idPersona = idEstudiante;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getEstadoAcademico() {
        return estadoAcademico;
    }

    public void setEstadoAcademico(String estadoAcademico) {
        this.estadoAcademico = estadoAcademico;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
}

