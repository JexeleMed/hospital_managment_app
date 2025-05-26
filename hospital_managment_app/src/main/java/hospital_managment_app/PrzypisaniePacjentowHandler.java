package hospital_managment_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.*;

public class PrzypisaniePacjentowHandler {
    private static final String FILE_NAME = "PrzypisaniePacjentow.csv";
    private static PrzypisaniePacjentowHandler instance;
    private Map<Integer, Integer> przypisania = new HashMap<>(); // patientId -> roomId

    private PrzypisaniePacjentowHandler() {
        createFileIfNotExists();
        loadPrzypisania();
    }

    public static PrzypisaniePacjentowHandler getInstance() {
        if (instance == null) {
            instance = new PrzypisaniePacjentowHandler();
        }
        return instance;
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

    private void loadPrzypisania() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String json = line.replace("\"\"", "\"");
                    Map<String,Integer> map = new Gson().fromJson(json, Map.class);
                    przypisania.put(map.get("idPacjenta"), map.get("idPomieszczenia"));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading assignments: " + e.getMessage());
        }
    }

    protected void savePrzypisania() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("json\n");
            for (Map.Entry<Integer, Integer> entry : przypisania.entrySet()) {
                Map<String, Integer> map = new HashMap<>();
                map.put("idPacjenta", entry.getKey());
                map.put("idPomieszczenia", entry.getValue());
                String json = gson.toJson(map);
                writer.write(json.replace("\"", "\"\"") + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving assignments: " + e.getMessage());
        }
    }

    protected Map<Integer, Integer> getPrzypisania() {
        return przypisania;
    }

    protected void dodajPrzypisanie(int idPacjenta, int idPomieszczenia) {
        przypisania.put(idPacjenta, idPomieszczenia);
        savePrzypisania();
    }

    protected void usunPrzypisanie(int idPacjenta) {
        przypisania.remove(idPacjenta);
        savePrzypisania();
    }

    protected int getPokojPacjenta(int idPacjenta) {
        return przypisania.getOrDefault(idPacjenta, -1);
    }
}