/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class FactorRiesgo {

    private int idFactor;
    private int idEstudiante;
    private String tipoFactor;
    private String descripcion;
    private String severidad;
    private String fechaRegistro;
    private String confidencial;

    public FactorRiesgo() {
    }

    public FactorRiesgo(int idFactor, int idEstudiante, String tipoFactor, String descripcion, String severidad, String fechaRegistro, String confidencial) {
        this.idFactor = idFactor;
        this.idEstudiante = idEstudiante;
        this.tipoFactor = tipoFactor;
        this.descripcion = descripcion;
        this.severidad = severidad;
        this.fechaRegistro = fechaRegistro;
        this.confidencial = confidencial;
    }

    public int getIdFactor() {
        return idFactor;
    }

    public void setIdFactor(int idFactor) {
        this.idFactor = idFactor;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getTipoFactor() {
        return tipoFactor;
    }

    public void setTipoFactor(String tipoFactor) {
        this.tipoFactor = tipoFactor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSeveridad() {
        return severidad;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getConfidencial() {
        return confidencial;
    }

    public void setConfidencial(String confidencial) {
        this.confidencial = confidencial;
    }
}

