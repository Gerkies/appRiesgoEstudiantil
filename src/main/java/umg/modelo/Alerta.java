/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class Alerta {

    private int idAlerta;
    private String numeroAlerta;
    private int idEstudiante;
    private int idUsuarioResp;
    private String fecha;
    private String tipoAlerta;
    private String motivo;
    private String prioridad;
    private String origenInformacion;
    private String estado;
    private String observaciones;

    public Alerta() {
    }

    public Alerta(int idAlerta, String numeroAlerta, int idEstudiante, int idUsuarioResp, String fecha, String tipoAlerta, String motivo, String prioridad, String origenInformacion, String estado, String observaciones) {
        this.idAlerta = idAlerta;
        this.numeroAlerta = numeroAlerta;
        this.idEstudiante = idEstudiante;
        this.idUsuarioResp = idUsuarioResp;
        this.fecha = fecha;
        this.tipoAlerta = tipoAlerta;
        this.motivo = motivo;
        this.prioridad = prioridad;
        this.origenInformacion = origenInformacion;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public String getNumeroAlerta() {
        return numeroAlerta;
    }

    public void setNumeroAlerta(String numeroAlerta) {
        this.numeroAlerta = numeroAlerta;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdUsuarioResp() {
        return idUsuarioResp;
    }

    public void setIdUsuarioResp(int idUsuarioResp) {
        this.idUsuarioResp = idUsuarioResp;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getOrigenInformacion() {
        return origenInformacion;
    }

    public void setOrigenInformacion(String origenInformacion) {
        this.origenInformacion = origenInformacion;
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
        return numeroAlerta + " - " + tipoAlerta + " (" + prioridad + ")";
    }
}
