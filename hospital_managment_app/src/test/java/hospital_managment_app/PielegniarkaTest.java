package hospital_managment_app;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class PielegniarkaTest {
    private Pielegniarka pielegniarka;
    @Before
    public void setUp() {

        pielegniarka = new Pielegniarka("Anna", "Gruszka", "09211684689", "Opieka medyczna", "MED8873", "Dobieranie lekow", 3, true);
    }


    @Test
    public void testKonstruktorDlaLekarzPusty() {
        try {
            new Pielegniarka();
            fail("Oczekiwano UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            assertEquals("Brak danych", e.getMessage());
        }
    }

    @Test
    public void testKonstruktorBezDanychKontaktowych() {
        Pielegniarka p = new Pielegniarka("Anna", "Kowalska", "12345678901",
                "Opieka nad pacjentem", "L12345", "Kwalifikacje A", 2, true);

        assertEquals("Anna", p.imie);
        assertEquals("Kowalska", p.nazwisko);
        assertEquals("12345678901", p.pesel);
        assertEquals("Opieka nad pacjentem", p.zakresObowiazkow);
        assertEquals("L12345", p.numerLicencji);
        assertEquals("Kwalifikacje A", p.kwalifikacjeDodatkowe);
        assertEquals(2, p.szczebelWHierachi);
        assertTrue(p.czyMozePodawacLeki);
    }

    @Test
    public void testKonstruktorZDanymiKontaktowymi() {
        LocalDate dataUrodzenia = LocalDate.of(1990, 1, 1);
        Pielegniarka p = new Pielegniarka("Anna", "Kowalska", "12345678901", dataUrodzenia,
                "123456789", "anna@example.com", "ul. Zdrowa 5",
                "Opieka nad pacjentem", "L12345", "Kwalifikacje A", 2, false);

        assertEquals("Anna", p.imie);
        assertEquals("Kowalska", p.nazwisko);
        assertEquals("12345678901", p.pesel);
        assertEquals(dataUrodzenia, p.dataUrodzenia);
        assertEquals("123456789", p.numerTelefonu);
        assertEquals("anna@example.com", p.adresEmail);
        assertEquals("ul. Zdrowa 5", p.adresZamieszkania);
        assertEquals("Opieka nad pacjentem", p.zakresObowiazkow);
        assertEquals("L12345", p.numerLicencji);
        assertEquals("Kwalifikacje A", p.kwalifikacjeDodatkowe);
        assertEquals(2, p.szczebelWHierachi);
        assertFalse(p.czyMozePodawacLeki);
    }
    @Test
    public void testWyswietlProfil(){
        pielegniarka.wyswietlProfil();
    }

    @Test
    public void testPlanowanieDyzuru(){
        LocalDateTime poczatekDyzuru = LocalDateTime.of(2025, 7, 10, 12, 0);
        LocalDateTime koniecDyzuru = LocalDateTime.of(2025, 7, 10, 20, 0);
        pielegniarka.zaplanujDyzur(poczatekDyzuru, koniecDyzuru);
        pielegniarka.zaplanujDyzur(poczatekDyzuru, koniecDyzuru,  "Endokrynologiczny");
        pielegniarka.wyswietlProfil();
    }
    @Test
    public void testDostepnosci() {
        assertFalse(pielegniarka.czyDostepny());
        pielegniarka.zaplanujDyzur(LocalDateTime.now().minusMinutes(10), LocalDateTime.now().plusMinutes(10));
        assertTrue(pielegniarka.czyDostepny());
    }

}
