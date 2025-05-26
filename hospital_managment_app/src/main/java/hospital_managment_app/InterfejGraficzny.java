package hospital_managment_app;

import java.util.Scanner;



public class InterfejGraficzny {
    public static void main(String[] args) {
        System.out.println("---------Szpital---------");
        Scanner sc = new Scanner(System.in);
        boolean koniec = false;
        Szpital szpital = new Szpital();

        while (!koniec) {
            System.out.print("Wybierz (d – dodaj, u – usuń, w – wyświetl, q – koniec): ");
            String opcja = sc.nextLine();

            int kod = switch (opcja) {
                case "d" -> {
                    System.out.println("Dodaj");
                    yield 1;
                }        // blok + yield
                case "u" -> {
                    System.out.println("Usun");
                    yield 2;
                }
                case "w" -> {
                    System.out.println("Wyswietl");
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
