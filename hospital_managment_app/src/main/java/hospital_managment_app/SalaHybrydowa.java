package hospital_managment_app;

import java.time.LocalDate;
import java.util.Map;

public class SalaHybrydowa extends Pomieszczenie{
    private Oddzial oddzial;

    // Konstruktor domyślny — zablokowany
    public SalaHybrydowa(int numer, int pietro, int pojemnoscSali, Oddzial oddzial) {
        super(numer, pietro, pojemnoscSali);
        this.oddzial = oddzial;
    }

    // przypisanie pacjenta do sali hybrydowej
    @Override
    public void przypiszPacjenta(Pacjent pacjent) {
        if (czyWolne()) {
            super.przypiszPacjenta(pacjent);
            System.out.println("Pacjent przypisany do sali hybrydowej.");
        } else {
            System.out.println("Sala hybrydowa jest pełna!");
        }
    }

    //metoda do wypisania pacjenta z sali hybrydowej
    public void dodajWypis(String id){
        oddzial.dodajWypis(id, LocalDate.now());
        System.out.println("Wypisano pacjenta o id: " + id + "w dniu: " + LocalDate.now());

    }

    // Metoda do wyświetlania szczegółowego opisu sali hybrydowej podczas generowania raportu
    @Override
    public void generujRaport(Map<Integer, Pacjent> pacjentMap){
        super.generujRaport(pacjentMap);
        System.out.println("Typ sali: Hybrydowa");
    }
}
