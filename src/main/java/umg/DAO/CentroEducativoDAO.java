/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import umg.modelo.CentroEducativo;

/**
 *
 * @author jsura
 */
public class CentroEducativoDAO {

    // metodo para obtener la configuracion institucional unica
    public CentroEducativo obtenerConfiguracion() throws SQLException {
        String sql = "SELECT idCentro, nombreCentro, codigoInstitucional, direccion, telefono, correo, nombreDirector, nivelesAtendidos, logotipo, anoVigente FROM centro_educativo LIMIT 1";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new CentroEducativo(
                    rs.getInt("idCentro"),
                    rs.getString("codigoInstitucional"),
                    rs.getString("nombreCentro"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("nombreDirector"),
                    rs.getString("nivelesAtendidos"),
                    rs.getString("logotipo"),
                    rs.getInt("anoVigente")
                );
            }
        }
        return null;
    }

    // metodo para actualizar datos del centro educativo
    public void actualizar(CentroEducativo c) throws SQLException {
        String sql = "UPDATE centro_educativo SET nombreCentro = ?, codigoInstitucional = ?, direccion = ?, telefono = ?, correo = ?, nombreDirector = ?, nivelesAtendidos = ?, logotipo = ?, anoVigente = ? WHERE idCentro = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getNombreCentro());
            ps.setString(2, c.getCodigoInstitucional());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getCorreo());
            ps.setString(6, c.getNombreDirector());
            ps.setString(7, c.getNivelesAtendidos());
            ps.setString(8, c.getLogotipo());
            ps.setInt(9, c.getAnoVigente());
            ps.setInt(10, c.getIdCentro());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar el centro educativo.");
            }
        }
    }
}
