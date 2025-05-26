package hospital_managment_app;

import java.time.LocalDate;
import java.util.*;

public class Oddzial {
    protected int idOddzialu;
    protected String nazwa;
    protected List<Integer> personel;
    protected List<Integer> sale;
    protected static int idGlobalOddzialu;

    // Konstruktor klasy Oddzial
    public Oddzial(String nazwa) {
        idGlobalOddzialu++;
        this.idOddzialu = idGlobalOddzialu;
        this.nazwa = nazwa;
        this.personel = new ArrayList<>();
        this.sale = new ArrayList<>();
    }

    // doaj personel
    protected void dodajPersonel(int idPracownika) {
        if (!personel.contains(idPracownika)) {
            personel.add(idPracownika);
        }
    }

    // usuń personel
    protected void usunPersonel(int idPracownika) {
        personel.remove((Integer) idPracownika);
    }

    // dodaj sale
    protected void dodajSale(int idSali) {
        if (!sale.contains(idSali)) {
            sale.add(idSali);
        }
    }

    // usuń sale
    protected void usunSale(int idSali) {
        sale.remove((Integer) idSali);
    }

    // wyswietl informacje o oddziale
    protected void wyswietlInformacje() {
        System.out.println("ID Oddziału: " + idOddzialu);
        System.out.println("Nazwa: " + nazwa);
        System.out.println("Liczba personelu: " + personel.size());
        System.out.println("Liczba sal: " + sale.size());
    }
}