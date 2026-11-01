/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class CentroEducativo {

    private int idCentro;
    private String codigoInstitucional;
    private String nombreCentro;
    private String direccion;
    private String telefono;
    private String correo;
    private String nombreDirector;
    private String nivelesAtendidos;
    private String logotipo;
    private int anoVigente;

    public CentroEducativo() {
    }

    public CentroEducativo(int idCentro, String codigoInstitucional, String nombreCentro, String direccion, String telefono, String correo, String nombreDirector, String nivelesAtendidos, String logotipo, int anoVigente) {
        this.idCentro = idCentro;
        this.codigoInstitucional = codigoInstitucional;
        this.nombreCentro = nombreCentro;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.nombreDirector = nombreDirector;
        this.nivelesAtendidos = nivelesAtendidos;
        this.logotipo = logotipo;
        this.anoVigente = anoVigente;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    public String getCodigoInstitucional() {
        return codigoInstitucional;
    }

    public void setCodigoInstitucional(String codigoInstitucional) {
        this.codigoInstitucional = codigoInstitucional;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getNivelesAtendidos() {
        return nivelesAtendidos;
    }

    public void setNivelesAtendidos(String nivelesAtendidos) {
        this.nivelesAtendidos = nivelesAtendidos;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    public int getAnoVigente() {
        return anoVigente;
    }

    public void setAnoVigente(int anoVigente) {
        this.anoVigente = anoVigente;
    }

    @Override
    public String toString() {
        return nombreCentro;
    }
}
