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
import umg.modelo.Curso;

/**
 *
 * @author jsura
 */
public class CursoDAO {

    // metodo insertar curso
    public void insertar(Curso c) throws SQLException {
        String sql = "INSERT INTO curso(codigoCurso, nombreCurso, area, estado) VALUES(?, ?, ?, ?)";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getCodigoCurso());
            ps.setString(2, c.getNombreCurso());
            ps.setString(3, c.getArea());
            ps.setString(4, c.getEstado());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el curso.");
            }
        }
    }

    // metodo actualizar curso
    public void actualizar(Curso c) throws SQLException {
        String sql = "UPDATE curso SET codigoCurso = ?, nombreCurso = ?, area = ?, estado = ? WHERE idCurso = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getCodigoCurso());
            ps.setString(2, c.getNombreCurso());
            ps.setString(3, c.getArea());
            ps.setString(4, c.getEstado());
            ps.setInt(5, c.getIdCurso());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar el curso.");
            }
        }
    }

    // metodo eliminar curso
    public void eliminar(int idCurso) throws SQLException {
        String sql = "DELETE FROM curso WHERE idCurso = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCurso);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar el curso.");
            }
        }
    }

    // metodo listar todos los cursos
    public List<Curso> listar() throws SQLException {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT idCurso, codigoCurso, nombreCurso, area, estado FROM curso ORDER BY idCurso ASC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Curso c = new Curso(
                    rs.getInt("idCurso"),
                    rs.getString("codigoCurso"),
                    rs.getString("nombreCurso"),
                    rs.getString("area"),
                    rs.getString("estado")
                );
                lista.add(c);
            }
        }
        return lista;
    }
}
