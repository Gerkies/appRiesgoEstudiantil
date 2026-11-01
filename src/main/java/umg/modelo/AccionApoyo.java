package umg;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jsura
 */
public class AccionApoyo {

    private int idAccion;
    private int idPlan;
    private int idUsuarioResp;
    private String fecha;
    private String tipoAccion;
    private String descripcion;
    private String resultado;
    private String compromisos;
    private String proximaFecha;
    private String evidencia;

    public AccionApoyo() {
    }

    public AccionApoyo(int idAccion, int idPlan, int idUsuarioResp, String fecha, String tipoAccion, String descripcion, String resultado, String compromisos, String proximaFecha, String evidencia) {
        this.idAccion = idAccion;
        this.idPlan = idPlan;
        this.idUsuarioResp = idUsuarioResp;
        this.fecha = fecha;
        this.tipoAccion = tipoAccion;
        this.descripcion = descripcion;
        this.resultado = resultado;
        this.compromisos = compromisos;
        this.proximaFecha = proximaFecha;
        this.evidencia = evidencia;
    }

    public int getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(int idAccion) {
        this.idAccion = idAccion;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
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

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getCompromisos() {
        return compromisos;
    }

    public void setCompromisos(String compromisos) {
        this.compromisos = compromisos;
    }

    public String getProximaFecha() {
        return proximaFecha;
    }

    public void setProximaFecha(String proximaFecha) {
        this.proximaFecha = proximaFecha;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }
}
