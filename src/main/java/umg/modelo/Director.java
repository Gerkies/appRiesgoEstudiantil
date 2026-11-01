/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author jsura
 */
import java.util.ArrayList;
import java.util.List;

public class Director extends Usuario {

    private String numeroColegiado;

    public Director() {
        super();
        setRol("Director");
    }

    public Director(int idUsuario, String username, String clave, String nombreCompleto, String correo, String telefono, String estado) {
        super(idUsuario, username, clave, nombreCompleto, "Director", correo, telefono, estado);
    }

    @Override
    public List<String> obtenerPermisosModulo() {
        List<String> p = new ArrayList<>();
        p.add("Reportes");
        p.add("Estadisticas");
        p.add("Fichas");
        p.add("Auditoria");
        p.add("CentroEducativo");
        return p;
    }

    @Override
    public String mostrarDetalle() {
        return "[DIRECTOR] " + getUsername() + " - Supervision global y reportes ejecutivos";
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }
}

