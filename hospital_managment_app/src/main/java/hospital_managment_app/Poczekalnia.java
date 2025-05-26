package hospital_managment_app;

import java.time.Duration;
import java.util.Map;
import java.util.*;

public class Poczekalnia extends Pomieszczenie {

    public Poczekalnia(int numer, int pietro, int pojemnoscSali) {
        super(numer, pietro, pojemnoscSali);
    }

    @Override
    protected void przypiszPacjenta(Pacjent pacjent) {
        if (czyWolne()) {
            if (pacjent.getPriorytet() == null) {
                System.out.println("Proszę określić priorytet pacjenta (1-10):");
                Scanner scanner = new Scanner(System.in);
                int priorytet = scanner.nextInt();
                pacjent.ustawPriorytet(priorytet);
            }
            super.przypiszPacjenta(pacjent);
            System.out.println("Pacjent oczekuje w poczekalni z priorytetem: " + pacjent.getPriorytet());
        } else {
            System.out.println("Poczekalnia jest pełna!");
        }
    }

    protected void wyswietlPacjentowPoczekalni(Map<Integer, Pacjent> pacjentMap, PrzypisaniePacjentowHandler przypisanieHandler) {
        System.out.println("Lista pacjentów w poczekalni (sortowana po priorytecie rosnąco):");

        List<Pacjent> pacjenciWPoczekalni = new ArrayList<>();

        // Znajdź wszystkich pacjentów przypisanych do tej poczekalni
        for (Map.Entry<Integer, Integer> entry : przypisanieHandler.getPrzypisania().entrySet()) {
            if (entry.getValue() == this.idPomieszczenia) {
                Pacjent pacjent = pacjentMap.get(entry.getKey());
                if (pacjent != null) {
                    pacjenciWPoczekalni.add(pacjent);
                }
            }
        }

        // Sortuj po priorytecie (rosnąco)
        pacjenciWPoczekalni.sort(Comparator.comparing(
                p -> p.getPriorytet() != null ? p.getPriorytet() : 0
        ));

        // Wyświetl posortowaną listę
        for (Pacjent p : pacjenciWPoczekalni) {
            System.out.printf("ID: %d, Imię: %s, Nazwisko: %s, Priorytet: %d%n",
                    p.idJednostki,
                    p.imie,
                    p.nazwisko,
                    p.getPriorytet() != null ? p.getPriorytet() : 0);
        }
    }

    @Override
    protected void generujRaport(Map<Integer, Pacjent> pacjentMap) {
        super.generujRaport(pacjentMap);
    }
}