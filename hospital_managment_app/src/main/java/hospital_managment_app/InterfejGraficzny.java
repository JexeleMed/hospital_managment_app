package hospital_managment_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InterfejGraficzny {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private final Szpital szpital;
    private final PomieszczeniaGUI pomieszczeniaGUI;
    private final OsobyGUI osobyGUI;

    public InterfejGraficzny() {
        this.szpital = Szpital.getInstance();
        this.pomieszczeniaGUI = new PomieszczeniaGUI();
        this.osobyGUI = new OsobyGUI();
    }

    public void start() throws IOException {
        loop:
        while (true) {
            Action action = menu();
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
        }
    }

    private void przypiszPacjentaDoSali() throws IOException {
        System.out.println("\n=== PRZYPISYWANIE PACJENTA DO SALI ===");
        System.out.print("Podaj ID pacjenta: ");
        int idPacjenta = Integer.parseInt(IN.readLine());

        System.out.print("Podaj ID pomieszczenia: ");
        int idPomieszczenia = Integer.parseInt(IN.readLine());

        szpital.przypiszPacjenta(
                szpital.listaPacjentow.stream()
                        .filter(p -> p.idJednostki == idPacjenta)
                        .findFirst()
                        .orElse(null),
                idPomieszczenia
        );
    }

    private void przeniesPacjenta() throws IOException {
        System.out.println("\n=== PRZENOSZENIE PACJENTA ===");
        System.out.print("Podaj ID pacjenta: ");
        int idPacjenta = Integer.parseInt(IN.readLine());

        System.out.print("Podaj ID sali źródłowej: ");
        int idZrodlo = Integer.parseInt(IN.readLine());

        System.out.print("Podaj ID sali docelowej: ");
        int idCel = Integer.parseInt(IN.readLine());

        szpital.przeniesDoSali(idPacjenta, idZrodlo, idCel);
    }

    private void usunPacjentaZSali() throws IOException {
        System.out.println("\n=== USUWANIE PACJENTA Z SALI ===");
        System.out.print("Podaj ID pacjenta: ");
        int idPacjenta = Integer.parseInt(IN.readLine());

        szpital.usunZSali(idPacjenta);
    }

    private void wyszukajOsobe() throws IOException {
        System.out.println("\n=== WYSZUKIWANIE OSOBY ===");
        System.out.println("1. Wyszukaj po PESEL");
        System.out.println("2. Wyszukaj po imieniu i nazwisku");
        System.out.println("3. Wyszukaj po ID");

        System.out.print("Wybór: ");
        String wybor = IN.readLine();

        switch (wybor) {
            case "1" -> {
                System.out.print("Podaj PESEL: ");
                szpital.pokazOsobe(IN.readLine());
            }
            case "2" -> {
                System.out.print("Podaj imię: ");
                String imie = IN.readLine();
                System.out.print("Podaj nazwisko: ");
                String nazwisko = IN.readLine();
                szpital.pokazOsobe(imie, nazwisko);
            }
            case "3" -> {
                System.out.print("Podaj ID: ");
                szpital.pokazOsobe(Integer.parseInt(IN.readLine()));
            }
            default -> System.out.println("Nieprawidłowa opcja!");
        }
    }

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
        return switch (IN.readLine().trim()) {
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
    }

    private enum Action {
        POMIESZCZENIA, OSOBY, PRZYPISZ_PACJENTA, PRZENIES_PACJENTA,
        USUN_Z_SALI, SEARCH, EXIT
    }
}