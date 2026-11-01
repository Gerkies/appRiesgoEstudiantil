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
public class Orientador extends Usuario {

    private String areaOrientacion;

    public Orientador() {
        super();
        setRol("Orientador");
    }

    public Orientador(int idUsuario, String username, String clave, String nombreCompleto, String correo, String telefono, String estado) {
        super(idUsuario, username, clave, nombreCompleto, "Orientador", correo, telefono, estado);
    }

    @Override
    public List<String> obtenerPermisosModulo() {
        List<String> p = new ArrayList<>();
        p.add("Alertas");
        p.add("Planes");
        p.add("FactoresRiesgo");
        p.add("AccionesApoyo");
        p.add("Reuniones");
        p.add("Visitas");
        p.add("Estudiantes");
        return p;
    }

    @Override
    public String mostrarDetalle() {
        return "[ORIENTADOR / PSICOLOGIA] " + getUsername() + " - Gestion de factores y apoyo psicopedagogico";
    }

    public String getAreaOrientacion() {
        return areaOrientacion;
    }

    public void setAreaOrientacion(String areaOrientacion) {
        this.areaOrientacion = areaOrientacion;
    }
}
