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
import umg.modelo.RendimientoAcademico;

/**
 *
 * @author jsura
 */
public class RendimientoAcademicoDAO {

    // metodo insertar calificacion / rendimiento
    public void insertar(RendimientoAcademico r) throws SQLException {
        String sql = "INSERT INTO rendimiento_academico(idEstudiante, idCurso, idUsuarioDocente, periodo, notaObtenida, notaMinima, fecha, observaciones) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, r.getIdEstudiante());
            ps.setInt(2, r.getIdCurso());
            ps.setInt(3, r.getIdUsuarioDocente());
            ps.setString(4, r.getPeriodo());
            ps.setDouble(5, r.getNotaObtenida());
            ps.setDouble(6, r.getNotaMinima());
            ps.setDate(7, java.sql.Date.valueOf(r.getFecha()));
            ps.setString(8, r.getObservaciones());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo registrar la calificacion.");
            }
        }
    }

    // metodo actualizar calificacion / rendimiento
    public void actualizar(RendimientoAcademico r) throws SQLException {
        String sql = "UPDATE rendimiento_academico SET idEstudiante = ?, idCurso = ?, idUsuarioDocente = ?, periodo = ?, notaObtenida = ?, notaMinima = ?, fecha = ?, observaciones = ? "
                   + "WHERE idRendimiento = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, r.getIdEstudiante());
            ps.setInt(2, r.getIdCurso());
            ps.setInt(3, r.getIdUsuarioDocente());
            ps.setString(4, r.getPeriodo());
            ps.setDouble(5, r.getNotaObtenida());
            ps.setDouble(6, r.getNotaMinima());
            ps.setDate(7, java.sql.Date.valueOf(r.getFecha()));
            ps.setString(8, r.getObservaciones());
            ps.setInt(9, r.getIdRendimiento());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar la calificacion.");
            }
        }
    }

    // metodo eliminar calificacion
    public void eliminar(int idRendimiento) throws SQLException {
        String sql = "DELETE FROM rendimiento_academico WHERE idRendimiento = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idRendimiento);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar la calificacion.");
            }
        }
    }

    // metodo listar todas las calificaciones
    public List<RendimientoAcademico> listar() throws SQLException {
        List<RendimientoAcademico> lista = new ArrayList<>();
        String sql = "SELECT idRendimiento, idEstudiante, idCurso, idUsuarioDocente, periodo, notaObtenida, notaMinima, fecha, observaciones FROM rendimiento_academico ORDER BY idRendimiento DESC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RendimientoAcademico r = new RendimientoAcademico(
                    rs.getInt("idRendimiento"),
                    rs.getInt("idEstudiante"),
                    rs.getInt("idCurso"),
                    rs.getInt("idUsuarioDocente"),
                    rs.getString("periodo"),
                    rs.getDouble("notaObtenida"),
                    rs.getDouble("notaMinima"),
                    rs.getString("fecha"),
                    rs.getString("observaciones")
                );
                lista.add(r);
            }
        }
        return lista;
    }

    // metodo listar calificaciones por estudiante
    public List<RendimientoAcademico> listarPorEstudiante(int idEstudiante) throws SQLException {
        List<RendimientoAcademico> lista = new ArrayList<>();
        String sql = "SELECT idRendimiento, idEstudiante, idCurso, idUsuarioDocente, periodo, notaObtenida, notaMinima, fecha, observaciones FROM rendimiento_academico WHERE idEstudiante = ? ORDER BY periodo ASC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RendimientoAcademico r = new RendimientoAcademico(
                        rs.getInt("idRendimiento"),
                        rs.getInt("idEstudiante"),
                        rs.getInt("idCurso"),
                        rs.getInt("idUsuarioDocente"),
                        rs.getString("periodo"),
                        rs.getDouble("notaObtenida"),
                        rs.getDouble("notaMinima"),
                        rs.getString("fecha"),
                        rs.getString("observaciones")
                    );
                    lista.add(r);
                }
            }
        }
        return lista;
    }

    // metodo para calcular promedio general de un estudiante
    public double calcularPromedioEstudiante(int idEstudiante) throws SQLException {
        String sql = "SELECT AVG(notaObtenida) AS promedio FROM rendimiento_academico WHERE idEstudiante = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("promedio");
                }
            }
        }
        return 0.0;
    }
}
