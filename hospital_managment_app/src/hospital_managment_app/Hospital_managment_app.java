/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital_managment_app;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kubam
 */
public class Hospital_managment_app {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hospital managment");

        LocalDate data = LocalDate.now();  // Bieżąca data


        List<Para<String, Integer>> recepta = new ArrayList<>();

        recepta.add(new Para<>("Paracetamol", 500));

        Pacjent pacjentTestowy = new Pacjent("Jan", "Nowak", "0013762830", "0 Rh-", "Phenylephrini hydrochloridum", recepta, "Standardowe", "666666666", data, "431234647", "lalala@gmail.com", "Liliowa 1");
        pacjentTestowy.wyswietlProfil();
        pacjentTestowy.dodajChorobe("Cukrzyca");
        pacjentTestowy.dodajChorobe("Bradykardia", LocalDate.parse("2007-12-03"));
        pacjentTestowy.wyswietlProfil();

    }
}
