// Klasa glowna - inicjuje cala aplikacje
package hospital_managment_app;

import java.io.IOException;

public class Hospital_managment_app {
    public static void main(String[] args) {
        try {
            Szpital szpital = Szpital.getInstance();
            InterfejGraficzny interfejs = new InterfejGraficzny();
            interfejs.start();
        } catch (IOException e) {
            System.err.println("Blad podczas uruchamiania aplikacji: " + e.getMessage());
            e.printStackTrace();
        }
    }
}