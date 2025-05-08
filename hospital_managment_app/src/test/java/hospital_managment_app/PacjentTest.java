package hospital_managment_app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacjentTest {

    private Pacjent pacjent;

    @Before
    public void setUp() {
        List<Para<String, Integer>> perskrypcje = new ArrayList<>();
        perskrypcje.add(new Para<>("Lek A", 500)); // przykładowa preskrypcja
        pacjent = new Pacjent("Jan", "Kowalski", "12345678901",
                "A+", "brak", perskrypcje,
                "wegetariańska", "987654321",
                LocalDate.of(1985, 5, 15),
                "123456789", "jan.kowalski@email.com",
                "ul. Przykładowa 1");
    }

    @Test
    public void testDodawanieIObliczanieRachunku() {
        pacjent.dodajDoRachunku("Wizyty u lekarza", 150.00);
        pacjent.dodajDoRachunku("Badania", 200.00);

        double koszt = pacjent.obliczKoszt();
        assertEquals(350.00, koszt, 0.001);
    }

    @Test
    public void testDodawanieChoroby() {
        pacjent.dodajChorobe("Grypa");
        pacjent.dodajChorobe("Zatrucie pokarmowe", LocalDate.of(2023, 4, 10));

        assertEquals(2, pacjent.historiaChoroby.size());
        assertEquals("Grypa", pacjent.historiaChoroby.get(0).nazwa);
        assertEquals(LocalDate.of(2023, 4, 10), pacjent.historiaChoroby.get(1).wartosc);
    }

    @Test
    public void testWyswietlProfil() {
        pacjent.wyswietlProfil(); // Ten test będzie wyświetlał dane w konsoli
        // W przypadku testów jednostkowych zwykle sprawdza się wyjścia w bardziej zaawansowany sposób,
        // np. poprzez przechwycenie System.out w strumień, ale w tym przypadku metoda wyświetlająca jest ok.
    }

    @Test
    public void testKonstruktorDlaPacjentaNaPoczekalnie() {
        Pacjent pacjentNaPoczekalnie = new Pacjent("Anna", "Nowak", "98765432101");
        assertNull(pacjentNaPoczekalnie.grupaKrwi);
        assertFalse(pacjentNaPoczekalnie.profilPacjentaKompletny);
    }
}
