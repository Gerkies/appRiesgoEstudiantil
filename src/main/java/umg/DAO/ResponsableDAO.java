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
import umg.modelo.Responsable;

/**
 *
 * @author jsura
 */
public class ResponsableDAO {

    // metodo insertar responsable
    public void insertar(Responsable r) throws SQLException {
        String sql = "INSERT INTO responsable(codigoResponsable, dpi, nombreCompleto, parentesco, direccion, telefono, correo, ocupacion, observaciones) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, r.getCodigoResponsable());
            ps.setString(2, r.getDpi());
            ps.setString(3, r.getNombreCompleto());
            ps.setString(4, r.getParentesco());
            ps.setString(5, r.getDireccion());
            ps.setString(6, r.getTelefono());
            ps.setString(7, r.getCorreoElectronico());
            ps.setString(8, r.getOcupacion());
            ps.setString(9, r.getObservaciones());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el responsable.");
            }
        }
    }

    // metodo actualizar responsable
    public void actualizar(Responsable r) throws SQLException {
        String sql = "UPDATE responsable SET codigoResponsable = ?, dpi = ?, nombreCompleto = ?, parentesco = ?, direccion = ?, telefono = ?, correo = ?, ocupacion = ?, observaciones = ? "
                   + "WHERE idResponsable = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, r.getCodigoResponsable());
            ps.setString(2, r.getDpi());
            ps.setString(3, r.getNombreCompleto());
            ps.setString(4, r.getParentesco());
            ps.setString(5, r.getDireccion());
            ps.setString(6, r.getTelefono());
            ps.setString(7, r.getCorreoElectronico());
            ps.setString(8, r.getOcupacion());
            ps.setString(9, r.getObservaciones());
            ps.setInt(10, r.getIdResponsable());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar el responsable.");
            }
        }
    }

    // metodo eliminar responsable
    public void eliminar(int idResponsable) throws SQLException {
        String sql = "DELETE FROM responsable WHERE idResponsable = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idResponsable);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar el responsable.");
            }
        }
    }

    // metodo listar todos los responsables
    public List<Responsable> listar() throws SQLException {
        List<Responsable> lista = new ArrayList<>();
        String sql = "SELECT idResponsable, codigoResponsable, dpi, nombreCompleto, parentesco, direccion, telefono, correo, ocupacion, observaciones FROM responsable ORDER BY idResponsable ASC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Responsable r = new Responsable(
                    rs.getInt("idResponsable"),
                    rs.getString("codigoResponsable"),
                    rs.getString("dpi"),
                    rs.getString("nombreCompleto"),
                    rs.getString("parentesco"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("ocupacion"),
                    rs.getString("observaciones")
                );
                lista.add(r);
            }
        }
        return lista;
    }
}
