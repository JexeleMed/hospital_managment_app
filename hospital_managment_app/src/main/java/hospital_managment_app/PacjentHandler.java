package hospital_managment_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacjentHandler implements HandlerCsv<Pacjent> {

    private static final String DATA_DIR = "data/";
    private static final String FILE_NAME = DATA_DIR + "pacjenci.json";
    private static PacjentHandler instance;

    private final Gson gson;
    private final Type listType = new TypeToken<List<Pacjent>>(){}.getType();

    private PacjentHandler() {
        ensureDataDirExists();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        ensureFileExists();
    }

    private void ensureDataDirExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static synchronized PacjentHandler getInstance() {
        if (instance == null) {
            instance = new PacjentHandler();
        }
        return instance;
    }

    private void ensureFileExists() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try (Writer w = new FileWriter(file)) {
                w.write("[]");
            } catch (IOException e) {
                System.err.println("Nie mogę utworzyć pliku " + FILE_NAME + ": " + e.getMessage());
            }
        }
    }

    @Override
    public List<Pacjent> loadAll() {
        try (Reader r = new FileReader(FILE_NAME)) {
            List<Pacjent> pacjenci = gson.fromJson(r, listType);
            return pacjenci != null ? pacjenci : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Błąd odczytu " + FILE_NAME + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void saveAll(List<Pacjent> pacjenci) throws IOException {
        try (Writer w = new FileWriter(FILE_NAME)) {
            gson.toJson(pacjenci, listType, w);
        }
    }
}