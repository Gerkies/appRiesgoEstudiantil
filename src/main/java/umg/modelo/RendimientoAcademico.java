/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class RendimientoAcademico {

    private int idRendimiento;
    private int idEstudiante;
    private int idCurso;
    private int idUsuarioDocente;
    private String periodo;
    private double notaObtenida;
    private double notaMinima;
    private String fecha;
    private String observaciones;

    public RendimientoAcademico() {
    }

    public RendimientoAcademico(int idRendimiento, int idEstudiante, int idCurso, int idUsuarioDocente, String periodo, double notaObtenida, double notaMinima, String fecha, String observaciones) {
        this.idRendimiento = idRendimiento;
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
        this.idUsuarioDocente = idUsuarioDocente;
        this.periodo = periodo;
        this.notaObtenida = notaObtenida;
        this.notaMinima = notaMinima;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public boolean estaAprobado() {
        return notaObtenida >= notaMinima;
    }

    public int getIdRendimiento() {
        return idRendimiento;
    }

    public void setIdRendimiento(int idRendimiento) {
        this.idRendimiento = idRendimiento;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdUsuarioDocente() {
        return idUsuarioDocente;
    }

    public void setIdUsuarioDocente(int idUsuarioDocente) {
        this.idUsuarioDocente = idUsuarioDocente;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public double getNotaObtenida() {
        return notaObtenida;
    }

    public void setNotaObtenida(double notaObtenida) {
        this.notaObtenida = notaObtenida;
    }

    public double getNotaMinima() {
        return notaMinima;
    }

    public void setNotaMinima(double notaMinima) {
        this.notaMinima = notaMinima;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

