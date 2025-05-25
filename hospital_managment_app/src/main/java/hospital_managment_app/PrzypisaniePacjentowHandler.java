package hospital_managment_app;

import java.io.*;
import java.util.*;
import com.google.gson.*;

public class PrzypisaniePacjentowHandler implements HandlerCsv<Integer> {
    private static final String FILE_NAME = "PrzypisaniePacjentow.csv";
    private static PrzypisaniePacjentowHandler instance;
    private Map<Integer, Integer> przypisania; // patientId -> roomId

    private PrzypisaniePacjentowHandler() {
        this.przypisania = new HashMap<>();
        createFileIfNotExists();
        try {
            loadAll();
        } catch (IOException e) {
            System.err.println("Error loading patient assignments: " + e.getMessage());
        }
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

    public static PrzypisaniePacjentowHandler getInstance() {
        if (instance == null) {
            instance = new PrzypisaniePacjentowHandler();
        }
        return instance;
    }

    @Override
    public List<Integer> loadAll() throws IOException {
        File file = new File(FILE_NAME);
        Gson gson = new GsonBuilder().create();

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String json = line.replace("\"\"", "\"");
                    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
                    int patientId = jsonObject.get("idPacjenta").getAsInt();
                    int roomId = jsonObject.get("idPomieszczenia").getAsInt();
                    przypisania.put(patientId, roomId);
                }
            }
        }
        return new ArrayList<>(przypisania.keySet());
    }

    @Override
    public void saveAll(List<Integer> items) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("json\n");
            Gson gson = new GsonBuilder().create();
            for (Map.Entry<Integer, Integer> entry : przypisania.entrySet()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("idPacjenta", entry.getKey());
                jsonObject.addProperty("idPomieszczenia", entry.getValue());
                writer.write(gson.toJson(jsonObject).replace("\"", "\"\""));
                writer.write("\n");
            }
        }
    }

    public void dodajPrzypisanie(int idPacjenta, int idPomieszczenia) {
        przypisania.put(idPacjenta, idPomieszczenia);
        try {
            saveAll(new ArrayList<>(przypisania.keySet()));
        } catch (IOException e) {
            System.err.println("Error saving patient assignments: " + e.getMessage());
        }
    }

    public void usunPrzypisanie(int idPacjenta) {
        przypisania.remove(idPacjenta);
        try {
            saveAll(new ArrayList<>(przypisania.keySet()));
        } catch (IOException e) {
            System.err.println("Error saving patient assignments: " + e.getMessage());
        }
    }

    public int getPokojPacjenta(int idPacjenta) {
        return przypisania.getOrDefault(idPacjenta, -1);
    }

    public Map<Integer, Integer> getPrzypisania() {
        return new HashMap<>(przypisania);
    }
}