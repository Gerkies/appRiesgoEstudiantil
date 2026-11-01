/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package umg.UI;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author jsura
 */
public class AppRiesgoEstudiantil {

    public static void main(String[] args) {
        // establecer el look and feel del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            // si falla continua con el look and feel por defecto
        }

        // arrancar el formulario de login en el hilo de swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmLogin login = new FrmLogin();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            }
        });
    }
}
