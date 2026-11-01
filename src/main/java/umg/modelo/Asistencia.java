/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class Asistencia {

    private int idAsistencia;
    private String fecha;
    private int idCurso;
    private int idGrupo;
    private int idEstudiante;
    private int idUsuarioDocente;
    private String estadoAsistencia;
    private String motivo;
    private String observaciones;

    public Asistencia() {
    }

    public Asistencia(int idAsistencia, String fecha, int idCurso, int idGrupo, int idEstudiante, int idUsuarioDocente, String estadoAsistencia, String motivo, String observaciones) {
        this.idAsistencia = idAsistencia;
        this.fecha = fecha;
        this.idCurso = idCurso;
        this.idGrupo = idGrupo;
        this.idEstudiante = idEstudiante;
        this.idUsuarioDocente = idUsuarioDocente;
        this.estadoAsistencia = estadoAsistencia;
        this.motivo = motivo;
        this.observaciones = observaciones;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdUsuarioDocente() {
        return idUsuarioDocente;
    }

    public void setIdUsuarioDocente(int idUsuarioDocente) {
        this.idUsuarioDocente = idUsuarioDocente;
    }

    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

