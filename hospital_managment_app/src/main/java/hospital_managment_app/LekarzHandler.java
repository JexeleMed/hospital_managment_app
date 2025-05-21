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
    private static final String FILE_NAME = "lekarze.csv";
    private static LekarzHandler instance;

    private LekarzHandler() {
        // Private constructor to prevent instantiation
    }

    public static LekarzHandler getInstance() {
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
        return null;
    }

    @Override
    public void saveAll(List<Lekarz> items) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Lekarz l : items) {
                String json = gson.toJson(l);
                writer.append(json.replace("\'", "\'\'"));
                writer.write("\n");
            }
            System.out.println("Zapisano lekarzy do pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new IOException("Error saving lekarze data", e);
        }
    }

    @Override
    public void add(Lekarz item) throws IOException {
        // Implement adding logic here
    }

    @Override
    public void remove(int id) throws IOException {
        // Implement removing logic here
    }
    //test
}
