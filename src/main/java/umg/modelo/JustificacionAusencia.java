/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class JustificacionAusencia {

    private int idJustificacion;
    private int idEstudiante;
    private int idResponsable;
    private int idUsuarioAutoriza;
    private String fechaAusencia;
    private String motivo;
    private String fechaPresentacion;
    private String documentoAdjunto;
    private String observaciones;

    public JustificacionAusencia() {
    }

    public JustificacionAusencia(int idJustificacion, int idEstudiante, int idResponsable, int idUsuarioAutoriza, String fechaAusencia, String motivo, String fechaPresentacion, String documentoAdjunto, String observaciones) {
        this.idJustificacion = idJustificacion;
        this.idEstudiante = idEstudiante;
        this.idResponsable = idResponsable;
        this.idUsuarioAutoriza = idUsuarioAutoriza;
        this.fechaAusencia = fechaAusencia;
        this.motivo = motivo;
        this.fechaPresentacion = fechaPresentacion;
        this.documentoAdjunto = documentoAdjunto;
        this.observaciones = observaciones;
    }

    public int getIdJustificacion() {
        return idJustificacion;
    }

    public void setIdJustificacion(int idJustificacion) {
        this.idJustificacion = idJustificacion;
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

    public int getIdUsuarioAutoriza() {
        return idUsuarioAutoriza;
    }

    public void setIdUsuarioAutoriza(int idUsuarioAutoriza) {
        this.idUsuarioAutoriza = idUsuarioAutoriza;
    }

    public String getFechaAusencia() {
        return fechaAusencia;
    }

    public void setFechaAusencia(String fechaAusencia) {
        this.fechaAusencia = fechaAusencia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(String fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public String getDocumentoAdjunto() {
        return documentoAdjunto;
    }

    public void setDocumentoAdjunto(String documentoAdjunto) {
        this.documentoAdjunto = documentoAdjunto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
