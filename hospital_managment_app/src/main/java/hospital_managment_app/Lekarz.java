package hospital_managment_app;

import java.time.LocalDate;

public class Lekarz extends Personel {
    protected String specjalizacja;
    protected String numerLicencjiLekarskiej;


    public Lekarz(String imie, String nazwisko, String pesel, String specjalizacja, String numerLicencjiLekarskiej) {
        super(imie, nazwisko, pesel);
        this.numerLicencjiLekarskiej = numerLicencjiLekarskiej;
        this.specjalizacja = specjalizacja;
    }
    public Lekarz(String imie, String nazwisko, String pesel, LocalDate dataUrodzenia, String numerTelefonu, String adresEmail, String adresZamieszkania, String specjalizacja, String numerLicencjiLekarskiej){
        super(imie, nazwisko, pesel, dataUrodzenia, numerTelefonu, adresEmail, adresZamieszkania);
        this.specjalizacja = specjalizacja;
        this.numerLicencjiLekarskiej = numerLicencjiLekarskiej;
    }
    public Lekarz() {
        super();
    }



    @Override
    protected void wyswietlProfil() {
        System.out.println("Profil lekarza: ");
        this.wyswietlPodstawoweInfo();
        System.out.println("Specjalizacja: " + this.specjalizacja);
        System.out.println("Numer licencji: " + this.numerLicencjiLekarskiej);
        this.wyswietlDostepnosc();

    }
}
