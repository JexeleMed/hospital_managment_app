package hospital_managment_app;

import java.util.*;
import java.time.LocalDate;

public class Pacjent extends Czlowiek {

    protected String historiaChoroby;
    protected String grupaKrwi;
    protected List<String> alergie;
    protected List<Para<String, Integer>> perskrypcje; // w mg
    protected String zywienie;
    protected String numerKontaktowyBliskich;
    protected List<Para<String, Float>> rachunek;
    private boolean profilPacjentaKompletny = false;

    // Konstruktor do zapisania pacjenta do oddzialu
    public Pacjent(String imie, String nazwisko, String pesel,
                   String historiaChoroby, String grupaKrwi,
                   String alergie, List<Para<String, Integer>> perskrypcje,
                   String zywienie, String numerKontaktowyBliskich,
                   LocalDate dataUrodzenia,
                   String numerTelefonu, String adresEmail, String adresZamieszkania) {
        super(imie, nazwisko, pesel, dataUrodzenia, numerTelefonu, adresEmail, adresZamieszkania);

        this.historiaChoroby = historiaChoroby;
        this.grupaKrwi = grupaKrwi;
        this.perskrypcje = perskrypcje;
        this.zywienie = zywienie;
        this.numerKontaktowyBliskich = numerKontaktowyBliskich;
        this.alergie = new ArrayList<>();
        this.alergie.add(alergie);  // Dodajemy alergię do listy
        this.profilPacjentaKompletny = true;

    }

    // Konstruktor do zapisania pacjenta na poczekalnie
    public Pacjent(String imie, String nazwisko, String pesel){
        super(imie, nazwisko, pesel);
    }


    @Override
    public void wyswietlProfil() {
        System.out.println("Profil pacjenta: ");
        super.wyswietlPodstawoweInfo();
        if (profilPacjentaKompletny) {
            System.out.println("Grupa krwi: " + grupaKrwi);
            System.out.println("Alergie: " + alergie);
            System.out.println("Perskrypcje: " + perskrypcje);
            System.out.println("Zywienie: " + zywienie);
            System.out.println("Numer kontaktowy bliskich: " + numerKontaktowyBliskich);
        }
    }

}
