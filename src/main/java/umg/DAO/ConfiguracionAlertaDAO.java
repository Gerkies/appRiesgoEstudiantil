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
import umg.modelo.ConfiguracionAlerta;

/**
 *
 * @author jsura
 */
public class ConfiguracionAlertaDAO {

    // metodo para listar todas las configuraciones de alertas
    public List<ConfiguracionAlerta> listar() throws SQLException {
        List<ConfiguracionAlerta> lista = new ArrayList<>();
        String sql = "SELECT idConfiguracion, nombreParametro, valorLimite, descripcion FROM configuracion_alerta ORDER BY idConfiguracion ASC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new ConfiguracionAlerta(
                    rs.getInt("idConfiguracion"),
                    rs.getString("nombreParametro"),
                    rs.getDouble("valorLimite"),
                    rs.getString("descripcion")
                ));
            }
        }
        return lista;
    }

    // metodo para obtener el valor numerico de un limite por su nombre
    public double obtenerValor(String nombreParametro, double valorPorDefecto) {
        String sql = "SELECT valorLimite FROM configuracion_alerta WHERE nombreParametro = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nombreParametro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("valorLimite");
                }
            }
        } catch (SQLException e) {
            // si ocurre un error retorna el valor por defecto
        }
        return valorPorDefecto;
    }

    // metodo para actualizar un limite especifico
    public void actualizar(int idConfiguracion, double valorLimite) throws SQLException {
        String sql = "UPDATE configuracion_alerta SET valorLimite = ? WHERE idConfiguracion = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDouble(1, valorLimite);
            ps.setInt(2, idConfiguracion);
            ps.executeUpdate();
        }
    }
}
