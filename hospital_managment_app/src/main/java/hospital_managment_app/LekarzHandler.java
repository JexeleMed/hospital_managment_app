package hospital_managment_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class LekarzHandler implements HandlerCsv<Lekarz> {

    private static final String FILE_NAME = "lekarze.json";
    private static LekarzHandler instance;

    private final Gson gson;
    private final Type listType = new TypeToken<List<Lekarz>>(){}.getType();

    /** =====================  Singleton  ===================== */
    public LekarzHandler() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,     new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    public static synchronized LekarzHandler getInstance() {
        if (instance == null) {
            instance = new LekarzHandler();
        }
        return instance;
    }

    /** ==================  Pomocnicze metody  ================= */
    private void ensureFileExists() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            try (Writer w = new FileWriter(f)) {
                w.write("[]");                // pusta tablica JSON
            } catch (IOException e) {
                System.err.println("Nie mogę utworzyć " + FILE_NAME + ": " + e.getMessage());
            }
        }
    }

    /** =====================  API publiczne  ================== */
    @Override
    public List<Lekarz> loadAll() {
        try (Reader r = new FileReader(FILE_NAME)) {
            List<Lekarz> lekarze = gson.fromJson(r, listType);
            return lekarze != null ? lekarze : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Błąd odczytu " + FILE_NAME + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void saveAll(List<Lekarz> lekarze) throws IOException {
        try (Writer w = new FileWriter(FILE_NAME)) {
            gson.toJson(lekarze, listType, w);
        }
    }
}
