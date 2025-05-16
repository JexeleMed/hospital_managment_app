package hospital_managment_app;
import java.time.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class Poczekalnia extends Pomieszczenie{
    private Duration czasOczekiwania;
    private HashMap<Integer, Integer> priorytetPacjentow;

    public Poczekalnia(int numer, int pietro, int pojemnoscSali) {
        super(numer, pietro, pojemnoscSali);
        this.czasOczekiwania = Duration.ZERO;
        this.priorytetPacjentow = new HashMap<>();
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
        System.out.println("Liczba pacjentów w poczekalni: " + priorytetPacjentow.size());
    }
}
