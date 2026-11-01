/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jsura
 */
public class ConexionDB {
    
    private static final String URL
            = "jdbc:mysql://localhost:3306/db_seguimiento_escolar"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=America/Guatemala";
    
    private static final String USER = "root";
    private static final String PASS = "SuNuevaClave";
    
    public static Connection abrir() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * Parsea fechas en formatos comunes (YYYY-MM-DD, DD-MM-YYYY, DD/MM/YYYY)
     * hacia java.sql.Date para compatibilidad total sin alterar la base de datos.
     */
    public static java.sql.Date parseFecha(String fechaStr) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            return new java.sql.Date(System.currentTimeMillis());
        }
        String s = fechaStr.trim().replace('/', '-');
        if (s.matches("^\\d{1,2}-\\d{1,2}-\\d{4}$")) {
            String[] p = s.split("-");
            s = String.format("%04d-%02d-%02d", Integer.parseInt(p[2]), Integer.parseInt(p[1]), Integer.parseInt(p[0]));
        }
        try {
            return java.sql.Date.valueOf(s);
        } catch (Exception ex) {
            String[] formatos = {"yyyy-MM-dd", "dd-MM-yyyy", "dd/MM/yyyy", "yyyy/MM/dd"};
            for (String f : formatos) {
                try {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(f);
                    sdf.setLenient(false);
                    return new java.sql.Date(sdf.parse(fechaStr.trim()).getTime());
                } catch (Exception ignored) {}
            }
            throw new IllegalArgumentException("Formato de fecha inválido: '" + fechaStr + "'. Use AAAA-MM-DD o DD-MM-AAAA.");
        }
    }
}
