/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class Grupo {

    private int idGrupo;
    private String codigoGrupo;
    private int anoAcademico;
    private String nivel;
    private String grado;
    private String carrera;
    private String seccion;
    private String jornada;
    private int idDocenteEnc;
    private int cantidadEstudiantes;
    private String estado;
    private String observaciones;

    public Grupo() {
    }

    public Grupo(int idGrupo, String codigoGrupo, int anoAcademico, String nivel, String grado, String carrera, String seccion, String jornada, int idDocenteEnc, int cantidadEstudiantes, String estado, String observaciones) {
        this.idGrupo = idGrupo;
        this.codigoGrupo = codigoGrupo;
        this.anoAcademico = anoAcademico;
        this.nivel = nivel;
        this.grado = grado;
        this.carrera = carrera;
        this.seccion = seccion;
        this.jornada = jornada;
        this.idDocenteEnc = idDocenteEnc;
        this.cantidadEstudiantes = cantidadEstudiantes;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public int getAnoAcademico() {
        return anoAcademico;
    }

    public void setAnoAcademico(int anoAcademico) {
        this.anoAcademico = anoAcademico;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public int getIdDocenteEnc() {
        return idDocenteEnc;
    }

    public void setIdDocenteEnc(int idDocenteEnc) {
        this.idDocenteEnc = idDocenteEnc;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return codigoGrupo + " - " + grado + " " + seccion + " (" + jornada + ")";
    }
}

