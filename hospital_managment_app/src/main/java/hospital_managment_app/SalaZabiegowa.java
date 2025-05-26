package hospital_managment_app;

import java.util.Map;

public class SalaZabiegowa extends Pomieszczenie{
    protected String opisOperacji;
    protected String specjalnaAparatura;

    // Konstruktor domyślny — zablokowany
    public SalaZabiegowa(int numer, int pietro, int pojemnoscSali, String opisOperacji, String specjalnaAparatura) {
        super(numer, pietro, pojemnoscSali);
        this.opisOperacji = opisOperacji;
        this.specjalnaAparatura = specjalnaAparatura;
    }

    // Metoda do przypisywania pacjenta do sali zabiegowej
    @Override
    protected void przypiszPacjenta(Pacjent pacjent) {
        if (czyWolne()) {
            super.przypiszPacjenta(pacjent);
            System.out.println("Pacjent przypisany do sali zabiegowej.");
        } else {
            System.out.println("Sala zabiegowa jest pełna!");
        }
    }

    // Metoda do wyświetlania szczegółowego opisu sali zabiegowej
    protected void wyswietlOpis(){
        System.out.println("Sala zabiegowa");
        this.lokalizacja();
        System.out.println("Opis sali zabiegowej: ");
        System.out.println(opisOperacji);
        System.out.println("Specjalna aparatura zabiegowej: ");
        System.out.println(specjalnaAparatura);
    }

    // Metoda do generowania raportu z sali zabiegowej
    @Override
    protected void generujRaport(Map<Integer, Pacjent> pacjentMap) {
        super.generujRaport(pacjentMap);
        System.out.println("Opis operacji: " + opisOperacji);
        System.out.println("Specjalistyczna aparatura w pomieszczeniu: " + specjalnaAparatura);
    }
}
