/* Klasa serializujaca obiekty klasy Lekarz do plikow json */
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

public class LekarzHandler implements HandlerCsv<Lekarz> {

    // Definicja scierzek
    private static final String DATA_DIR = "data/";
    private static final String FILE_NAME = DATA_DIR + "lekarze.json";
    private static LekarzHandler instance;

    private final Gson gson;
    private final Type listType = new TypeToken<List<Lekarz>>(){}.getType();

    // Uzyto autorskich adapterow
    public LekarzHandler() {
        ensureDataDirExists();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,     new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
        ensureFileExists();
    }

    // Sprawdzanie czy istnieje dir
    private void ensureDataDirExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static synchronized LekarzHandler getInstance() {
        if (instance == null) {
            instance = new LekarzHandler();
        }
        return instance;
    }

    // Sprawdzanie czy istnieje plik do zapisu
    private void ensureFileExists() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            try (Writer w = new FileWriter(f)) {
                w.write("[]");
            } catch (IOException e) {
                System.err.println("Nie mogę utworzyć " + FILE_NAME + ": " + e.getMessage());
            }
        }
    }
    // Ladowanie z pliku
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
    //Zapis do pliku
    @Override
    public void saveAll(List<Lekarz> lekarze) throws IOException {
        try (Writer w = new FileWriter(FILE_NAME)) {
            gson.toJson(lekarze, listType, w);
        }
    }
}