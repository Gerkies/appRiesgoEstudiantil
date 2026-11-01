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
import umg.AccionApoyo;
import umg.modelo.CierreSeguimiento;
import umg.modelo.PlanSeguimiento;

/**
 *
 * @author jsura
 */
public class PlanSeguimientoDAO {

    // metodo insertar plan de seguimiento
    public void insertar(PlanSeguimiento p) throws SQLException {
        String sql = "INSERT INTO plan_seguimiento(idEstudiante, idAlerta, idCoordinador, situacionIdentificada, objetivos, accionesPrevistas, fechaInicio, fechaRevision, estado, observaciones) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdEstudiante());
            ps.setInt(2, p.getIdAlerta());
            ps.setInt(3, p.getIdCoordinador());
            ps.setString(4, p.getSituacionIdentificada());
            ps.setString(5, p.getObjetivos());
            ps.setString(6, p.getAccionesPrevistas());
            ps.setDate(7, java.sql.Date.valueOf(p.getFechaInicio()));
            ps.setDate(8, java.sql.Date.valueOf(p.getFechaRevision()));
            ps.setString(9, p.getEstado());
            ps.setString(10, p.getObservaciones());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo registrar el plan de seguimiento.");
            }
        }
    }

    // metodo actualizar plan de seguimiento
    public void actualizar(PlanSeguimiento p) throws SQLException {
        String sql = "UPDATE plan_seguimiento SET idEstudiante = ?, idAlerta = ?, idCoordinador = ?, situacionIdentificada = ?, objetivos = ?, accionesPrevistas = ?, fechaInicio = ?, fechaRevision = ?, estado = ?, observaciones = ? "
                   + "WHERE idPlan = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdEstudiante());
            ps.setInt(2, p.getIdAlerta());
            ps.setInt(3, p.getIdCoordinador());
            ps.setString(4, p.getSituacionIdentificada());
            ps.setString(5, p.getObjetivos());
            ps.setString(6, p.getAccionesPrevistas());
            ps.setDate(7, java.sql.Date.valueOf(p.getFechaInicio()));
            ps.setDate(8, java.sql.Date.valueOf(p.getFechaRevision()));
            ps.setString(9, p.getEstado());
            ps.setString(10, p.getObservaciones());
            ps.setInt(11, p.getIdPlan());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar el plan de seguimiento.");
            }
        }
    }

    // metodo eliminar plan de seguimiento
    public void eliminar(int idPlan) throws SQLException {
        String sql = "DELETE FROM plan_seguimiento WHERE idPlan = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPlan);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar el plan de seguimiento.");
            }
        }
    }

    // metodo listar planes de seguimiento
    public List<PlanSeguimiento> listar() throws SQLException {
        List<PlanSeguimiento> lista = new ArrayList<>();
        String sql = "SELECT idPlan, idEstudiante, idAlerta, idCoordinador, situacionIdentificada, objetivos, accionesPrevistas, fechaInicio, fechaRevision, estado, observaciones FROM plan_seguimiento ORDER BY idPlan DESC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PlanSeguimiento p = new PlanSeguimiento(
                    rs.getInt("idPlan"),
                    rs.getInt("idEstudiante"),
                    rs.getInt("idAlerta"),
                    rs.getInt("idCoordinador"),
                    rs.getString("situacionIdentificada"),
                    rs.getString("objetivos"),
                    rs.getString("accionesPrevistas"),
                    rs.getString("fechaInicio"),
                    rs.getString("fechaRevision"),
                    rs.getString("estado"),
                    rs.getString("observaciones")
                );
                lista.add(p);
            }
        }
        return lista;
    }

    // metodo registrar una accion de apoyo vinculada al plan
    public void insertarAccion(AccionApoyo a) throws SQLException {
        String sql = "INSERT INTO accion_apoyo(idPlan, idUsuarioResp, fecha, tipoAccion, descripcion, resultado, compromisos, proximaFecha, evidencia) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, a.getIdPlan());
            ps.setInt(2, a.getIdUsuarioResp());
            ps.setDate(3, java.sql.Date.valueOf(a.getFecha()));
            ps.setString(4, a.getTipoAccion());
            ps.setString(5, a.getDescripcion());
            ps.setString(6, a.getResultado());
            ps.setString(7, a.getCompromisos());
            if (a.getProximaFecha() != null && !a.getProximaFecha().isEmpty()) {
                ps.setDate(8, java.sql.Date.valueOf(a.getProximaFecha()));
            } else {
                ps.setNull(8, java.sql.Types.DATE);
            }
            ps.setString(9, a.getEvidencia());
            ps.executeUpdate();
        }
    }

    // metodo listar acciones de un plan
    public List<AccionApoyo> listarAccionesPorPlan(int idPlan) throws SQLException {
        List<AccionApoyo> lista = new ArrayList<>();
        String sql = "SELECT idAccion, idPlan, idUsuarioResp, fecha, tipoAccion, descripcion, resultado, compromisos, proximaFecha, evidencia FROM accion_apoyo WHERE idPlan = ? ORDER BY fecha DESC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPlan);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new AccionApoyo(
                        rs.getInt("idAccion"),
                        rs.getInt("idPlan"),
                        rs.getInt("idUsuarioResp"),
                        rs.getString("fecha"),
                        rs.getString("tipoAccion"),
                        rs.getString("descripcion"),
                        rs.getString("resultado"),
                        rs.getString("compromisos"),
                        rs.getString("proximaFecha"),
                        rs.getString("evidencia")
                    ));
                }
            }
        }
        return lista;
    }

    // metodo formal para cerrar un seguimiento
    public void cerrarSeguimiento(CierreSeguimiento c) throws SQLException {
        String sqlInsert = "INSERT INTO cierre_seguimiento(idPlan, idUsuarioCierra, fechaCierre, motivoCierre, resultado, observaciones) "
                         + "VALUES(?, ?, ?, ?, ?, ?)";
        String sqlUpdatePlan = "UPDATE plan_seguimiento SET estado = 'Cerrado' WHERE idPlan = ?";
        try (Connection cn = ConexionDB.abrir()) {
            cn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = cn.prepareStatement(sqlInsert)) {
                    ps.setInt(1, c.getIdPlan());
                    ps.setInt(2, c.getIdUsuarioCierra());
                    ps.setDate(3, java.sql.Date.valueOf(c.getFechaCierre()));
                    ps.setString(4, c.getMotivoCierre());
                    ps.setString(5, c.getResultado());
                    ps.setString(6, c.getObservaciones());
                    ps.executeUpdate();
                }
                try (PreparedStatement ps2 = cn.prepareStatement(sqlUpdatePlan)) {
                    ps2.setInt(1, c.getIdPlan());
                    ps2.executeUpdate();
                }
                cn.commit();
            } catch (SQLException ex) {
                cn.rollback();
                throw ex;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }
}