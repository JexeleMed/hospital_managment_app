package hospital_managment_app;

import java.time.Duration;
import java.util.Map;
import java.util.*;

public class Poczekalnia extends Pomieszczenie {
    private Duration czasOczekiwania;

    public Poczekalnia(int numer, int pietro, int pojemnoscSali) {
        super(numer, pietro, pojemnoscSali);
        this.czasOczekiwania = Duration.ZERO;
    }

    public void dodajDoPoczekalni(int idPacjenta) {
        dodajDoSali(idPacjenta);
    }

    public void wyswietlKolejke(Map<Integer, Pacjent> pacjentMap) {
        System.out.println("Lista pacjentów według priorytetu:");
        pacjenci.stream()
                .map(pacjentMap::get)
                .filter(p -> p != null)
                .sorted(Comparator.comparing(p -> p.getPriorytet() != null ? p.getPriorytet() : 10))
                .forEach(p -> System.out.println(
                        "Imię: " + p.imie +
                                ", Nazwisko: " + p.nazwisko +
                                ", Priorytet: " + (p.getPriorytet() != null ? p.getPriorytet() : "brak")
                ));
    }

    @Override
    public void generujRaport(Map<Integer, Pacjent> pacjentMap) {
        super.generujRaport(pacjentMap);
        System.out.println("Czas oczekiwania: " + czasOczekiwania);
        System.out.println("\nPacjenci według priorytetu:");
        wyswietlKolejke(pacjentMap);
    }
}