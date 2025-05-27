package hospital_managment_app;

import java.util.*;

public class Poczekalnia extends Pomieszczenie {

    // konstruktor klasy Poczekalnia
    public Poczekalnia(int numer, int pietro, int pojemnoscSali) {
        super(numer, pietro, pojemnoscSali);
    }

    // przypisz pacjenta do poczekalni
    @Override
    protected void przypiszPacjenta(Pacjent pacjent) {
        if (czyWolne()) {
            if (pacjent.getPriorytet() == null) {
                System.out.println("Prosze okreslic priorytet pacjenta (1-10):");
                Scanner scanner = new Scanner(System.in);
                int priorytet = scanner.nextInt();
                pacjent.ustawPriorytet(priorytet);
            }
            super.przypiszPacjenta(pacjent);
            System.out.println("Pacjent oczekuje w poczekalni z priorytetem: " + pacjent.getPriorytet());
        } else {
            System.out.println("Poczekalnia jest pelna!");
        }
    }

    // wyswietl pacjentow w poczekalni wraz z ich priorytetami
    protected void wyswietlPacjentowPoczekalni(Map<Integer, Pacjent> pacjentMap, PrzypisaniePacjentowHandler przypisanieHandler) {
        System.out.println("Lista pacjentow w poczekalni (sortowana po priorytecie rosnaco):");

        List<Pacjent> pacjenciWPoczekalni = new ArrayList<>();

        // Znajdz wszystkich pacjentow przypisanych do tej poczekalni
        for (Map.Entry<Integer, Integer> entry : przypisanieHandler.getPrzypisania().entrySet()) {
            if (entry.getValue() == this.idPomieszczenia) {
                Pacjent pacjent = pacjentMap.get(entry.getKey());
                if (pacjent != null) {
                    pacjenciWPoczekalni.add(pacjent);
                }
            }
        }

        // Sortuj po priorytecie (rosnaco)
        pacjenciWPoczekalni.sort(Comparator.comparing(
                p -> p.getPriorytet() != null ? p.getPriorytet() : 0
        ));

        // Wyswietl posortowana liste
        for (Pacjent p : pacjenciWPoczekalni) {
            System.out.printf("ID: %d, Imie: %s, Nazwisko: %s, Priorytet: %d%n",
                    p.idJednostki,
                    p.imie,
                    p.nazwisko,
                    p.getPriorytet() != null ? p.getPriorytet() : 0);
        }
    }

    // generuj raport o pacjentach w poczekalni
    @Override
    protected void generujRaport(Map<Integer, Pacjent> pacjentMap) {
        super.generujRaport(pacjentMap);
    }
}