package hospital_managment_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacjentHandler implements HandlerCsv<Pacjent> {
    private static PacjentHandler instance;
    private static final String FILE_NAME = "PacjenciBaza.csv";

    private PacjentHandler() {
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

    public static PacjentHandler getInstance() {
        if (instance == null) {
            instance = new PacjentHandler();
        }
        return instance;
    }

    @Override
    public List<Pacjent> loadAll() {
        List<Pacjent> lista = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return lista;
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // skip header
            while((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String json = line.replace("\"\"", "\"");
                    Pacjent p = gson.fromJson(json, Pacjent.class);
                    lista.add(p);
                }
            }
        } catch (IOException e){
            System.err.println("Error loading patients: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public void saveAll(List<Pacjent> pacjenci) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("json\n");
            for (Pacjent p : pacjenci) {
                String json = gson.toJson(p);
                writer.write(json.replace("\"", "\"\"") + "\n");
            }
        }
    }
}