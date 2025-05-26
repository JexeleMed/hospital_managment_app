package hospital_managment_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class PomieszczeniaGUI {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private final Szpital szpital;

    public PomieszczeniaGUI() {
        this.szpital = Szpital.getInstance();
    }

    public void start() throws IOException {
        loop:
        while (true) {
            Action action = menu();
            switch (action) {
                case LIST -> pokazWszystkiePomieszczenia();
                case ADD -> dodajPomieszczenie();
                case DELETE -> usunPomieszczenie();
                case REPORT -> generujRaport();
                case EXIT -> {
                    System.out.println("Wyjście z zarządzania pomieszczeniami.");
                    break loop;
                }
            }
            System.out.println(); // empty line between operations
        }
    }

    private void pokazWszystkiePomieszczenia() {
        System.out.println("\n=== LISTA POMIESZCZEŃ ===");
        for (Pomieszczenie p : szpital.listaPomieszczen) {
            System.out.println("\nID: " + p.idPomieszczenia);
            p.lokalizacja();
            System.out.println("Typ: " + p.getClass().getSimpleName());
            System.out.println("----------------------");
        }
    }

    private void dodajPomieszczenie() throws IOException {
        System.out.println("\n=== DODAWANIE POMIESZCZENIA ===");
        System.out.println("Wybierz typ pomieszczenia:");
        System.out.println("1. Sala Zabiegowa");
        System.out.println("2. Sala Hybrydowa");
        System.out.println("3. Poczekalnia");

        System.out.print("Wybór: ");
        String wybor = IN.readLine();

        System.out.print("Numer sali: ");
        int numer = Integer.parseInt(IN.readLine());

        System.out.print("Piętro: ");
        int pietro = Integer.parseInt(IN.readLine());

        System.out.print("Pojemność sali: ");
        int pojemnosc = Integer.parseInt(IN.readLine());

        Pomieszczenie pomieszczenie = null;

        switch (wybor) {
            case "1" -> {
                System.out.print("Opis operacji: ");
                String opisOperacji = IN.readLine();
                System.out.print("Specjalna aparatura: ");
                String aparatura = IN.readLine();
                pomieszczenie = new SalaZabiegowa(numer, pietro, pojemnosc, opisOperacji, aparatura);
            }
            case "2" -> {
                Oddzial oddzial = new Oddzial("Oddział ogólny"); // You might want to add oddzial selection
                pomieszczenie = new SalaHybrydowa(numer, pietro, pojemnosc, oddzial);
            }
            case "3" -> pomieszczenie = new Poczekalnia(numer, pietro, pojemnosc);
            default -> System.out.println("Nieprawidłowy wybór!");
        }

        if (pomieszczenie != null) {
            szpital.dodajPomieszczenie(pomieszczenie);
            System.out.println("Pomieszczenie dodane pomyślnie.");
        }
    }

    private void usunPomieszczenie() throws IOException {
        System.out.print("Podaj numer sali: ");
        int numer = Integer.parseInt(IN.readLine());

        System.out.print("Podaj piętro: ");
        int pietro = Integer.parseInt(IN.readLine());

        szpital.usunPomieszczenie(numer, pietro);
    }

    private void generujRaport() {
        System.out.println("\n=== RAPORT POMIESZCZEŃ ===");
        Map<Integer, Pacjent> pacjentMap = szpital.listaPacjentow.stream()
                .collect(java.util.stream.Collectors.toMap(p -> p.idJednostki, p -> p));

        for (Pomieszczenie p : szpital.listaPomieszczen) {
            System.out.println("\n=== Pomieszczenie ID: " + p.idPomieszczenia + " ===");
            p.generujRaport(pacjentMap);

            if (p instanceof Poczekalnia) {
                System.out.println("\nLista oczekujących pacjentów:");
                ((Poczekalnia) p).wyswietlPacjentowPoczekalni(pacjentMap, PrzypisaniePacjentowHandler.getInstance());
            }

            System.out.println("----------------------");
        }
    }

    private Action menu() throws IOException {
        System.out.println("""
                \n=== ZARZĄDZANIE POMIESZCZENIAMI ===
                1. Wyświetl wszystkie pomieszczenia
                2. Dodaj pomieszczenie
                3. Usuń pomieszczenie
                4. Generuj raport
                0. Wyjście
                """);

        System.out.print("Wybór: ");
        return switch (IN.readLine().trim()) {
            case "1" -> Action.LIST;
            case "2" -> Action.ADD;
            case "3" -> Action.DELETE;
            case "4" -> Action.REPORT;
            case "0" -> Action.EXIT;
            default -> {
                System.out.println("Nieprawidłowa opcja!");
                yield menu();
            }
        };
    }

    private enum Action {
        LIST, ADD, DELETE, REPORT, EXIT
    }
}