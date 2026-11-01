/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
public class EstudianteResponsable {

    private int idEstResp;
    private int idEstudiante;
    private int idResponsable;
    private String esPrincipal;

    public EstudianteResponsable() {
    }

    public EstudianteResponsable(int idEstResp, int idEstudiante, int idResponsable, String esPrincipal) {
        this.idEstResp = idEstResp;
        this.idEstudiante = idEstudiante;
        this.idResponsable = idResponsable;
        this.esPrincipal = esPrincipal;
    }

    public int getIdEstResp() {
        return idEstResp;
    }

    public void setIdEstResp(int idEstResp) {
        this.idEstResp = idEstResp;
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

    public String getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(String esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
}
