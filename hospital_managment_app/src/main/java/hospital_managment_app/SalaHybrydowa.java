package hospital_managment_app;

import java.time.LocalDate;
import java.util.Map;

public class SalaHybrydowa extends Pomieszczenie{
    private Oddzial oddzial;

    public SalaHybrydowa(int numer, int pietro, int pojemnoscSali, Oddzial oddzial) {
        super(numer, pietro, pojemnoscSali);
        this.oddzial = oddzial;
    }

    @Override
    public void przypiszPacjenta(Pacjent pacjent) {
        if (czyWolne()) {
            super.przypiszPacjenta(pacjent);
            System.out.println("Pacjent przypisany do sali hybrydowej.");
        } else {
            System.out.println("Sala hybrydowa jest pełna!");
        }
    }


    public void dodajWypis(String id){
        oddzial.dodajWypis(id, LocalDate.now());
        System.out.println("Wypisano pacjenta o id: " + id + "w dniu: " + LocalDate.now());

    }

    @Override
    public void generujRaport(Map<Integer, Pacjent> pacjentMap){
        super.generujRaport(pacjentMap);
        System.out.println("Typ sali: Hybrydowa");
    }
}
