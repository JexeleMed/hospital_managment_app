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

    private static final String FILE_NAME = "pacjenci.json";
    private static PacjentHandler instance;

    private final Gson gson;
    private final Type listType = new TypeToken<List<Pacjent>>(){}.getType();

    public PacjentHandler() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        ensureFileExists();
    }

    /** Singleton */
    public static synchronized PacjentHandler getInstance() {
        if (instance == null) {
            instance = new PacjentHandler();
        }
        return instance;
    }

    /** Tworzy plik z pustą tablicą, jeśli go brak */
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

    /** Wczytuje wszystkich pacjentów z pliku */
    @Override
    public List<Pacjent> loadAll() {
        try (Reader r = new FileReader(FILE_NAME)) {
            List<Pacjent> pacjenci = gson.fromJson(r, listType);
            // gson zwraca null, gdy w pliku jest "null"
            return pacjenci != null ? pacjenci : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Błąd odczytu " + FILE_NAME + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /** Zapisuje listę pacjentów do pliku */
    @Override
    public void saveAll(List<Pacjent> pacjenci) throws IOException {
        try (Writer w = new FileWriter(FILE_NAME)) {
            gson.toJson(pacjenci, listType, w);
        }
    }
}
