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

    public Czlowiek(String imie, String nazwisko, String pesel){
        idGlobal += idGlobal++; // generowanie nowego ID
        this.idJednostki = idGlobal; // przypisanie ID uzytkownikowi
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }

    public Czlowiek(String imie, String nazwisko, String pesel, LocalDate dataUrodzenia, String numerTelefonu, String adresEmail, String adresZamieszkania){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.numerTelefonu = numerTelefonu;
        this.adresEmail = adresEmail;
        this.adresZamieszkania = adresZamieszkania;

    }

    public Czlowiek(){
        throw new UnsupportedOperationException("Brak danych");
    }
}
