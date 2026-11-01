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
public class CoordinadorAcademico extends Usuario {

    private String nivelCoordinado;

    public CoordinadorAcademico() {
        super();
        setRol("Coordinador academico");
    }

    public CoordinadorAcademico(int idUsuario, String username, String clave, String nombreCompleto, String correo, String telefono, String estado) {
        super(idUsuario, username, clave, nombreCompleto, "Coordinador academico", correo, telefono, estado);
    }

    @Override
    public List<String> obtenerPermisosModulo() {
        List<String> p = new ArrayList<>();
        p.add("Grupos");
        p.add("Cursos");
        p.add("Asignaciones");
        p.add("Estudiantes");
        p.add("Alertas");
        p.add("Planes");
        p.add("Reportes");
        return p;
    }

    @Override
    public String mostrarDetalle() {
        return "[COORDINADOR ACADEMICO] " + getUsername() + " - Supervision de grupos, cursos y planes";
    }

    public String getNivelCoordinado() {
        return nivelCoordinado;
    }

    public void setNivelCoordinado(String nivelCoordinado) {
        this.nivelCoordinado = nivelCoordinado;
    }
}
