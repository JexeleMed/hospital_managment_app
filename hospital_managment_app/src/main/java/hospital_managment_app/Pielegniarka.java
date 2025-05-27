package hospital_managment_app;

import java.time.LocalDate;

public class Pielegniarka extends Personel {
    protected Boolean czyMozePodawacLeki;
    protected int szczebelWHierachi;
    protected String zakresObowiazkow;
    protected String kwalifikacjeDodatkowe;
    protected String numerLicencji;

    public Pielegniarka() {
        super();
    }

    // Konstruktor z podstawowymi danymi
    public Pielegniarka(String imie, String nazwisko, String pesel, String zakresObowiazkow, String numerLicencji, String kwalifikacjeDodatkowe, int szczebelWHierachi, Boolean czyMozePodawacLeki) {
        super(imie, nazwisko, pesel);
        this.zakresObowiazkow = zakresObowiazkow;
        this.numerLicencji = numerLicencji;
        this.kwalifikacjeDodatkowe = kwalifikacjeDodatkowe;
        this.szczebelWHierachi = szczebelWHierachi;
        this.czyMozePodawacLeki = czyMozePodawacLeki;



    }

    // Konstruktor z pelnymi danymi
    public Pielegniarka(String imie, String nazwisko, String pesel, LocalDate dataUrodzenia, String numerTelefonu, String adresEmail, String adresZamieszkania,
                        String zakresObowiazkow, String numerLicencji, String kwalifikacjeDodatkowe, int szczebelWHierachi, Boolean czyMozePodawacLeki) {
        super(imie, nazwisko, pesel, dataUrodzenia, numerTelefonu, adresEmail, adresZamieszkania);
        this.zakresObowiazkow = zakresObowiazkow;
        this.numerLicencji = numerLicencji;
        this.kwalifikacjeDodatkowe = kwalifikacjeDodatkowe;
        this.szczebelWHierachi = szczebelWHierachi;
        this.czyMozePodawacLeki = czyMozePodawacLeki;
    }

    // Metoda do przypisywania pacjenta do pielegniarki
    @Override
    protected void wyswietlProfil() {
        System.out.println("Profil pielegniarki: ");
        this.wyswietlPodstawoweInfo();
        System.out.println("Numer licencji: " + this.numerLicencji);
        System.out.println("Kwalifikacje dodatkowe: " + this.kwalifikacjeDodatkowe);
        System.out.println("Zakres obowiazkow: " + this.zakresObowiazkow);
        System.out.println("Szczebel w hierarchii " + this.numerLicencji);
        System.out.println("Czy moze podawac leki: " + this.czyMozePodawacLeki);
        this.wyswietlDostepnosc();
    }

    // Metoda do wyswietlania szczegolowych informacji o pielegniarce
    protected boolean czyMozePodawacLeki(){
        return czyMozePodawacLeki;
    }
}
