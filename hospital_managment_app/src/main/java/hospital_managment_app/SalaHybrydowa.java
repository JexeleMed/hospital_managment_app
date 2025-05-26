package hospital_managment_app;

import java.time.LocalDate;
import java.util.Map;

public class SalaHybrydowa extends Pomieszczenie{
    protected Oddzial oddzial;

    // Konstruktor domyślny — zablokowany
    public SalaHybrydowa(int numer, int pietro, int pojemnoscSali, Oddzial oddzial) {
        super(numer, pietro, pojemnoscSali);
        this.oddzial = oddzial;
    }

    // przypisanie pacjenta do sali hybrydowej
    @Override
    protected void przypiszPacjenta(Pacjent pacjent) {
        if (czyWolne()) {
            super.przypiszPacjenta(pacjent);
            System.out.println("Pacjent przypisany do sali hybrydowej.");
        } else {
            System.out.println("Sala hybrydowa jest pełna!");
        }
    }


    // Metoda do wyświetlania szczegółowego opisu sali hybrydowej podczas generowania raportu
    @Override
    protected void generujRaport(Map<Integer, Pacjent> pacjentMap){
        super.generujRaport(pacjentMap);
        System.out.println("Typ sali: Hybrydowa");
    }
}
