package hospital_managment_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// Klasa odpowiedzialna za obsluge zapisu i odczytu danych pomieszczen
public class PomieszczenieHandler implements HandlerCsv<Pomieszczenie> {

    private static final String DATA_DIR = "data/";
    private static final String FILE_NAME = DATA_DIR + "pomieszczenia.json";
    private static PomieszczenieHandler instance;

    // Narzedzie do konwersji danych (JSON)
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Pomieszczenie.class, new PomieszczenieSerializer())
            .registerTypeAdapter(Pomieszczenie.class, new PomieszczenieDeserializer())
            .setPrettyPrinting()
            .create();

    // Typ danych dla listy obiektow
    private final Type listType = new TypeToken<List<Pomieszczenie>>(){}.getType();

    // Konstruktor – tworzy katalog i plik, jesli nie istnieja
    private PomieszczenieHandler() {
        ensureDataDirExists();
        ensureFileExists();
    }

    // Sprawdza i tworzy katalog na dane
    private void ensureDataDirExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Zwraca jedyna instancje tej klasy (Singleton)
    public static synchronized PomieszczenieHandler getInstance() {
        if (instance == null)
            instance = new PomieszczenieHandler();
        return instance;
    }

    // Sprawdza i tworzy plik z danymi, jesli nie istnieje
    private void ensureFileExists() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            try (Writer w = new FileWriter(f)) { w.write("[]"); }
            catch (IOException e) { System.err.println("Nie moge utworzyc " + FILE_NAME + ": " + e.getMessage()); }
        }
    }

    // Wczytuje dane z pliku
    @Override
    public List<Pomieszczenie> loadAll() {
        try (Reader r = new FileReader(FILE_NAME)) {
            List<Pomieszczenie> list = gson.fromJson(r, listType);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Blad odczytu " + FILE_NAME + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Zapisuje dane do pliku
    @Override
    public void saveAll(List<Pomieszczenie> pomieszczenia) throws IOException {
        try (Writer w = new FileWriter(FILE_NAME)) {
            gson.toJson(pomieszczenia, listType, w);
        }
    }
}