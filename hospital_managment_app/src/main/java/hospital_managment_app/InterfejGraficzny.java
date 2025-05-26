package hospital_managment_app;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;




public class InterfejGraficzny {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean koniec = false;
        Szpital szpital = new Szpital();
        PacjentHandler pacjentHandler = new PacjentHandler();
        LekarzHandler lekarzHandler = new LekarzHandler();
        PielegniarkaHandler pielegniarkaHandler = new PielegniarkaHandler();


        List<Para<String, Integer>> perskrypcje = new ArrayList<>();
        perskrypcje.add(new Para<>("Lek A", 500)); // przykładowa preskrypcja
        Pacjent pacjent = new Pacjent("Jan", "Kowalski", "12345678901",
                "A+", "Orzechy", perskrypcje,
                "wegetariańska", "987654321",
                LocalDate.of(1985, 5, 15),
                "123456789", "jan.kowalski@email.com",
                "ul. Przykładowa 1");

        Lekarz lekarzPelny = new Lekarz("Jan", "Szeregowy", "80031767916", LocalDate.of(80, 3, 17), "601765314", "ja.szeregowy@szpital.com", "Gronowa 12", "Onkolog", "Onko3131");
        Pielegniarka pielegniarka = new Pielegniarka("Anna", "Gruszka", "09211684689", "Opieka medyczna", "MED8873", "Dobieranie lekow", 3, true);
        System.out.println("---------Szpital---------");

        while (!koniec) {
            System.out.print("Wybierz (d – dodaj, u – usuń, w – wyświetl, q – koniec): ");
            String opcja = sc.nextLine();

            int kod = switch (opcja) {
                case "d" -> {
                   szpital.dodajOsobe(pacjent);
                   szpital.dodajOsobe(lekarzPelny);
                   szpital.dodajOsobe(pielegniarka);
                    try {
                        pacjentHandler.saveAll(szpital.listaPacjentow);
                        lekarzHandler.saveAll(szpital.listaLekarzy);
                        pielegniarkaHandler.saveAll(szpital.listaPielegniarek);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    yield 1;
                }        // blok + yield
                case "u" -> {
                    System.out.println("Usun");
                    yield 2;
                }
                case "w" -> {
                    System.out.println("Wyswietl");
                    szpital.pokazOsobe(1);
                    yield 3;
                }
                case "q", "0", "quit" -> {
                    koniec = true;
                    yield -1;
                }
                default -> {
                    System.out.println("Nieznana komenda");
                    yield 0;
                }
            };

            System.out.println("Zwrócony kod: " + kod);


        }
    }
}
