package hospital_managment_app;
import java.time.*;
import java.util.HashMap;


public class Poczekalnia extends Pomieszczenie{
    private Duration czasOczekiwania;
    private HashMap<Integer, Integer> priorytetPacjentow;

    public void wyswietlKolejke(){
        System.out.println("Lista priorytetow pacjentow:");
        //tu wstawic z czyczekanaprzyjecie i czlowiek
//        priorytetPacjentow.forEach(()) -> System.out.println("ID pacjenta: " + Czlowiek.idGlobal + ", Priorytet: " + );
    }

    @Override
    public void generujRaport(){
        super.generujRaport();
        System.out.println("Czas oczekiwania: " + czasOczekiwania);
        System.out.println("Liczba pacjentów w poczekalni: "+ priorytetPacjentow.size());
    }
}
