/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class PlanSeguimiento {

    private int idPlan;
    private int idEstudiante;
    private int idAlerta;
    private int idCoordinador;
    private String situacionIdentificada;
    private String objetivos;
    private String accionesPrevistas;
    private String fechaInicio;
    private String fechaRevision;
    private String estado;
    private String observaciones;

    public PlanSeguimiento() {
    }

    public PlanSeguimiento(int idPlan, int idEstudiante, int idAlerta, int idCoordinador, String situacionIdentificada, String objetivos, String accionesPrevistas, String fechaInicio, String fechaRevision, String estado, String observaciones) {
        this.idPlan = idPlan;
        this.idEstudiante = idEstudiante;
        this.idAlerta = idAlerta;
        this.idCoordinador = idCoordinador;
        this.situacionIdentificada = situacionIdentificada;
        this.objetivos = objetivos;
        this.accionesPrevistas = accionesPrevistas;
        this.fechaInicio = fechaInicio;
        this.fechaRevision = fechaRevision;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public String getSituacionIdentificada() {
        return situacionIdentificada;
    }

    public void setSituacionIdentificada(String situacionIdentificada) {
        this.situacionIdentificada = situacionIdentificada;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getAccionesPrevistas() {
        return accionesPrevistas;
    }

    public void setAccionesPrevistas(String accionesPrevistas) {
        this.accionesPrevistas = accionesPrevistas;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(String fechaRevision) {
        this.fechaRevision = fechaRevision;
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
        return "Plan #" + idPlan + " (" + estado + ")";
    }
}

