// Klasa panelu zarzadzania pacjentami
package hospital_managment_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OsobyGUI {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private final Szpital szpital;
    private final OsobyDodaj osobyDodaj;

    public OsobyGUI() {
        this.szpital = Szpital.getInstance();
        this.osobyDodaj = new OsobyDodaj();
    }

    public void main(String[] args) throws IOException {
        loop:
        while (true) {
            Action action = menu();
            switch (action) {
                case DODAJ -> osobyDodaj.osobyDodaj();
                case USUN -> usunOsobe();
                case EXIT -> {
                    break loop;
                }
            }
            szpital.saveAllData();
            System.out.println();
        }
    }

    private void usunOsobe() throws IOException {
        System.out.println("\n=== USUWANIE OSOBY ===");
        System.out.println("1. Usun po PESEL");
        System.out.println("2. Usun po imieniu i nazwisku");
        System.out.println("3. Usun po ID");

        System.out.print("Wybor: ");
        String wybor = IN.readLine();

        switch (wybor) {
            case "1" -> {
                System.out.print("Podaj PESEL: ");
                szpital.usunOsobe(IN.readLine());
            }
            case "2" -> {
                System.out.print("Podaj imie: ");
                String imie = IN.readLine();
                System.out.print("Podaj nazwisko: ");
                String nazwisko = IN.readLine();
                szpital.usunOsobe(imie, nazwisko);
            }
            case "3" -> {
                System.out.print("Podaj ID: ");
                szpital.usunOsobe(Integer.parseInt(IN.readLine()));
            }
            default -> System.out.println("Nieprawidlowa opcja!");
        }
    }
    // Action MENU bazujace na switch i enum
    private Action menu() throws IOException {
        System.out.println("""
                \n=== ZARZADZANIE OSOBAMI ===
                1. Dodaj osobe
                2. Usun osobe
                0. Wyjscie
                """);

        System.out.print("Wybor: ");
        return switch (IN.readLine().trim()) {
            case "1" -> Action.DODAJ;
            case "2" -> Action.USUN;
            case "0" -> Action.EXIT;
            default -> {
                System.out.println("Nieprawidlowa opcja!");
                yield menu();
            }
        };
    }

    private enum Action {
        DODAJ, USUN, EXIT
    }
}