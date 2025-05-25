package hospital_managment_app;

import java.io.*;
import java.util.*;
import com.google.gson.*;

public class PrzypisaniePacjentowHandler implements HandlerCsv<Integer> {
    private static final String FILE_NAME = "PrzypisaniePacjentow.csv";
    private static PrzypisaniePacjentowHandler instance;
    private Map<Integer, Integer> przypisania; // idJednostki -> idPomieszczenia

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
        przypisania.clear();
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
                    int idJednostki = jsonObject.get("idJednostki").getAsInt();
                    int idPomieszczenia = jsonObject.get("idPomieszczenia").getAsInt();
                    przypisania.put(idJednostki, idPomieszczenia);
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
                jsonObject.addProperty("idJednostki", entry.getKey());
                jsonObject.addProperty("idPomieszczenia", entry.getValue());
                writer.write(gson.toJson(jsonObject));
                writer.write("\n");
            }
        }
    }

    public void dodajPrzypisanie(int idJednostki, int idPomieszczenia) {
        przypisania.put(idJednostki, idPomieszczenia);
        try {
            saveAll(new ArrayList<>(przypisania.keySet()));
        } catch (IOException e) {
            System.err.println("Error saving patient assignment: " + e.getMessage());
        }
    }

    public void usunPrzypisanie(int idJednostki) {
        przypisania.remove(idJednostki);
        try {
            saveAll(new ArrayList<>(przypisania.keySet()));
        } catch (IOException e) {
            System.err.println("Error removing patient assignment: " + e.getMessage());
        }
    }

    public Map<Integer, Integer> getPrzypisania() {
        return new HashMap<>(przypisania);
    }

    public int getPokojPacjenta(int idJednostki) {
        return przypisania.getOrDefault(idJednostki, -1);
    }
}