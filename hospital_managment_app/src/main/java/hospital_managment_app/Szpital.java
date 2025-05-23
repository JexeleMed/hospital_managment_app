package hospital_managment_app;

import java.util.*;

public class Szpital {
    private final PacjentHandler pacjentHandler;
    private final LekarzHandler lekarzHandler;
    private final PielegniarkaHandler pielegniarkaHandler;
    private final PomieszczenieHandler pomieszczenieHandler;

    protected static String nazwaSzpitala = "Szpital";
    protected List<Pomieszczenie> listaPomieszczen;
    protected List<Oddzial> listaOddzialow;
    protected List<Pacjent> listaPacjentow;
    protected List<Lekarz> listaLekarzy;
    protected List<Pielegniarka> listaPielegniarek;

    // Inicjalizacja handlerow
    public Szpital() {
        // Initialize collections
        listaPomieszczen = new ArrayList<>();
        listaOddzialow = new ArrayList<>();
        listaPacjentow = new ArrayList<>();
        listaLekarzy = new ArrayList<>();
        listaPielegniarek = new ArrayList<>();

        // Initialize handlers
        pacjentHandler = PacjentHandler.getInstance();
        lekarzHandler = LekarzHandler.getInstance();
        pielegniarkaHandler = PielegniarkaHandler.getInstance();
        pomieszczenieHandler = PomieszczenieHandler.getInstance();

        // Load data
        try {
            listaPacjentow = pacjentHandler.loadAll();
            listaLekarzy = lekarzHandler.loadAll();
            listaPielegniarek = pielegniarkaHandler.loadAll();
            listaPomieszczen = pomieszczenieHandler.loadAll();
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

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

    // Dodawanie pomieszczen
    public void dodajPomieszczenie(Pomieszczenie p){
        listaPomieszczen.add(p);
        System.out.println("Dodano pomieszczenie.");
    }
    // Usuwanie pomieszczen
    public void usunPomieszczenie(int numer, int pietro){
        listaPomieszczen.removeIf(z -> z.numer == numer && z.pietro == pietro);
        System.out.println("Usunieto z bazy.");
    }
    // Pokazywanie pomieszczen
    public void pokazPomieszczenie(int numer, int pietro){
        for(Pomieszczenie p : listaPomieszczen){
            if(p.numer == numer && p.pietro == pietro){
                p.lokalizacja();
                System.out.println("______________________");
            }
        }
    }
    // Zapisywanie wszystkich metod
    public void saveAllData() {
        try {
            pacjentHandler.saveAll(listaPacjentow);
            lekarzHandler.saveAll(listaLekarzy);
            pielegniarkaHandler.saveAll(listaPielegniarek);
            pomieszczenieHandler.saveAll(listaPomieszczen);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
}
