package hospital_managment_app;

import java.util.*;

abstract class Pomieszczenie {
    protected int numer;
    protected int pietro;
    protected int pojemnoscSali;
    protected int aktualnaPojemnosc;
    protected List<Integer> pacjenci = new ArrayList<>();


    public Pomieszczenie() {
        throw new UnsupportedOperationException("Brak danych");
    }

    public Pomieszczenie(int numer, int pietro, int pojemnoscSali){
        this.numer = numer;
        this.pietro = pietro;
        this.pojemnoscSali = pojemnoscSali;
        this.aktualnaPojemnosc = 0;
    }

    public void dodajDoSali(int id) {
        if (aktualnaPojemnosc < pojemnoscSali) {
            pacjenci.add(id);
            aktualnaPojemnosc++;
        } else {
            System.out.println("Sala jest już pełna!");
        }
    }

    public boolean czyWolne() {
        return aktualnaPojemnosc < pojemnoscSali;
    }

    public boolean czyWolne(int iluPacjentow) {
        return (aktualnaPojemnosc + iluPacjentow) <= pojemnoscSali;
    }

    public void usunZSali(int id) {
        if (aktualnaPojemnosc > 0 && pacjenci.remove((Integer) id)) {
            aktualnaPojemnosc--;
        } else {
            System.out.println("Nie znaleziono pacjenta o ID: " + id);
        }
    }

    public void lokalizacja(){
        System.out.println("Pietro: " + pietro);
        System.out.println("Numer: " + numer);
        System.out.println("Aktualna pojemnosc: " + aktualnaPojemnosc);
        System.out.println("Pojemnosc: " + pojemnoscSali);
    }

    public void generujRaport(Map<Integer, Pacjent> pacjentMap){
        // Dodaj print pacjentow w tej sali, access po id, ale print imie nazwisko pesel i historia choroby
        if (pacjenci.isEmpty()) {
            System.out.println("Brak pacjentów w sali");
        }
        else {
            System.out.println("Pacjenci w sali:");
            for (Integer id : pacjenci) {
                Pacjent p = pacjentMap.get(id);
                if (p != null) {
                    System.out.println("Imie: " + p.imie + ", Nazwisko: " + p.nazwisko);
                }
            }
        }
        System.out.println("Pietro: " + pietro);
        System.out.println("Numer sali: " + numer);
        System.out.println("Pojemnosc sali: " + pojemnoscSali);
        System.out.println("Aktualna Pojemnosc: " + aktualnaPojemnosc);
        System.out.println("Czy sala jest wolna: " + (czyWolne() ? "TAK" : "NIE"));
    }
}
