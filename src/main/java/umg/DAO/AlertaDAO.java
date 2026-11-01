/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import umg.modelo.Alerta;

/**
 *
 * @author jsura
 */
public class AlertaDAO {

    // metodo insertar alerta
    public void insertar(Alerta a) throws SQLException {
        String sql = "INSERT INTO alerta(numeroAlerta, idEstudiante, idUsuarioResp, fecha, tipoAlerta, motivo, prioridad, origenInformacion, estado, observaciones) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, a.getNumeroAlerta());
            ps.setInt(2, a.getIdEstudiante());
            ps.setInt(3, a.getIdUsuarioResp());
            ps.setDate(4, java.sql.Date.valueOf(a.getFecha()));
            ps.setString(5, a.getTipoAlerta());
            ps.setString(6, a.getMotivo());
            ps.setString(7, a.getPrioridad());
            ps.setString(8, a.getOrigenInformacion());
            ps.setString(9, a.getEstado());
            ps.setString(10, a.getObservaciones());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo registrar la alerta.");
            }
        }
    }

    // metodo actualizar alerta
    public void actualizar(Alerta a) throws SQLException {
        String sql = "UPDATE alerta SET numeroAlerta = ?, idEstudiante = ?, idUsuarioResp = ?, fecha = ?, tipoAlerta = ?, motivo = ?, prioridad = ?, origenInformacion = ?, estado = ?, observaciones = ? "
                   + "WHERE idAlerta = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, a.getNumeroAlerta());
            ps.setInt(2, a.getIdEstudiante());
            ps.setInt(3, a.getIdUsuarioResp());
            ps.setDate(4, java.sql.Date.valueOf(a.getFecha()));
            ps.setString(5, a.getTipoAlerta());
            ps.setString(6, a.getMotivo());
            ps.setString(7, a.getPrioridad());
            ps.setString(8, a.getOrigenInformacion());
            ps.setString(9, a.getEstado());
            ps.setString(10, a.getObservaciones());
            ps.setInt(11, a.getIdAlerta());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar la alerta.");
            }
        }
    }

    // metodo eliminar alerta
    public void eliminar(int idAlerta) throws SQLException {
        String sql = "DELETE FROM alerta WHERE idAlerta = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idAlerta);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar la alerta.");
            }
        }
    }

    // metodo listar todas las alertas
    public List<Alerta> listar() throws SQLException {
        List<Alerta> lista = new ArrayList<>();
        String sql = "SELECT idAlerta, numeroAlerta, idEstudiante, idUsuarioResp, fecha, tipoAlerta, motivo, prioridad, origenInformacion, estado, observaciones FROM alerta ORDER BY idAlerta DESC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Alerta a = new Alerta(
                    rs.getInt("idAlerta"),
                    rs.getString("numeroAlerta"),
                    rs.getInt("idEstudiante"),
                    rs.getInt("idUsuarioResp"),
                    rs.getString("fecha"),
                    rs.getString("tipoAlerta"),
                    rs.getString("motivo"),
                    rs.getString("prioridad"),
                    rs.getString("origenInformacion"),
                    rs.getString("estado"),
                    rs.getString("observaciones")
                );
                lista.add(a);
            }
        }
        return lista;
    }

    // metodo listar alertas pendientes de revision
    public List<Alerta> listarPendientes() throws SQLException {
        List<Alerta> lista = new ArrayList<>();
        String sql = "SELECT idAlerta, numeroAlerta, idEstudiante, idUsuarioResp, fecha, tipoAlerta, motivo, prioridad, origenInformacion, estado, observaciones FROM alerta WHERE estado = 'Pendiente de revision' ORDER BY idAlerta DESC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Alerta a = new Alerta(
                    rs.getInt("idAlerta"),
                    rs.getString("numeroAlerta"),
                    rs.getInt("idEstudiante"),
                    rs.getInt("idUsuarioResp"),
                    rs.getString("fecha"),
                    rs.getString("tipoAlerta"),
                    rs.getString("motivo"),
                    rs.getString("prioridad"),
                    rs.getString("origenInformacion"),
                    rs.getString("estado"),
                    rs.getString("observaciones")
                );
                lista.add(a);
            }
        }
        return lista;
    }

    // regla de negocio central: escaneo y generacion automatica de alertas segun limites configurables
    public int detectarYGenerarAlertasAutomaticas() throws SQLException {
        ConfiguracionAlertaDAO configDAO = new ConfiguracionAlertaDAO();
        double maxAusencias = configDAO.obtenerValor("MAX_AUSENCIAS", 5.0);
        double notaMinima = configDAO.obtenerValor("NOTA_MINIMA_APROBACION", 60.0);

        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int nuevasGeneradas = 0;

        try (Connection cn = ConexionDB.abrir()) {
            // 1. Deteccion de ausencias reiteradas
            String sqlAsis = "SELECT a.idEstudiante, COUNT(a.idAsistencia) AS totalAusencias "
                           + "FROM asistencia a "
                           + "WHERE a.estadoAsistencia = 'Ausente' "
                           + "GROUP BY a.idEstudiante "
                           + "HAVING totalAusencias >= ?";
            try (PreparedStatement psAsis = cn.prepareStatement(sqlAsis)) {
                psAsis.setDouble(1, maxAusencias);
                try (ResultSet rs = psAsis.executeQuery()) {
                    while (rs.next()) {
                        int idEst = rs.getInt("idEstudiante");
                        int aus = rs.getInt("totalAusencias");

                        // verificar si ya existe una alerta activa para este estudiante
                        String sqlCheck = "SELECT COUNT(*) FROM alerta WHERE idEstudiante = ? AND tipoAlerta = 'Ausencias reiteradas' AND estado IN ('Pendiente de revision', 'Confirmada', 'En seguimiento')";
                        try (PreparedStatement psCheck = cn.prepareStatement(sqlCheck)) {
                            psCheck.setInt(1, idEst);
                            try (ResultSet rsCheck = psCheck.executeQuery()) {
                                if (rsCheck.next() && rsCheck.getInt(1) == 0) {
                                    // insertar nueva alerta automatica
                                    String numAlerta = "ALT-ASIS-" + idEst + "-" + (System.currentTimeMillis() % 10000);
                                    String motivo = "Superó el límite permitido acumulando " + aus + " ausencias injustificadas (Límite: " + (int)maxAusencias + ").";
                                    Alerta nueva = new Alerta(0, numAlerta, idEst, 3, fechaActual, "Ausencias reiteradas", motivo, "Alto", "Registro de asistencia escolar", "Pendiente de revision", "Detección preventiva generada automáticamente.");
                                    insertar(nueva);
                                    nuevasGeneradas++;
                                }
                            }
                        }
                    }
                }
            }

            // 2. Deteccion de bajo rendimiento
            String sqlRend = "SELECT r.idEstudiante, COUNT(r.idRendimiento) AS cursosReprobados, AVG(r.notaObtenida) AS prom "
                           + "FROM rendimiento_academico r "
                           + "WHERE r.notaObtenida < ? "
                           + "GROUP BY r.idEstudiante "
                           + "HAVING cursosReprobados >= 1";
            try (PreparedStatement psRend = cn.prepareStatement(sqlRend)) {
                psRend.setDouble(1, notaMinima);
                try (ResultSet rs = psRend.executeQuery()) {
                    while (rs.next()) {
                        int idEst = rs.getInt("idEstudiante");
                        int rep = rs.getInt("cursosReprobados");
                        double prom = rs.getDouble("prom");

                        String sqlCheck = "SELECT COUNT(*) FROM alerta WHERE idEstudiante = ? AND tipoAlerta = 'Bajo rendimiento' AND estado IN ('Pendiente de revision', 'Confirmada', 'En seguimiento')";
                        try (PreparedStatement psCheck = cn.prepareStatement(sqlCheck)) {
                            psCheck.setInt(1, idEst);
                            try (ResultSet rsCheck = psCheck.executeQuery()) {
                                if (rsCheck.next() && rsCheck.getInt(1) == 0) {
                                    String numAlerta = "ALT-REND-" + idEst + "-" + (System.currentTimeMillis() % 10000);
                                    String motivo = "El alumno reprueba " + rep + " curso(s) con promedio de " + String.format("%.1f", prom) + " pts (Mínimo requerido: " + (int)notaMinima + " pts).";
                                    Alerta nueva = new Alerta(0, numAlerta, idEst, 3, fechaActual, "Bajo rendimiento", motivo, "Medio", "Registro de calificaciones del período", "Pendiente de revision", "Detección preventiva por bajo rendimiento.");
                                    insertar(nueva);
                                    nuevasGeneradas++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return nuevasGeneradas;
    }
}
