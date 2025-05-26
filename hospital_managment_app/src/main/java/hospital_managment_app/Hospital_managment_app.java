package hospital_managment_app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;

public class Hospital_managment_app {
    public static void main(String[] args) {
        // Get Szpital instance using Singleton pattern
        Szpital szpital = Szpital.getInstance();

        // Create InterfejGraficzny without passing szpital
        // (it will get the instance internally)
        InterfejGraficzny interfejs = new InterfejGraficzny();
//        interfejs.start();
    }
}