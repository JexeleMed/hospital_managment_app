package hospital_managment_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PielegniarkaHandler implements HandlerCsv<Pielegniarka>{

    private static PielegniarkaHandler instance;
    private static final String FILE_NAME = "PielegniarkiBaza.csv";

    private PielegniarkaHandler() {
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

    public static PielegniarkaHandler getInstance() {
        if (instance == null) {
            instance = new PielegniarkaHandler();
        }
        return instance;
    }

    @Override
    public List<Pielegniarka> loadAll() {
        List<Pielegniarka> lista = new ArrayList<>();
        Gson gson = new Gson();

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = br.readLine();
            while((line = br.readLine()) != null) {
                String json = line.replace("\"\"", "\"");
                Pielegniarka p = gson.fromJson(json, Pielegniarka.class);
                lista.add(p);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return lista;
    }
    @Override
    public void saveAll(List<Pielegniarka> pielegniarki) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).serializeNulls().create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.append("json\n");
            for (Pielegniarka p : pielegniarki) {
                String json = gson.toJson(p);
                writer.append(json.replace("\"", "\"\""));
                writer.append("\n");
            }
            System.out.println("Zapisano pielegniarki do pliku " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
