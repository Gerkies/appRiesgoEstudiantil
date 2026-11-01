/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class Responsable extends Persona {

    private String codigoResponsable;
    private String dpi;
    private String parentesco;
    private String correoElectronico;
    private String ocupacion;

    public Responsable() {
        super();
    }

    public Responsable(int idResponsable, String codigoResponsable, String dpi, String nombreCompleto, String parentesco, String direccion, String telefono, String correoElectronico, String ocupacion, String observaciones) {
        super(idResponsable, nombreCompleto, "", direccion, telefono, observaciones);
        this.codigoResponsable = codigoResponsable;
        this.dpi = dpi;
        this.parentesco = parentesco;
        this.correoElectronico = correoElectronico;
        this.ocupacion = ocupacion;
    }

    @Override
    public String mostrarDetalle() {
        return "Responsable: " + nombres + " (" + parentesco + ") - Tel: " + telefono;
    }

    public int getIdResponsable() {
        return idPersona;
    }

    public void setIdResponsable(int idResponsable) {
        this.idPersona = idResponsable;
    }

    public String getCodigoResponsable() {
        return codigoResponsable;
    }

    public void setCodigoResponsable(String codigoResponsable) {
        this.codigoResponsable = codigoResponsable;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCorreo() {
        return correoElectronico;
    }

    public void setCorreo(String correo) {
        this.correoElectronico = correo;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
}

