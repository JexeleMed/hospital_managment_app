// Klasa odpowiadajaca za interfejs graficzny
package hospital_managment_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InterfejGraficzny {
    // Deklaracja bufora oraz intancjowanie szpitala
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private final Szpital szpital;
    private final PomieszczeniaGUI pomieszczeniaGUI;
    private final OsobyGUI osobyGUI;

    public InterfejGraficzny() {
        this.szpital = Szpital.getInstance();
        this.pomieszczeniaGUI = new PomieszczeniaGUI();
        this.osobyGUI = new OsobyGUI();
    }

    // Petla menu startowego
    public void start() throws IOException {
        loop:
        while (true) {
            Action action = menu();
            try {
                switch (action) {
                    case POMIESZCZENIA -> pomieszczeniaGUI.start();
                    case OSOBY -> osobyGUI.main(null);
                    case PRZYPISZ_PACJENTA -> przypiszPacjentaDoSali();
                    case PRZENIES_PACJENTA -> przeniesPacjenta();
                    case USUN_Z_SALI -> usunPacjentaZSali();
                    case SEARCH -> wyszukajOsobe();
                    case EXIT -> {
                        System.out.println("Do widzenia!");
                        break loop;
                    }
                }
                szpital.saveAllData();
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Błąd: Wprowadzono nieprawidłowy format liczby!");
            } catch (Exception e) {
                System.out.println("Błąd: " + e.getMessage());
            }
        }
    }

    // Przypisywanie pacjentow do sal
    private void przypiszPacjentaDoSali() throws IOException {
        try {
            System.out.println("\n=== PRZYPISYWANIE PACJENTA DO SALI ===");
            System.out.print("Podaj ID pacjenta: ");
            int idPacjenta = Integer.parseInt(IN.readLine());

            System.out.print("Podaj ID pomieszczenia: ");
            int idPomieszczenia = Integer.parseInt(IN.readLine());

            Pacjent pacjent = szpital.listaPacjentow.stream()
                    .filter(p -> p.idJednostki == idPacjenta)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono pacjenta o ID: " + idPacjenta));

            szpital.przypiszPacjenta(pacjent, idPomieszczenia);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ID musi być liczbą całkowitą!");
        }
    }

    // Przenoszenie pacjentow miedzy salami
    private void przeniesPacjenta() throws IOException {
        try {
            System.out.println("\n=== PRZENOSZENIE PACJENTA ===");
            System.out.print("Podaj ID pacjenta: ");
            int idPacjenta = Integer.parseInt(IN.readLine());

            System.out.print("Podaj ID sali źródłowej: ");
            int idZrodlo = Integer.parseInt(IN.readLine());

            System.out.print("Podaj ID sali docelowej: ");
            int idCel = Integer.parseInt(IN.readLine());

            szpital.przeniesDoSali(idPacjenta, idZrodlo, idCel);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ID musi być liczbą całkowitą!");
        }
    }

    //Usuwanie pacjentow z sal
    private void usunPacjentaZSali() throws IOException {
        try {
            System.out.println("\n=== USUWANIE PACJENTA Z SALI ===");
            System.out.print("Podaj ID pacjenta: ");
            int idPacjenta = Integer.parseInt(IN.readLine());
            szpital.usunZSali(idPacjenta);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ID musi być liczbą całkowitą!");
        }
    }
    // Wyszukiwanie osob
    private void wyszukajOsobe() throws IOException {
        System.out.println("\n=== WYSZUKIWANIE OSOBY ===");
        System.out.println("1. Wyszukaj po PESEL");
        System.out.println("2. Wyszukaj po imieniu i nazwisku");
        System.out.println("3. Wyszukaj po ID");

        System.out.print("Wybór: ");
        String wybor = IN.readLine();
        // Rozne sposoby wyszukiwania osob
        try {
            switch (wybor) {
                case "1" -> {
                    System.out.print("Podaj PESEL: ");
                    String pesel = IN.readLine();
                    if (pesel == null || pesel.trim().isEmpty()) {
                        throw new IllegalArgumentException("PESEL nie może być pusty!");
                    }
                    szpital.pokazOsobe(pesel);
                }
                case "2" -> {
                    System.out.print("Podaj imię: ");
                    String imie = IN.readLine();
                    System.out.print("Podaj nazwisko: ");
                    String nazwisko = IN.readLine();
                    if (imie == null || imie.trim().isEmpty() || nazwisko == null || nazwisko.trim().isEmpty()) {
                        throw new IllegalArgumentException("Imię i nazwisko nie mogą być puste!");
                    }
                    szpital.pokazOsobe(imie, nazwisko);
                }
                case "3" -> {
                    System.out.print("Podaj ID: ");
                    int id = Integer.parseInt(IN.readLine());
                    szpital.pokazOsobe(id);
                }
                default -> System.out.println("Nieprawidłowa opcja!");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ID musi być liczbą całkowitą!");
        }
    }

    // Action MENU bazujace na switch i enum
    private Action menu() throws IOException {
        System.out.println("""
                \n=== SYSTEM ZARZĄDZANIA SZPITALEM ===
                1. Zarządzanie pomieszczeniami
                2. Zarządzanie osobami
                3. Przypisz pacjenta do sali
                4. Przenieś pacjenta między salami
                5. Usuń pacjenta z sali
                6. Szybkie wyszukiwanie osoby
                0. Wyjście
                """);

        System.out.print("Wybór: ");
        String input = IN.readLine().trim();
        try {
            return switch (input) {
                case "1" -> Action.POMIESZCZENIA;
                case "2" -> Action.OSOBY;
                case "3" -> Action.PRZYPISZ_PACJENTA;
                case "4" -> Action.PRZENIES_PACJENTA;
                case "5" -> Action.USUN_Z_SALI;
                case "6" -> Action.SEARCH;
                case "0" -> Action.EXIT;
                default -> {
                    System.out.println("Nieprawidłowa opcja!");
                    yield menu();
                }
            };
        } catch (Exception e) {
            System.out.println("⚠ Błąd: " + e.getMessage());
            return menu();
        }
    }

    private enum Action {
        POMIESZCZENIA, OSOBY, PRZYPISZ_PACJENTA, PRZENIES_PACJENTA,
        USUN_Z_SALI, SEARCH, EXIT
    }
}