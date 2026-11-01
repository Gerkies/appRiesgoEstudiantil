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
import umg.modelo.*;

/**
 *
 * @author jsura
 */
public class UsuarioDAO {

    // metodo para autenticar usuario y retornar el objeto polimorfico segun rol
    public Usuario autenticar(String username, String clave) throws SQLException {
        String sql = "SELECT idUsuario, username, clave, nombreCompleto, rol, correo, telefono, estado "
                   + "FROM usuario WHERE username = ? AND clave = ? AND estado = 'Activo'";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, clave);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("idUsuario");
                    String user = rs.getString("username");
                    String pass = rs.getString("clave");
                    String nombre = rs.getString("nombreCompleto");
                    String rol = rs.getString("rol");
                    String correo = rs.getString("correo");
                    String tel = rs.getString("telefono");
                    String estado = rs.getString("estado");

                    // creacion polimorfica del objeto segun el rol
                    switch (rol) {
                        case "Administrador":
                            return new Administrador(id, user, pass, nombre, correo, tel, estado);
                        case "Docente":
                            return new Docente(id, user, pass, nombre, correo, tel, estado);
                        case "Orientador":
                            return new Orientador(id, user, pass, nombre, correo, tel, estado);
                        case "Coordinador academico":
                            return new CoordinadorAcademico(id, user, pass, nombre, correo, tel, estado);
                        case "Director":
                            return new Director(id, user, pass, nombre, correo, tel, estado);
                        default:
                            return new Usuario(id, user, pass, nombre, rol, correo, tel, estado);
                    }
                }
            }
        }
        return null; // credenciales invalidas o usuario inactivo
    }

    // metodo insertar nuevo usuario
    public void insertar(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuario(username, clave, nombreCompleto, rol, correo, telefono, estado) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getClave());
            ps.setString(3, u.getNombreCompleto());
            ps.setString(4, u.getRol());
            ps.setString(5, u.getCorreo());
            ps.setString(6, u.getTelefono());
            ps.setString(7, u.getEstado());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el usuario.");
            }
        }
    }

    // metodo actualizar usuario
    public void actualizar(Usuario u) throws SQLException {
        String sql = "UPDATE usuario SET username = ?, clave = ?, nombreCompleto = ?, rol = ?, correo = ?, telefono = ?, estado = ? WHERE idUsuario = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getClave());
            ps.setString(3, u.getNombreCompleto());
            ps.setString(4, u.getRol());
            ps.setString(5, u.getCorreo());
            ps.setString(6, u.getTelefono());
            ps.setString(7, u.getEstado());
            ps.setInt(8, u.getIdUsuario());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar el usuario.");
            }
        }
    }

    // metodo eliminar usuario
    public void eliminar(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar el usuario.");
            }
        }
    }

    // metodo listar todos los usuarios
    public List<Usuario> listar() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT idUsuario, username, clave, nombreCompleto, rol, correo, telefono, estado FROM usuario ORDER BY idUsuario ASC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("idUsuario"),
                    rs.getString("username"),
                    rs.getString("clave"),
                    rs.getString("nombreCompleto"),
                    rs.getString("rol"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("estado")
                );
                lista.add(u);
            }
        }
        return lista;
    }
}

