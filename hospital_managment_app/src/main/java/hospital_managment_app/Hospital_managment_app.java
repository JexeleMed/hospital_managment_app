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
        List<Para<String, Integer>> perskrypcje = new ArrayList<>();
        perskrypcje.add(new Para<>("Lek A", 500)); // przykładowa preskrypcja
        Pacjent pacjent = new Pacjent("Jan", "Kowalski", "12345678901",
                "A+", "Orzechy", perskrypcje,
                "wegetariańska", "987654321",
                LocalDate.of(1985, 5, 15),
                "123456789", "jan.kowalski@email.com",
                "ul. Przykładowa 1");


        List<Pacjent> pacjents = new ArrayList<>();
        pacjents.add(pacjent);

        PacjentHandler pacjentHandler = PacjentHandler.getInstance();

        pacjentHandler.saveAll(pacjents);
    }



}

