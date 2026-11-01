package umg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import umg.modelo.Estudiante;

public class EstudianteDAO {

    // metodo insertar estudiante
    public void insertar(Estudiante e) throws SQLException {
        String sql = "INSERT INTO estudiante(carne, nombres, apellidos, fechaNacimiento, sexo, direccion, comunidad, telefono, fechaInscripcion, idGrupo, estadoAcademico, fotografia, observaciones) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, e.getCarne());
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getApellidos());
            ps.setDate(4, ConexionDB.parseFecha(e.getFechaNacimiento()));
            ps.setString(5, e.getSexo());
            ps.setString(6, e.getDireccion());
            ps.setString(7, e.getComunidad());
            ps.setString(8, e.getTelefono());
            ps.setDate(9, ConexionDB.parseFecha(e.getFechaInscripcion()));
            ps.setInt(10, e.getIdGrupo());
            ps.setString(11, e.getEstadoAcademico());
            ps.setString(12, e.getFotografia());
            ps.setString(13, e.getObservaciones());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo insertar el estudiante.");
            }
        }
    }

    // metodo actualizar estudiante
    public void actualizar(Estudiante e) throws SQLException {
        String sql = "UPDATE estudiante SET carne = ?, nombres = ?, apellidos = ?, fechaNacimiento = ?, sexo = ?, direccion = ?, comunidad = ?, telefono = ?, fechaInscripcion = ?, idGrupo = ?, estadoAcademico = ?, fotografia = ?, observaciones = ? "
                   + "WHERE idEstudiante = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, e.getCarne());
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getApellidos());
            ps.setDate(4, ConexionDB.parseFecha(e.getFechaNacimiento()));
            ps.setString(5, e.getSexo());
            ps.setString(6, e.getDireccion());
            ps.setString(7, e.getComunidad());
            ps.setString(8, e.getTelefono());
            ps.setDate(9, ConexionDB.parseFecha(e.getFechaInscripcion()));
            ps.setInt(10, e.getIdGrupo());
            ps.setString(11, e.getEstadoAcademico());
            ps.setString(12, e.getFotografia());
            ps.setString(13, e.getObservaciones());
            ps.setInt(14, e.getIdEstudiante());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar el estudiante.");
            }
        }
    }

    // metodo eliminar estudiante
    public void eliminar(int idEstudiante) throws SQLException {
        String sql = "DELETE FROM estudiante WHERE idEstudiante = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idEstudiante);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo eliminar el estudiante.");
            }
        }
    }

    // metodo listar todos los estudiantes
    public List<Estudiante> listar() throws SQLException {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT idEstudiante, carne, nombres, apellidos, fechaNacimiento, sexo, direccion, comunidad, telefono, fechaInscripcion, idGrupo, estadoAcademico, fotografia, observaciones FROM estudiante ORDER BY idEstudiante ASC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Estudiante e = new Estudiante(
                    rs.getInt("idEstudiante"),
                    rs.getString("carne"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("fechaNacimiento"),
                    rs.getString("sexo"),
                    rs.getString("direccion"),
                    rs.getString("comunidad"),
                    rs.getString("telefono"),
                    rs.getString("fechaInscripcion"),
                    rs.getInt("idGrupo"),
                    rs.getString("estadoAcademico"),
                    rs.getString("fotografia"),
                    rs.getString("observaciones")
                );
                lista.add(e);
            }
        }
        return lista;
    }

    // metodo para listar estudiantes filtrados por grupo
    public List<Estudiante> listarPorGrupo(int idGrupo) throws SQLException {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT idEstudiante, carne, nombres, apellidos, fechaNacimiento, sexo, direccion, comunidad, telefono, fechaInscripcion, idGrupo, estadoAcademico, fotografia, observaciones FROM estudiante WHERE idGrupo = ? ORDER BY apellidos ASC";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idGrupo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Estudiante e = new Estudiante(
                        rs.getInt("idEstudiante"),
                        rs.getString("carne"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("fechaNacimiento"),
                        rs.getString("sexo"),
                        rs.getString("direccion"),
                        rs.getString("comunidad"),
                        rs.getString("telefono"),
                        rs.getString("fechaInscripcion"),
                        rs.getInt("idGrupo"),
                        rs.getString("estadoAcademico"),
                        rs.getString("fotografia"),
                        rs.getString("observaciones")
                    );
                    lista.add(e);
                }
            }
        }
        return lista;
    }
}
