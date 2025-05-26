package hospital_managment_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Prosty interfejs tekstowy do zarządzania pacjentami.
 * Wykorzystuje switch-expression (Java 17+) z yield.
 */
public class PacjentGUI {

    private static final BufferedReader IN =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        PacjentHandler pacjentHandler = PacjentHandler.getInstance();

        loop:
        while (true) {
            Action action = menu();   // <- tutaj używamy switch-expression
            switch (action) {
                case LIST -> {
                    List<Pacjent> all = pacjentHandler.loadAll();
                    System.out.println("\n== LISTA PACJENTÓW ==");
                    all.forEach(System.out::println);
                }
                case ADD -> {
                    System.out.print("Imię: ");
                    String imie = IN.readLine();
                    System.out.print("Nazwisko: ");
                    String nazwisko = IN.readLine();
                    System.out.print("PESEL: ");
                    String pesel = IN.readLine();
                    // … pobierz resztę pól
                    Pacjent p = new Pacjent(imie, nazwisko, pesel);
                    List<Pacjent> all = pacjentHandler.loadAll();
                    all.add(p);
                    pacjentHandler.saveAll(all);
                    System.out.println("✅ Dodano pacjenta.");
                }
                case DELETE -> {
                    System.out.print("Podaj PESEL do usunięcia: ");
                    String pesel = IN.readLine();
                    List<Pacjent> all = pacjentHandler.loadAll();
                    boolean removed = all.removeIf(p -> p.getPesel().equals(pesel));
                    pacjentHandler.saveAll(all);
                    System.out.println(removed ? " Usunięto." : " Nie znaleziono pacjenta.");
                }
                case EXIT -> {
                    System.out.println("Do zobaczenia!");
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
                1. Wyświetl wszystkich
                2. Dodaj nowego
                3. Usuń pacjenta
                0. Wyjście
                """);

        System.out.print("Twój wybór: ");
        String wybor = IN.readLine().trim();

        // switch-expression ↓↓↓
        return switch (wybor) {
            case "1"  -> Action.LIST;
            case "2"  -> Action.ADD;
            case "3"  -> Action.DELETE;
            case "0"  -> Action.EXIT;
            default   -> {
                System.out.println("⛔ Nieznana opcja!");
                yield menu();          // REKURENCJA: pokaż menu ponownie
            }
        };
    }

    /** Prosty enum opisujący możliwe akcje */
    private enum Action { LIST, ADD, DELETE, EXIT }
}
