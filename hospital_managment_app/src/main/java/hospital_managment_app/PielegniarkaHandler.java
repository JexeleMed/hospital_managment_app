package hospital_managment_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PielegniarkaHandler implements HandlerCsv<Pielegniarka> {

    private static final String DATA_DIR = "data/";
    private static final String FILE_NAME = DATA_DIR + "pielegniarki.json";
    private static PielegniarkaHandler instance;

    private final Gson gson;
    private final Type listType = new TypeToken<List<Pielegniarka>>(){}.getType();

    // Konstruktor – tworzy katalog i plik, jesli nie istnieja
    public PielegniarkaHandler() {
        ensureDataDirExists();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,     new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
        ensureFileExists();
    }

    // Sprawdza i tworzy katalog na dane, jesli nie istnieje
    private void ensureDataDirExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Zwraca jedyna instancje tej klasy (Singleton)
    public static synchronized PielegniarkaHandler getInstance() {
        if (instance == null) {
            instance = new PielegniarkaHandler();
        }
        return instance;
    }

    // Sprawdza i tworzy plik z danymi, jesli nie istnieje
    private void ensureFileExists() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            try (Writer w = new FileWriter(f)) {
                w.write("[]");
            } catch (IOException e) {
                System.err.println("Nie moge utworzyc " + FILE_NAME + ": " + e.getMessage());
            }
        }
    }

    // Wczytuje dane z pliku
    @Override
    public List<Pielegniarka> loadAll() {
        try (Reader r = new FileReader(FILE_NAME)) {
            List<Pielegniarka> list = gson.fromJson(r, listType);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Blad odczytu " + FILE_NAME + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Zapisuje dane do pliku
    @Override
    public void saveAll(List<Pielegniarka> pielegniarki) throws IOException {
        try (Writer w = new FileWriter(FILE_NAME)) {
            gson.toJson(pielegniarki, listType, w);
        }
    }
}