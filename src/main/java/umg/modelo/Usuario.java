/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jsura
 */
public class Usuario extends Persona {

    private String username;
    private String clave;
    private String rol;
    private String correo;
    private String estado;

    // constructor vacio
    public Usuario() {
        super();
    }

    // constructor completo
    public Usuario(int idUsuario, String username, String clave, String nombreCompleto, String rol, String correo, String telefono, String estado) {
        super(idUsuario, nombreCompleto, "", "", telefono, "");
        this.username = username;
        this.clave = clave;
        this.rol = rol;
        this.correo = correo;
        this.estado = estado;
    }

    // metodo polimorfico base para permisos
    public List<String> obtenerPermisosModulo() {
        List<String> permisos = new ArrayList<>();
        permisos.add("Inicio");
        return permisos;
    }

    @Override
    public String mostrarDetalle() {
        return "Usuario: " + username + " | Rol: " + rol + " | Estado: " + estado;
    }

    // getters y setters
    public int getIdUsuario() {
        return idPersona;
    }

    public void setIdUsuario(int idUsuario) {
        this.idPersona = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

