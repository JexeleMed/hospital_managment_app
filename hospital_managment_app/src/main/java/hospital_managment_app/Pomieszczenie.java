package hospital_managment_app;

import java.util.*;

abstract class Pomieszczenie {
    protected static int idGlobalPomieszczenia;
    protected int idPomieszczenia;
    protected int numer;
    protected int pietro;
    protected int pojemnoscSali;
    protected int aktualnaPojemnosc;

    // Lista przechowująca przypisanych pacjentów
    protected List<Integer> pacjenci = new ArrayList<>();

    // Konstruktor domyślny — zablokowany
    public Pomieszczenie() {
        throw new UnsupportedOperationException("Brak danych");
    }

    // Konstruktor ustawiający dane pomieszczenia
    public Pomieszczenie(int numer, int pietro, int pojemnoscSali){
        idGlobalPomieszczenia++;
        this.idPomieszczenia = idGlobalPomieszczenia;
        this.numer = numer;
        this.pietro = pietro;
        this.pojemnoscSali = pojemnoscSali;
        this.aktualnaPojemnosc = 0;
    }

    // Sprawdzenie, czy w sali jest wolne miejsce
    public boolean czyWolne() {
        return aktualnaPojemnosc < pojemnoscSali;
    }

    // Sprawdzenie, czy w sali jest miejsce dla określonej liczby pacjentów
    public boolean czyWolne(int iluPacjentow) {
        return (aktualnaPojemnosc + iluPacjentow) <= pojemnoscSali;
    }

    // Przypisanie pacjenta do sali
    public void przypiszPacjenta(Pacjent pacjent) {
        if (aktualnaPojemnosc < pojemnoscSali) {
            pacjenci.add(pacjent.idJednostki);
            aktualnaPojemnosc++;
        } else {
            System.out.println("Sala jest już pełna!");
        }
    }

    // Usunięcie pacjenta z sali
    public void usunZSali(int id) {
        if (aktualnaPojemnosc > 0 && pacjenci.remove((Integer) id)) {
            aktualnaPojemnosc--;
        } else {
            System.out.println("Nie znaleziono pacjenta o ID: " + id);
        }
    }

    // Wyświetlenie podstawowych informacji o lokalizacji sali
    public void lokalizacja(){
        System.out.println("Pietro: " + pietro);
        System.out.println("Numer: " + numer);
        System.out.println("Aktualna pojemnosc: " + aktualnaPojemnosc);
        System.out.println("Pojemnosc: " + pojemnoscSali);
    }

    // Generowanie raportu o sali i pacjentach
    public void generujRaport(Map<Integer, Pacjent> pacjentMap){
        System.out.println("Id pomieszczenia: " + idPomieszczenia);
        System.out.println("Pietro: " + pietro);
        System.out.println("Numer sali: " + numer);
        System.out.println("Pojemnosc sali: " + pojemnoscSali);
        System.out.println("Aktualna Pojemnosc: " + aktualnaPojemnosc);
        System.out.println("Czy sala jest wolna: " + (czyWolne() ? "TAK" : "NIE"));
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
    }
}
