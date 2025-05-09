package hospital_managment_app;

import java.time.LocalDateTime;
import java.util.*;

abstract class Personel extends Czlowiek {
    Boolean dostepnosc;
    List<Para<Para<LocalDateTime, LocalDateTime>, String>> zaplanowaneDyzury = new ArrayList<>();


    protected void zaplanujDyzur(LocalDateTime poczatek, LocalDateTime koniec) {
        Para<LocalDateTime, LocalDateTime> daty = new Para<>(poczatek, koniec);
        Para<Para<LocalDateTime, LocalDateTime>, String> zaplanowanyDyzur = new Para<>(daty, "niepodano");
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
        for(Para<Para<LocalDateTime, LocalDateTime>, String> dyzur : this.zaplanowaneDyzury){
            LocalDateTime teraz = LocalDateTime.now();
            LocalDateTime dataStartuDyzuru = dyzur.nazwa.nazwa;
            LocalDateTime dataZakonczeniaDyzuru = dyzur.nazwa.wartosc;
            dostepnosc = teraz.isAfter(dataStartuDyzuru) && teraz.isBefore(dataZakonczeniaDyzuru);
        }

    }
    protected void wyswietlDostepnosc(){
        System.out.print("Dostępny/a: ");
        if(this.dostepnosc){
            System.out.print("Tak");
        } else {
            System.out.print("Nie");
        }

        System.out.println("Planowany dyżur: ");
        for(Para<Para<LocalDateTime, LocalDateTime>, String> dyzur : this.zaplanowaneDyzury){
            System.out.print(dyzur);
        }
    }

}
