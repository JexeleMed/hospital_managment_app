package hospital_managment_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Klasa odpowiedzialna za zarządzanie przypisaniami pacjentów do pomieszczeń
public class PrzypisaniePacjentowHandler {

    private static final String DATA_DIR = "data/";
    private static final String FILE_NAME = DATA_DIR + "przypisania.json";
    private static PrzypisaniePacjentowHandler instance;

    private final Map<Integer, Integer> przypisania = new HashMap<>();

    // Narzędzie do konwersji danych (JSON)
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // Typ danych dla listy przypisań
    private static final Type LIST_TYPE =
            new TypeToken<List<Assignment>>(){}.getType();

    // Konstruktor – tworzy katalog i plik, jeśli nie istnieją
    private PrzypisaniePacjentowHandler() {
        ensureDataDirExists();
        ensureFileExists();
        loadPrzypisania();
    }

    private void ensureDataDirExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Zwraca jedyną instancję tej klasy (Singleton)
    public static synchronized PrzypisaniePacjentowHandler getInstance() {
        if (instance == null)
            instance = new PrzypisaniePacjentowHandler();
        return instance;
    }

    // Sprawdza i tworzy plik z danymi, jeśli nie istnieje
    private void ensureFileExists() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            try (Writer w = new FileWriter(f)) { w.write("[]"); }
            catch (IOException e) { System.err.println("Nie mogę utworzyć " + FILE_NAME + ": " + e.getMessage()); }
        }
    }

    // Wczytuje dane z pliku
    private void loadPrzypisania() {
        try (Reader r = new FileReader(FILE_NAME)) {
            List<Assignment> list = gson.fromJson(r, LIST_TYPE);
            if (list != null) {
                list.forEach(a -> przypisania.put(a.idPacjenta, a.idPomieszczenia));
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu " + FILE_NAME + ": " + e.getMessage());
        }
    }

    // Zapisuje dane do pliku
    private void savePrzypisania() {
        List<Assignment> list = przypisania.entrySet().stream()
                .map(e -> new Assignment(e.getKey(), e.getValue()))
                .toList();

        try (Writer w = new FileWriter(FILE_NAME)) {
            gson.toJson(list, LIST_TYPE, w);
        } catch (IOException e) {
            System.err.println("Błąd zapisu " + FILE_NAME + ": " + e.getMessage());
        }
    }

    // Zwraca niezmienną mapę przypisań pacjentów do pomieszczeń
    public Map<Integer, Integer> getPrzypisania() {
        return Collections.unmodifiableMap(przypisania);
    }

    // Metody do zarządzania przypisaniami pacjentów do pomieszczeń
    public void dodajPrzypisanie(int idPacjenta, int idPomieszczenia) {
        przypisania.put(idPacjenta, idPomieszczenia);
        savePrzypisania();
    }

    // Usuwa przypisanie pacjenta do pomieszczenia
    public void usunPrzypisanie(int idPacjenta) {
        przypisania.remove(idPacjenta);
        savePrzypisania();
    }

    // Zwraca ID pomieszczenia, do którego przypisany jest pacjent
    public int getPokojPacjenta(int idPacjenta) {
        return przypisania.getOrDefault(idPacjenta, -1);
    }

    // Zwraca ID pacjenta przypisanego do danego pomieszczenia
    private static class Assignment {
        int idPacjenta;
        int idPomieszczenia;

        Assignment(int idPacjenta, int idPomieszczenia) {
            this.idPacjenta = idPacjenta;
            this.idPomieszczenia = idPomieszczenia;
        }
    }
}