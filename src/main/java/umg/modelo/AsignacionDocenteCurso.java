/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class AsignacionDocenteCurso {

    private int idAsignacion;
    private int idUsuario;
    private int idCurso;
    private int idGrupo;
    private int anoAcademico;
    private String fechaInicio;
    private String fechaFin;
    private String estado;

    public AsignacionDocenteCurso() {
    }

    public AsignacionDocenteCurso(int idAsignacion, int idUsuario, int idCurso, int idGrupo, int anoAcademico, String fechaInicio, String fechaFin, String estado) {
        this.idAsignacion = idAsignacion;
        this.idUsuario = idUsuario;
        this.idCurso = idCurso;
        this.idGrupo = idGrupo;
        this.anoAcademico = anoAcademico;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public int getAnoAcademico() {
        return anoAcademico;
    }

    public void setAnoAcademico(int anoAcademico) {
        this.anoAcademico = anoAcademico;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
