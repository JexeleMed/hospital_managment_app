package hospital_managment_app;

import java.time.Duration;
import java.util.Map;
import java.util.*;

public class Poczekalnia extends Pomieszczenie {
    private Duration czasOczekiwania;
    private Map<Integer, Integer> priorytetyPacjentow; // Map<PatientID, Priority>

    public Poczekalnia(int numer, int pietro, int pojemnoscSali) {
        super(numer, pietro, pojemnoscSali);
        this.czasOczekiwania = Duration.ZERO;
        this.priorytetyPacjentow = new HashMap<>();
    }

    @Override
    public void przypiszPacjenta(Pacjent pacjent) {
        if (czyWolne()) {
            super.przypiszPacjenta(pacjent);
            System.out.println("Proszę określić priorytet pacjenta (1-10):");
            Scanner scanner = new Scanner(System.in);
            int priorytet = scanner.nextInt();
            priorytetyPacjentow.put(pacjent.idJednostki, priorytet);
            System.out.println("Pacjent oczekuje w poczekalni z priorytetem: " + priorytet);
        } else {
            System.out.println("Poczekalnia jest pełna!");
        }
    }

    @Override
    public void usunZSali(int id) {
        super.usunZSali(id);
        priorytetyPacjentow.remove(id);
    }

    public void wyswietlKolejke(Map<Integer, Pacjent> pacjentMap) {
        System.out.println("Lista pacjentów według priorytetu:");
        pacjenci.stream()
                .sorted((id1, id2) -> priorytetyPacjentow.get(id2).compareTo(priorytetyPacjentow.get(id1)))
                .forEach(id -> {
                    Pacjent p = pacjentMap.get(id);
                    if (p != null) {
                        System.out.println("Imię: " + p.imie +
                                ", Nazwisko: " + p.nazwisko +
                                ", Priorytet: " + priorytetyPacjentow.get(id));
                    }
                });
    }

    @Override
    public void generujRaport(Map<Integer, Pacjent> pacjentMap) {
        super.generujRaport(pacjentMap);
        System.out.println("Czas oczekiwania: " + czasOczekiwania);
        System.out.println("\nPacjenci według priorytetu:");
        wyswietlKolejke(pacjentMap);
    }
}