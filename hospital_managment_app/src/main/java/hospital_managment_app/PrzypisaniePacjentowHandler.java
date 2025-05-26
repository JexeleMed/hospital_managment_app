package hospital_managment_app;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/** Zarządza relacją pacjent-pomieszczenie. */
public class PrzypisaniePacjentowHandler {

    private static final String FILE_NAME = "przypisania.json";
    private static PrzypisaniePacjentowHandler instance;

    /** patientId → roomId */
    private final Map<Integer, Integer> przypisania = new HashMap<>();

    /* ---------- konfiguracja GSON ---------- */
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Type LIST_TYPE =
            new TypeToken<List<Assignment>>(){}.getType();

    /* ----------  Singleton  ---------- */
    private PrzypisaniePacjentowHandler() {
        ensureFileExists();
        loadPrzypisania();
    }

    public static synchronized PrzypisaniePacjentowHandler getInstance() {
        if (instance == null)
            instance = new PrzypisaniePacjentowHandler();
        return instance;
    }

    /* ----------  I/O  ---------- */
    private void ensureFileExists() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            try (Writer w = new FileWriter(f)) { w.write("[]"); }          // pusta tablica
            catch (IOException e) { System.err.println("Nie mogę utworzyć " + FILE_NAME + ": " + e.getMessage()); }
        }
    }

    /** Wczytuje z pliku do mapy. */
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

    /** Zapisuje całą mapę do pliku. */
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

    /* ----------  API publiczne  ---------- */
    public Map<Integer, Integer> getPrzypisania() {
        return Collections.unmodifiableMap(przypisania);
    }

    public void dodajPrzypisanie(int idPacjenta, int idPomieszczenia) {
        przypisania.put(idPacjenta, idPomieszczenia);
        savePrzypisania();
    }

    public void usunPrzypisanie(int idPacjenta) {
        przypisania.remove(idPacjenta);
        savePrzypisania();
    }

    public int getPokojPacjenta(int idPacjenta) {
        return przypisania.getOrDefault(idPacjenta, -1);
    }

    /* ----------  DTO wewnętrzne  ---------- */
    private static class Assignment {
        int idPacjenta;
        int idPomieszczenia;

        Assignment(int idPacjenta, int idPomieszczenia) {
            this.idPacjenta = idPacjenta;
            this.idPomieszczenia = idPomieszczenia;
        }
    }
}
