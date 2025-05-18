package hospital_managment_app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

abstract class Personel extends Czlowiek {
    Boolean dostepnosc;
    List<Para<Para<LocalDateTime, LocalDateTime>, String>> zaplanowaneDyzury = new ArrayList<>();


    public Personel(String imie, String nazwisko, String pesel) {
        super(imie, nazwisko, pesel);
    }
    public Personel(String imie, String nazwisko, String pesel, LocalDate dataUrodzenia, String numerTelefonu, String adresEmail, String adresZamieszkania) {
        super(imie, nazwisko, pesel, dataUrodzenia, numerTelefonu, adresEmail, adresZamieszkania);
    }

    public Personel(){
        super();
    }

    protected void zaplanujDyzur(LocalDateTime poczatek, LocalDateTime koniec) {
        Para<LocalDateTime, LocalDateTime> daty = new Para<>(poczatek, koniec);
        Para<Para<LocalDateTime, LocalDateTime>, String> zaplanowanyDyzur = new Para<>(daty, "nie podano");
        zaplanowaneDyzury.add(zaplanowanyDyzur);
        zaktualizujDostepnosc();
    }

    protected void zaplanujDyzur(LocalDateTime poczatek, LocalDateTime koniec, String oddzial) {
        Para<LocalDateTime, LocalDateTime> daty = new Para<>(poczatek, koniec);
        Para<Para<LocalDateTime, LocalDateTime>, String> zaplanowanyDyzur = new Para<>(daty, oddzial);
        zaplanowaneDyzury.add(zaplanowanyDyzur);
        zaktualizujDostepnosc();
    }

    protected void zaktualizujDostepnosc(){
        // petla nie dziala
        if(!this.zaplanowaneDyzury.isEmpty()){
            for(Para<Para<LocalDateTime, LocalDateTime>, String> dyzur : this.zaplanowaneDyzury) {
                LocalDateTime teraz = LocalDateTime.now();
                LocalDateTime dataStartuDyzuru = dyzur.nazwa.nazwa;
                LocalDateTime dataZakonczeniaDyzuru = dyzur.nazwa.wartosc;
                this.dostepnosc = teraz.isAfter(dataStartuDyzuru) && teraz.isBefore(dataZakonczeniaDyzuru);
            }
        } else
            this.dostepnosc = false;
    }

    protected boolean czyDostepny() {
        zaktualizujDostepnosc();
        return dostepnosc;
    }
    protected void wyswietlDostepnosc(){
        System.out.print("Dostępny/a: ");
        if(czyDostepny()){
            System.out.print("Tak");
        } else {
            System.out.print("Nie");
        }
        System.out.println();
        System.out.println("Planowany dyżur: ");
        for(Para<Para<LocalDateTime, LocalDateTime>, String> dyzur : this.zaplanowaneDyzury){
            System.out.println(dyzur);
        }
    }

}
