/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class ConfiguracionAlerta {

    private int idConfiguracion;
    private String nombreParametro;
    private double valorLimite;
    private String descripcion;

    public ConfiguracionAlerta() {
    }

    public ConfiguracionAlerta(int idConfiguracion, String nombreParametro, double valorLimite, String descripcion) {
        this.idConfiguracion = idConfiguracion;
        this.nombreParametro = nombreParametro;
        this.valorLimite = valorLimite;
        this.descripcion = descripcion;
    }

    public int getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(int idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(double valorLimite) {
        this.valorLimite = valorLimite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombreParametro + " (Limite: " + valorLimite + ")";
    }
}
