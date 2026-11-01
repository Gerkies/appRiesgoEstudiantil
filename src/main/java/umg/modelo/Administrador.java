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
public class Administrador extends Usuario {

    private int nivelAcceso = 1;

    public Administrador() {
        super();
        setRol("Administrador");
    }

    public Administrador(int idUsuario, String username, String clave, String nombreCompleto, String correo, String telefono, String estado) {
        super(idUsuario, username, clave, nombreCompleto, "Administrador", correo, telefono, estado);
    }

    @Override
    public List<String> obtenerPermisosModulo() {
        List<String> p = new ArrayList<>();
        p.add("Usuarios");
        p.add("CentroEducativo");
        p.add("Grupos");
        p.add("Cursos");
        p.add("Asignaciones");
        p.add("Estudiantes");
        p.add("Responsables");
        p.add("Asistencia");
        p.add("Rendimiento");
        p.add("Alertas");
        p.add("Planes");
        p.add("Reportes");
        return p;
    }

    @Override
    public String mostrarDetalle() {
        return "[ADMINISTRADOR GENERAL] " + getUsername() + " - Acceso total al sistema";
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
}

