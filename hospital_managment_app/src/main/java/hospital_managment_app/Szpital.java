package hospital_managment_app;

import java.util.*;

public class Szpital {

    protected static String nazwaSzpitala = "Szpital";
    protected List<Oddzial> listaOddzialow;
    protected List<Pacjent> listaPacjentow;
    protected List<Lekarz> listaLekarzy;
    protected List<Pielegniarka> listaPielegniarek;

    // Szukanie osob
    public void pokazOsobe(String pesel){
        System.out.println("Pacjenci o tym nr PESEL: ");
        for(Pacjent p : listaPacjentow){
            if(p.pesel.equals(pesel)){
                p.wyswietlProfil();
                System.out.println("______________________");
            }
        }
        System.out.println("Lekarze o tym nr PESEL: ");
        for(Lekarz l : listaLekarzy){
            if(l.pesel.equals(pesel)){
                l.wyswietlProfil();
                System.out.println("______________________");
            }
        }
        System.out.println("Pielegniarki o tym nr PESEL: ");
        for(Pielegniarka p : listaPielegniarek){
            if(p.pesel.equals(pesel)){
                p.wyswietlProfil();
                System.out.println("______________________");
            }
        }
    }
    public void pokazOsobe(String imie, String nazwisko){
        System.out.println("Pacjenci o tym imieniu i nazwisku: ");
        for(Pacjent p : listaPacjentow){
            if(p.imie.equals(imie) && p.nazwisko.equals(nazwisko)){
                p.wyswietlProfil();
                System.out.println("______________________");
            }
        }
        System.out.println("Lekarz o tym imieniu i nazwisku: ");
        for(Lekarz l : listaLekarzy){
            if(l.imie.equals(imie) && l.nazwisko.equals(nazwisko)){
                l.wyswietlProfil();
                System.out.println("______________________");
            }
        }
        System.out.println("Pielegniarki o tym imieniu i nazwisku: ");
        for(Pielegniarka p : listaPielegniarek){
            if(p.imie.equals(imie) && p.nazwisko.equals(nazwisko)){
                p.wyswietlProfil();
                System.out.println("______________________");
            }
        }
    }
    public void pokazOsobe(int id){
        System.out.println("Pacjenci o tym id: ");
        for(Pacjent p : listaPacjentow){
            if(p.idJednostki == id){
                p.wyswietlProfil();
                System.out.println("______________________");
            }
        }
        System.out.println("Lekarze o tym id: ");
        for(Lekarz l : listaLekarzy){
            if(l.idJednostki == id){
                l.wyswietlProfil();
                System.out.println("______________________");
            }
        }
        System.out.println("Pielegniarki o tym id: ");
        for(Pielegniarka p : listaPielegniarek){
            if(p.idJednostki == id){
                p.wyswietlProfil();
                System.out.println("______________________");
            }
        }
    }

    // Dodawanie osob
    public void dodajOsobe(Pacjent p){
        listaPacjentow.add(p);
        System.out.println("Dodano pacjenta.");
    }
    public void dodajOsobe(Lekarz l){
        listaLekarzy.add(l);
        System.out.println("Dodano lekarza.");
    }
    public void dodajOsobe(Pielegniarka p){
        listaPielegniarek.add(p);
        System.out.println("Dodano pielegniarke.");
    }

    // Usuwanie osob
    public void usunOsobe(String pesel){
        listaPacjentow.removeIf(z -> z.pesel.equals(pesel));
        listaLekarzy.removeIf(z -> z.pesel.equals(pesel));
        listaPielegniarek.removeIf(z -> z.pesel.equals(pesel));
        System.out.println("Usunieto z bazy.");
    }
    public void usunOsobe(int id){
        listaPacjentow.removeIf(z -> z.idJednostki == id);
        listaLekarzy.removeIf(z -> z.idJednostki == id);
        listaPielegniarek.removeIf(z -> z.idJednostki == id);
        System.out.println("Usunieto z bazy.");
    }
    public void usunOsobe(String imie, String nazwisko){
        listaPacjentow.removeIf(z -> z.imie.equals(imie) && z.nazwisko.equals(nazwisko));
        listaLekarzy.removeIf(z -> z.imie.equals(imie) && z.nazwisko.equals(nazwisko));
        listaPielegniarek.removeIf(z -> z.imie.equals(imie) && z.nazwisko.equals(nazwisko));
        System.out.println("Usunieto z bazy.");
    }
}
