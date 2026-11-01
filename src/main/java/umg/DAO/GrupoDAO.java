/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import umg.modelo.Grupo;

/**
 *
 * @author jsura
 */
public class GrupoDAO {

    // metodo insertar grupo
    public void insertar(Grupo g) throws SQLException {
        String sql = "INSERT INTO grupo(codigoGrupo, anoAcademico, nivel, grado, carrera, seccion, jornada, idDocenteEnc, cantidadEstudiantes, estado, observaciones) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, g.getCodigoGrupo());
            ps.setInt(2, g.getAnoAcademico());
            ps.setString(3, g.getNivel());
            ps.setString(4, g.getGrado());
            ps.setString(5, g.getCarrera());
            ps.setString(6, g.getSeccion());
            ps.setString(7, g.getJornada());
            ps.setInt(8, g.getIdDocenteEnc());
            ps.setInt(9, g.getCantidadEstudiantes());
            ps.setString(10, g.getEstado());
            ps.setString(11, g.getObservaciones());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el grupo.");
            }
        }
    }

    // metodo actualizar grupo
    public void actualizar(Grupo g) throws SQLException {
        String sql = "UPDATE grupo SET codigoGrupo = ?, anoAcademico = ?, nivel = ?, grado = ?, carrera = ?, seccion = ?, jornada = ?, idDocenteEnc = ?, cantidadEstudiantes = ?, estado = ?, observaciones = ? "
                   + "WHERE idGrupo = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, g.getCodigoGrupo());
            ps.setInt(2, g.getAnoAcademico());
            ps.setString(3, g.getNivel());
            ps.setString(4, g.getGrado());
            ps.setString(5, g.getCarrera());
            ps.setString(6, g.getSeccion());
            ps.setString(7, g.getJornada());
            ps.setInt(8, g.getIdDocenteEnc());
            ps.setInt(9, g.getCantidadEstudiantes());
            ps.setString(10, g.getEstado());
            ps.setString(11, g.getObservaciones());
            ps.setInt(12, g.getIdGrupo());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar el grupo.");
            }
        }
    }

    // metodo eliminar grupo
    public void eliminar(int idGrupo) throws SQLException {
        String sql = "DELETE FROM grupo WHERE idGrupo = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idGrupo);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar el grupo.");
            }
        }
    }

    // metodo listar todos los grupos
    public List<Grupo> listar() throws SQLException {
        List<Grupo> lista = new ArrayList<>();
        String sql = "SELECT idGrupo, codigoGrupo, anoAcademico, nivel, grado, carrera, seccion, jornada, idDocenteEnc, cantidadEstudiantes, estado, observaciones FROM grupo ORDER BY idGrupo ASC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Grupo g = new Grupo(
                    rs.getInt("idGrupo"),
                    rs.getString("codigoGrupo"),
                    rs.getInt("anoAcademico"),
                    rs.getString("nivel"),
                    rs.getString("grado"),
                    rs.getString("carrera"),
                    rs.getString("seccion"),
                    rs.getString("jornada"),
                    rs.getInt("idDocenteEnc"),
                    rs.getInt("cantidadEstudiantes"),
                    rs.getString("estado"),
                    rs.getString("observaciones")
                );
                lista.add(g);
            }
        }
        return lista;
    }
}
