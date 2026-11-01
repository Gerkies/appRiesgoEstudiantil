/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class ReunionResponsable {

    private int idReunion;
    private int idEstudiante;
    private int idResponsable;
    private int idUsuarioConvoca;
    private String fechaHora;
    private String motivo;
    private String participantes;
    private String situacionPresentada;
    private String acuerdos;
    private String responsablesAcuerdos;
    private String fechaRevision;
    private String estadoAcuerdos;
    private String observaciones;

    public ReunionResponsable() {
    }

    public ReunionResponsable(int idReunion, int idEstudiante, int idResponsable, int idUsuarioConvoca, String fechaHora, String motivo, String participantes, String situacionPresentada, String acuerdos, String responsablesAcuerdos, String fechaRevision, String estadoAcuerdos, String observaciones) {
        this.idReunion = idReunion;
        this.idEstudiante = idEstudiante;
        this.idResponsable = idResponsable;
        this.idUsuarioConvoca = idUsuarioConvoca;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.participantes = participantes;
        this.situacionPresentada = situacionPresentada;
        this.acuerdos = acuerdos;
        this.responsablesAcuerdos = responsablesAcuerdos;
        this.fechaRevision = fechaRevision;
        this.estadoAcuerdos = estadoAcuerdos;
        this.observaciones = observaciones;
    }

    public int getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(int idReunion) {
        this.idReunion = idReunion;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public int getIdUsuarioConvoca() {
        return idUsuarioConvoca;
    }

    public void setIdUsuarioConvoca(int idUsuarioConvoca) {
        this.idUsuarioConvoca = idUsuarioConvoca;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    public String getSituacionPresentada() {
        return situacionPresentada;
    }

    public void setSituacionPresentada(String situacionPresentada) {
        this.situacionPresentada = situacionPresentada;
    }

    public String getAcuerdos() {
        return acuerdos;
    }

    public void setAcuerdos(String acuerdos) {
        this.acuerdos = acuerdos;
    }

    public String getResponsablesAcuerdos() {
        return responsablesAcuerdos;
    }

    public void setResponsablesAcuerdos(String responsablesAcuerdos) {
        this.responsablesAcuerdos = responsablesAcuerdos;
    }

    public String getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(String fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getEstadoAcuerdos() {
        return estadoAcuerdos;
    }

    public void setEstadoAcuerdos(String estadoAcuerdos) {
        this.estadoAcuerdos = estadoAcuerdos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

