package hospital_managment_app;

import java.io.IOException;
import java.lang.reflect.GenericSignatureFormatError;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.List;


public class LekarzHandler implements HandlerCsv<Lekarz> {
    private static LekarzHandler instance;
    private static final String FILE_NAME = "LekarzeBaza.csv";

    private LekarzHandler() {
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("json\n");
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    public static LekarzHandler getInstance() { // Fix: Change return type
        if (instance == null) {
            instance = new LekarzHandler();
        }
        return instance;
    }

    @Override
    public List<Lekarz> loadAll() throws IOException {
        List<Lekarz> lekarze = new ArrayList<>();
        Gson gson = new Gson();
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String json = line.replace("\'", "\'\'");
                Lekarz l = gson.fromJson(json, Lekarz.class);
                lekarze.add(l);
            }
        } catch (IOException e) {
            throw new IOException("Error loading lekarze data", e);
        }
        return lekarze;
    }

    @Override
    public void saveAll(List<Lekarz> lekarze) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("json\n");
            for (Lekarz l : lekarze) {
                String json = gson.toJson(l);
                writer.write(json.replace("\"", "\"\"") + "\n");
            }
        }
    }
}
