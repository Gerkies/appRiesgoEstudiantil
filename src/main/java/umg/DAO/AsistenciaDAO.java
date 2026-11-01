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
import umg.modelo.Asistencia;

/**
 *
 * @author jsura
 */
public class AsistenciaDAO {

    // metodo insertar asistencia con validacion de no duplicidad
    public void insertar(Asistencia a) throws SQLException {
        String sql = "INSERT INTO asistencia(fecha, idCurso, idGrupo, idEstudiante, idUsuarioDocente, estadoAsistencia, motivo, observaciones) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(a.getFecha()));
            ps.setInt(2, a.getIdCurso());
            ps.setInt(3, a.getIdGrupo());
            ps.setInt(4, a.getIdEstudiante());
            ps.setInt(5, a.getIdUsuarioDocente());
            ps.setString(6, a.getEstadoAsistencia());
            ps.setString(7, a.getMotivo());
            ps.setString(8, a.getObservaciones());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo registrar la asistencia.");
            }
        }
    }

    // metodo actualizar asistencia
    public void actualizar(Asistencia a) throws SQLException {
        String sql = "UPDATE asistencia SET fecha = ?, idCurso = ?, idGrupo = ?, idEstudiante = ?, idUsuarioDocente = ?, estadoAsistencia = ?, motivo = ?, observaciones = ? "
                   + "WHERE idAsistencia = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(a.getFecha()));
            ps.setInt(2, a.getIdCurso());
            ps.setInt(3, a.getIdGrupo());
            ps.setInt(4, a.getIdEstudiante());
            ps.setInt(5, a.getIdUsuarioDocente());
            ps.setString(6, a.getEstadoAsistencia());
            ps.setString(7, a.getMotivo());
            ps.setString(8, a.getObservaciones());
            ps.setInt(9, a.getIdAsistencia());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar la asistencia.");
            }
        }
    }

    // metodo eliminar asistencia
    public void eliminar(int idAsistencia) throws SQLException {
        String sql = "DELETE FROM asistencia WHERE idAsistencia = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idAsistencia);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar la asistencia.");
            }
        }
    }

    // metodo listar todas las asistencias
    public List<Asistencia> listar() throws SQLException {
        List<Asistencia> lista = new ArrayList<>();
        String sql = "SELECT idAsistencia, fecha, idCurso, idGrupo, idEstudiante, idUsuarioDocente, estadoAsistencia, motivo, observaciones FROM asistencia ORDER BY fecha DESC, idAsistencia DESC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Asistencia a = new Asistencia(
                    rs.getInt("idAsistencia"),
                    rs.getString("fecha"),
                    rs.getInt("idCurso"),
                    rs.getInt("idGrupo"),
                    rs.getInt("idEstudiante"),
                    rs.getInt("idUsuarioDocente"),
                    rs.getString("estadoAsistencia"),
                    rs.getString("motivo"),
                    rs.getString("observaciones")
                );
                lista.add(a);
            }
        }
        return lista;
    }

    // metodo listar asistencia por estudiante
    public List<Asistencia> listarPorEstudiante(int idEstudiante) throws SQLException {
        List<Asistencia> lista = new ArrayList<>();
        String sql = "SELECT idAsistencia, fecha, idCurso, idGrupo, idEstudiante, idUsuarioDocente, estadoAsistencia, motivo, observaciones FROM asistencia WHERE idEstudiante = ? ORDER BY fecha DESC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Asistencia a = new Asistencia(
                        rs.getInt("idAsistencia"),
                        rs.getString("fecha"),
                        rs.getInt("idCurso"),
                        rs.getInt("idGrupo"),
                        rs.getInt("idEstudiante"),
                        rs.getInt("idUsuarioDocente"),
                        rs.getString("estadoAsistencia"),
                        rs.getString("motivo"),
                        rs.getString("observaciones")
                    );
                    lista.add(a);
                }
            }
        }
        return lista;
    }
}
