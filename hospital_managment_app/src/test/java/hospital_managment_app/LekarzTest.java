package hospital_managment_app;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class LekarzTest {
    private Lekarz lekarz;

    @Before
    public void setUp() {

        lekarz = new Lekarz("Roman", "Gerden", "69021828893", LocalDate.of(1969, 2, 18),
                "764531296", "RomanGe@szpital.com", "Konwaliowa 1", "Endokrynolog",
                "Endo3732");

    }

    @Test
    public void testKonstruktorDlaLekarzPodstawowy() {
        Lekarz lekarzPodstawowy = new Lekarz("Adam", "Nowak", "92080367917", "Kardiolog", "KARD1010");
        assertNull(lekarzPodstawowy.adresZamieszkania);
        assertEquals("92080367917", lekarzPodstawowy.pesel );
    }

    @Test
    public void testKonstruktorDlaLekarzPusty() {
        try {
            new Lekarz();
            fail("Oczekiwano UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            assertEquals("Brak danych", e.getMessage());
        }
    }
    @Test
    public void testKonstruktorDlaLekarzPelny() {
        Lekarz lekarzPelny = new Lekarz("Jan", "Szeregowy", "80031767916", LocalDate.of(80, 3, 17), "601765314", "ja.szeregowy@szpital.com", "Gronowa 12", "Onkolog", "Onko3131");
        assertEquals("601765314",lekarzPelny.numerTelefonu);
        assertTrue(lekarzPelny.czyProfilKompletny);
    }

    @Test
    public void testWyswietlProfil(){
        lekarz.wyswietlProfil();
    }


    @Test
    public void testPlanowanieDyzuru(){
        LocalDateTime poczatekDyzuru = LocalDateTime.of(2025, 7, 10, 12, 0);
        LocalDateTime koniecDyzuru = LocalDateTime.of(2025, 7, 10, 20, 0);
        lekarz.zaplanujDyzur(poczatekDyzuru, koniecDyzuru);
        lekarz.zaplanujDyzur(poczatekDyzuru, koniecDyzuru,  "Endokrynologiczny");
        lekarz.wyswietlProfil();
    }

    @Test
    public void testDostepnosci() {
        assertFalse(lekarz.czyDostepny());
        lekarz.zaplanujDyzur(LocalDateTime.now().minusMinutes(10), LocalDateTime.now().plusMinutes(10));
        assertTrue(lekarz.czyDostepny());
    }

}
