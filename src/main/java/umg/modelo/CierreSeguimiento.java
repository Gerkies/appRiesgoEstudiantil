/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class CierreSeguimiento {

    private int idCierre;
    private int idPlan;
    private int idUsuarioCierra;
    private String fechaCierre;
    private String motivoCierre;
    private String resultado;
    private String observaciones;

    public CierreSeguimiento() {
    }

    public CierreSeguimiento(int idCierre, int idPlan, int idUsuarioCierra, String fechaCierre, String motivoCierre, String resultado, String observaciones) {
        this.idCierre = idCierre;
        this.idPlan = idPlan;
        this.idUsuarioCierra = idUsuarioCierra;
        this.fechaCierre = fechaCierre;
        this.motivoCierre = motivoCierre;
        this.resultado = resultado;
        this.observaciones = observaciones;
    }

    public int getIdCierre() {
        return idCierre;
    }

    public void setIdCierre(int idCierre) {
        this.idCierre = idCierre;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getIdUsuarioCierra() {
        return idUsuarioCierra;
    }

    public void setIdUsuarioCierra(int idUsuarioCierra) {
        this.idUsuarioCierra = idUsuarioCierra;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getMotivoCierre() {
        return motivoCierre;
    }

    public void setMotivoCierre(String motivoCierre) {
        this.motivoCierre = motivoCierre;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}