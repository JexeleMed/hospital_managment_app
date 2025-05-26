package hospital_managment_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Prosty interfejs tekstowy do zarządzania pacjentami.
 * Wykorzystuje switch-expression (Java 17+) z yield.
 */
public class OsobyGUI {

    private static final BufferedReader IN =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Szpital szpital = Szpital.getInstance();
        OsobyDodaj osobyDodaj = new OsobyDodaj();

        loop:
        while (true) {
            Action action = menu();   // <- tutaj używamy switch-expression
            switch (action) {
                case FINDbyId -> {
                    szpital.loadAllData();
                    System.out.println("Podaj id: ");
                    String id = IN.readLine();
                    szpital.pokazOsobe(Integer.parseInt(id));
                }

                case FINDbyName -> {
                    szpital.loadAllData();
                    System.out.println("Podaj imie: ");
                    String imie = IN.readLine();
                    System.out.println("Podaj nazwisko: ");
                    String nazwisko = IN.readLine();
                    szpital.pokazOsobe(imie, nazwisko);
                }
                case FINDbyPesel -> {
                    szpital.loadAllData();
                    System.out.println("Podaj PESEL: ");
                    String pesel = IN.readLine();
                    szpital.pokazOsobe(pesel);

                }
                case ADD -> {
                    osobyDodaj.osobyDodaj();
                }
                case DELETEbyId -> {
                    szpital.loadAllData();
                    System.out.println("Podaj id: ");
                    String id = IN.readLine();
                    szpital.usunOsobe(Integer.parseInt(id));
                }

                case DELETEbyName -> {
                    szpital.loadAllData();
                    System.out.println("Podaj imie: ");
                    String imie = IN.readLine();
                    System.out.println("Podaj nazwisko: ");
                    String nazwisko = IN.readLine();
                    szpital.usunOsobe(imie, nazwisko);
                }
                case DeletebyPesel -> {
                    szpital.loadAllData();
                    System.out.println("Podaj PESEL: ");
                    String pesel = IN.readLine();
                    szpital.usunOsobe(pesel);

                }
                case EXIT -> {
                    System.out.println("Powrot do menu!");
                    break loop;
                }
            }
            System.out.println(); // pusty wiersz między operacjami
        }
    }

    /** =======  MENU z użyciem switch-expression + yield  ======= */
    private static Action menu() throws IOException {
        System.out.println("""
                \n=== ZARZĄDZANIE PACJENTAMI ===
                1. Znajdz po ID
                2. Znajdz po imieniu i nazwisku
                3. Znajdz po nr PESEL
                4. Dodaj
                5. Usun po ID
                6. Usun po imieniu i nazwisku
                7. Usun po nr PESEL
                0. Wyjście
                """);

        System.out.print("Twój wybór: ");
        String wybor = IN.readLine().trim();

        // switch-expression ↓↓↓
        return switch (wybor) {
            case "1"  -> Action.FINDbyId;
            case "2"  -> Action.FINDbyName;
            case "3"  -> Action.FINDbyPesel;
            case "4"  -> Action.ADD;
            case "5"  -> Action.DELETEbyId;
            case "6"  -> Action.DELETEbyName;
            case "7"  -> Action.DeletebyPesel;
            case "0"  -> Action.EXIT;
            default   -> {
                System.out.println("Nieznana opcja!");
                yield menu();          // REKURENCJA: pokaż menu ponownie
            }
        };
    }

    /** Prosty enum opisujący możliwe akcje */
    private enum Action { FINDbyId, FINDbyName, FINDbyPesel, ADD, DELETEbyId, DELETEbyName, DeletebyPesel, EXIT }

}
