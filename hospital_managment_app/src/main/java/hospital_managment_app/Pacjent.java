package hospital_managment_app;

import java.util.*;
import java.time.LocalDate;

public class Pacjent extends Czlowiek {

    protected List<Para<String, LocalDate>> historiaChoroby = new ArrayList<>();
    protected String grupaKrwi;
    protected List<String> alergie = new ArrayList<>();
    protected List<Para<String, Integer>> perskrypcje; // w mg
    protected String zywienie;
    protected String numerKontaktowyBliskich;
    protected List<Para<String, Double>> rachunek = new ArrayList<>();
    protected boolean profilPacjentaKompletny = false;
    protected Integer priorytet; // od 1 do 10 gdzie 1 to najniższy priorytet, a 10 to najwyższy

    // Konstruktor do zapisania pacjenta do oddzialu
    public Pacjent(String imie, String nazwisko, String pesel,
                    String grupaKrwi,
                   String alergie, List<Para<String, Integer>> perskrypcje,
                   String zywienie, String numerKontaktowyBliskich,
                   LocalDate dataUrodzenia,
                   String numerTelefonu, String adresEmail, String adresZamieszkania) {
        super(imie, nazwisko, pesel, dataUrodzenia, numerTelefonu, adresEmail, adresZamieszkania);


        this.grupaKrwi = grupaKrwi;
        this.perskrypcje = perskrypcje;
        this.zywienie = zywienie;
        this.numerKontaktowyBliskich = numerKontaktowyBliskich;
        this.alergie.add(alergie);  // Dodajemy alergię do listy
        this.profilPacjentaKompletny = true;
        this.priorytet = null;

    }

    public Pacjent(String imie, String nazwisko, String pesel){
        super(imie, nazwisko, pesel);
    }

    public Pacjent(){
        super();
    }

    //ustawianie priorytetu pacjenta
    protected void ustawPriorytet(int priorytet) {
        if (priorytet < 1 || priorytet > 10) {
            throw new IllegalArgumentException("Priorytet musi być między 1 a 10");
        }
        this.priorytet = priorytet;
    }

    protected Integer getPriorytet() {
        return priorytet;
    }

    @Override
    protected void wyswietlProfil() {
        System.out.println("Profil pacjenta: ");
        this.wyswietlPodstawoweInfo();
        if (profilPacjentaKompletny) {
            System.out.println("Grupa krwi: " + grupaKrwi);
            System.out.println("Alergie: " + alergie);
            System.out.println("Perskrypcje: " + perskrypcje);
            System.out.println("Zywienie: " + zywienie);
            System.out.println("Numer kontaktowy bliskich: " + numerKontaktowyBliskich);
            if(historiaChoroby != null) {
                System.out.println("Historia choroby: " + historiaChoroby);
            }
        }
    }

    protected void dodajDoRachunku(String usluga, Double cena){
        this.rachunek.add(new Para<>(usluga, cena));
    }

    protected double obliczKoszt(){
        double koszt = 0;
        for(Para<String, Double> p :rachunek){
            koszt += p.wartosc;
        }
        return koszt;
    }


    protected void dodajChorobe(String opis){
        this.historiaChoroby.add(new Para<>(opis, LocalDate.now()));
    }
    protected void dodajChorobe(String opis, LocalDate data){
        this.historiaChoroby.add(new Para<>(opis, data));
    }
    protected boolean czyMaAlergieNa(String subst){
        return alergie.contains(subst);
    }

}
