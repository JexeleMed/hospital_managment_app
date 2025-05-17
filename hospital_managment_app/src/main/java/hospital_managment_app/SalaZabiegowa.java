package hospital_managment_app;
import java.util.*;
import java.util.Scanner;

public class SalaZabiegowa extends Pomieszczenie{
    private String opisOperacji;
    private String specjalnaAparatura;

    public SalaZabiegowa(int numer, int pietro, int pojemnoscSali, String opisOperacji, String specjalnaAparatura) {
        super(numer, pietro, pojemnoscSali);
        this.opisOperacji = opisOperacji;
        this.specjalnaAparatura = specjalnaAparatura;
    }

    //czy da sie inaczej niz tworzyc metode lokalizacja
    protected void wyswietlOpis(){
        System.out.println("Sala zabiegowa");
        this.lokalizacja();
        System.out.println("Opis sali zabiegowej: ");
        System.out.println(opisOperacji);
        System.out.println("Specjalna aparatura zabiegowej: ");
        System.out.println(specjalnaAparatura);
    }

    @Override
    public void generujRaport(Map<Integer, Pacjent> pacjentMap) {
        super.generujRaport(pacjentMap);
        System.out.println("Opis operacji: " + opisOperacji);
        System.out.println("Specjalistyczna aparatura w pomieszczeniu: " + specjalnaAparatura);
    }
}
