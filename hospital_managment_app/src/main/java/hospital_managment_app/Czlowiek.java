package hospital_managment_app;
import java.time.*;

abstract class Czlowiek {

    protected static int idGlobal;
    protected int idJednostki;
    protected String pesel;
    protected String imie;
    protected String nazwisko;
    protected LocalDate dataUrodzenia;
    protected String numerTelefonu;
    protected String adresEmail;
    protected String adresZamieszkania;
    private boolean czyProfilKompletny;

    public Czlowiek(String imie, String nazwisko, String pesel){
        idGlobal += idGlobal++; // generowanie nowego ID
        this.idJednostki = idGlobal; // przypisanie ID uzytkownikowi
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.czyProfilKompletny = false;
    }

    public Czlowiek(String imie, String nazwisko, String pesel, LocalDate dataUrodzenia, String numerTelefonu, String adresEmail, String adresZamieszkania){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.numerTelefonu = numerTelefonu;
        this.adresEmail = adresEmail;
        this.adresZamieszkania = adresZamieszkania;
        this.czyProfilKompletny = true;

    }

    public Czlowiek(){
        throw new UnsupportedOperationException("Brak danych");
    }

    protected void wyswietlPodstawoweInfo(){
        System.out.println("Imie: " + this.imie);
        System.out.println("Nazwisko: " + this.nazwisko);
        System.out.println("Pesel: " + this.pesel);
        if(this.czyProfilKompletny){
            System.out.println("Data urodzenia: " + this.dataUrodzenia);
            System.out.println("Numer telefonu: " + this.numerTelefonu);
            System.out.println("Adres email: " + this.adresEmail);
            System.out.println(("Adres zamieszkania: " + this.adresZamieszkania));
        }
    }
    protected abstract void wyswietlProfil();
}
