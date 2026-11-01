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
public class Docente extends Usuario {

    private String especialidad;

    public Docente() {
        super();
        setRol("Docente");
    }

    public Docente(int idUsuario, String username, String clave, String nombreCompleto, String correo, String telefono, String estado) {
        super(idUsuario, username, clave, nombreCompleto, "Docente", correo, telefono, estado);
    }

    @Override
    public List<String> obtenerPermisosModulo() {
        List<String> p = new ArrayList<>();
        p.add("Asistencia");
        p.add("Rendimiento");
        p.add("Estudiantes");
        p.add("MisCursos");
        return p;
    }

    @Override
    public String mostrarDetalle() {
        return "[DOCENTE] " + getUsername() + " - Registro de notas y asistencia";
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
