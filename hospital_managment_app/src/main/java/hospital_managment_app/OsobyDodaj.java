// Klasa pomocnicza do interfejsu uzytkownika
package hospital_managment_app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OsobyDodaj {
    Szpital szpital = Szpital.getInstance();
    Scanner sc = new Scanner(System.in);


    public void osobyDodaj() {
        System.out.print("""
                Co chcesz dodac?
                  1 – Pacjent
                  2 – Lekarz
                  3 – Pielegniarka
                Wpisz 1, 2 lub 3 i zatwierdz:""");

        String wybor = sc.nextLine().trim();   // wczytujemy caly wiersz i obcinamy biale znaki

        switch (wybor) {
            case "1", "pacjent", "Pacjent" -> dodajPacjenta();
            case "2", "lekarz",  "Lekarz"  -> dodajLekarza();
            case "3", "pielęgniarka", "Pielegniarka", "Pielęgniarka" -> dodajPielegniarke();
            default -> System.out.println("⚠ Nie rozpoznano opcji: " + wybor);
        }
    }

    protected void dodajPacjenta() {
        System.out.println("Podaj dane pacjenta:");

        System.out.print("Imie: ");
        String imie = sc.nextLine().trim();
        System.out.print("Nazwisko: ");
        String nazwisko = sc.nextLine().trim();
        System.out.print("PESEL: ");
        String pesel = sc.nextLine().trim();

        System.out.print("Czy chcesz wpisac dodatkowe dane? 1 - tak 2 - nie: ");
        int wpisac = Integer.parseInt(sc.nextLine().trim());  // wczytanie calej lini

        if (wpisac != 1) {
            // Konstruktor uproszczony (tylko wymagane pola)
            Pacjent pacjent = new Pacjent(imie, nazwisko, pesel);
            szpital.dodajOsobe(pacjent);
            return;
        }

        // =========================
        // Pytania o dane opcjonalne
        // =========================
        System.out.print("Grupa krwi (puste jesli nieznana): ");
        String grupaKrwi = sc.nextLine().trim();
        if (grupaKrwi.isEmpty()) grupaKrwi = null;

        // Alergie
        System.out.print("Alergie (jesli kilka rozdziel przecinkami, puste jesli brak): ");
        String alergie = sc.nextLine().trim();
        if (alergie.isEmpty()) alergie = null;

        // Recepty
        List<Para<String, Integer>> perskrypcje = new ArrayList<>();
        System.out.println("Dodawanie perskrypcji – pozostaw nazwe pusta, aby zakonczyc:");
        while (true) {
            System.out.print("Nazwa leku: ");
            String lek = sc.nextLine().trim();
            if (lek.isEmpty()) break;

            System.out.print("Dawka w mg: ");
            int dawka = Integer.parseInt(sc.nextLine().trim());
            perskrypcje.add(new Para<>(lek, dawka));
        }
        // Zywienie
        System.out.print("Preferencje zywieniowe (puste jesli brak): ");
        String zywienie = sc.nextLine().trim();
        if (zywienie.isEmpty()) zywienie = null;
        // Nr do bliskich
        System.out.print("Numer kontaktowy bliskich (puste jesli brak): ");
        String numerKontaktowyBliskich = sc.nextLine().trim();
        if (numerKontaktowyBliskich.isEmpty()) numerKontaktowyBliskich = null;
        // Data urodzenia
        System.out.print("Data urodzenia (rrrr-mm-dd) lub puste: ");
        String dataUrStr = sc.nextLine().trim();
        LocalDate dataUrodzenia = dataUrStr.isEmpty() ? null : LocalDate.parse(dataUrStr);
        // Nr telefonu pacjenta
        System.out.print("Numer telefonu (puste jesli brak): ");
        String numerTelefonu = sc.nextLine().trim();
        if (numerTelefonu.isEmpty()) numerTelefonu = null;
        // Adres email pacjenta
        System.out.print("Adres e‑mail (puste jesli brak): ");
        String adresEmail = sc.nextLine().trim();
        if (adresEmail.isEmpty()) adresEmail = null;
        // Adres zamieszkania pacjenta
        System.out.print("Adres zamieszkania (puste jesli brak): ");
        String adresZamieszkania = sc.nextLine().trim();
        if (adresZamieszkania.isEmpty()) adresZamieszkania = null;

        // =========================
        // Utworzenie kompletnego pacjenta
        // =========================
        Pacjent pacjent = new Pacjent(
                imie, nazwisko, pesel,
                grupaKrwi,
                alergie, perskrypcje,
                zywienie, numerKontaktowyBliskich,
                dataUrodzenia,
                numerTelefonu, adresEmail, adresZamieszkania);

        szpital.dodajOsobe(pacjent);
    }
    protected void dodajLekarza() {
        System.out.println("Podaj dane lekarza:");

        System.out.print("Imie: ");
        String imie = sc.nextLine().trim();
        System.out.print("Nazwisko: ");
        String nazwisko = sc.nextLine().trim();
        System.out.print("PESEL: ");
        String pesel = sc.nextLine().trim();
        System.out.print("Specjalizacja: ");
        String specjalizacja = sc.nextLine().trim();
        System.out.print("Numer licencji lekarskiej: ");
        String numerLicencji = sc.nextLine().trim();

        // Zapytanie o chec wprowadzenia dodatkowych informacji
        System.out.print("Czy chcesz wpisac dodatkowe dane? 1 - tak 2 - nie: ");
        int wpisac = Integer.parseInt(sc.nextLine().trim());

        if (wpisac != 1) {
            Lekarz lekarz = new Lekarz(imie, nazwisko, pesel, specjalizacja, numerLicencji);
            szpital.dodajOsobe(lekarz);
            return;
        }

        // ======= Dane opcjonalne =======
        System.out.print("Data urodzenia (rrrr‑mm‑dd) lub puste: ");
        String dataUrStr = sc.nextLine().trim();
        LocalDate dataUrodzenia = dataUrStr.isEmpty() ? null : LocalDate.parse(dataUrStr);

        System.out.print("Numer telefonu (puste jesli brak): ");
        String numerTelefonu = sc.nextLine().trim();
        if (numerTelefonu.isEmpty()) numerTelefonu = null;

        System.out.print("Adres e‑mail (puste jesli brak): ");
        String adresEmail = sc.nextLine().trim();
        if (adresEmail.isEmpty()) adresEmail = null;

        System.out.print("Adres zamieszkania (puste jesli brak): ");
        String adresZamieszkania = sc.nextLine().trim();
        if (adresZamieszkania.isEmpty()) adresZamieszkania = null;

        Lekarz lekarz = new Lekarz(
                imie, nazwisko, pesel,
                dataUrodzenia,
                numerTelefonu, adresEmail, adresZamieszkania,
                specjalizacja, numerLicencji);

        szpital.dodajOsobe(lekarz);
    }
    /*--------------------------------------------------------------
     *  DODAWANIE PIELEGNIARKI
     *--------------------------------------------------------------*/
    protected void dodajPielegniarke() {
        System.out.println("Podaj dane pielegniarki:");

        System.out.print("Imie: ");
        String imie = sc.nextLine().trim();
        System.out.print("Nazwisko: ");
        String nazwisko = sc.nextLine().trim();
        System.out.print("PESEL: ");
        String pesel = sc.nextLine().trim();

        System.out.print("Zakres obowiazkow: ");
        String zakresObowiazkow = sc.nextLine().trim();

        System.out.print("Numer licencji pielegniarskiej: ");
        String numerLicencji = sc.nextLine().trim();

        System.out.print("Kwalifikacje dodatkowe (puste jesli brak): ");
        String kwalifikacjeDodatkowe = sc.nextLine().trim();
        if (kwalifikacjeDodatkowe.isEmpty()) kwalifikacjeDodatkowe = null;

        System.out.print("Szczebel w hierarchii (liczba calkowita): ");
        int szczebelWHierarchii = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Czy pielegniarka moze podawac leki? 1-tak / 0-nie: ");
        Boolean czyMozePodawacLeki = Integer.parseInt(sc.nextLine().trim()) == 1;

        // Zapytanie o chec wprowadzenia dodatkowych informacji
        System.out.print("Czy chcesz wpisac dodatkowe dane kontaktowe? 1-tak / 2-nie: ");
        int wpisac = Integer.parseInt(sc.nextLine().trim());


        /* --- tylko wymagane pola --- */
        if (wpisac != 1) {
            Pielegniarka p = new Pielegniarka(
                    imie, nazwisko, pesel,
                    zakresObowiazkow, numerLicencji,
                    kwalifikacjeDodatkowe, szczebelWHierarchii,
                    czyMozePodawacLeki);
            szpital.dodajOsobe(p);
            return;
        }

        /* --- dane opcjonalne --- */
        System.out.print("Data urodzenia (rrrr-mm-dd) lub puste: ");
        String dataUrStr = sc.nextLine().trim();
        LocalDate dataUrodzenia = dataUrStr.isEmpty() ? null : LocalDate.parse(dataUrStr);

        System.out.print("Numer telefonu (puste jesli brak): ");
        String numerTelefonu = sc.nextLine().trim();
        if (numerTelefonu.isEmpty()) numerTelefonu = null;

        System.out.print("Adres e-mail (puste jesli brak): ");
        String adresEmail = sc.nextLine().trim();
        if (adresEmail.isEmpty()) adresEmail = null;

        System.out.print("Adres zamieszkania (puste jesli brak): ");
        String adresZamieszkania = sc.nextLine().trim();
        if (adresZamieszkania.isEmpty()) adresZamieszkania = null;

        Pielegniarka pielegniarka = new Pielegniarka(
                imie, nazwisko, pesel,
                dataUrodzenia, numerTelefonu, adresEmail, adresZamieszkania,
                zakresObowiazkow, numerLicencji, kwalifikacjeDodatkowe,
                szczebelWHierarchii, czyMozePodawacLeki);

        szpital.dodajOsobe(pielegniarka);
    }



}
