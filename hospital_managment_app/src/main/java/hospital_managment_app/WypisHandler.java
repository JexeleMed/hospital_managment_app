package hospital_managment_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WypisHandler implements HandlerCsv<Map<String, Object>> {
    private static final String DATA_DIR = "data/";
    private static final String FILE_NAME = DATA_DIR + "ListaWypisow.csv";
    private static WypisHandler instance;

    // Konstruktor – tworzy katalog i plik, jeśli nie istnieją
    private WypisHandler() {
        ensureDataDirExists();
        createFileIfNotExists();
    }

    // Sprawdza i tworzy katalog na dane, jeśli nie istnieje
    private void ensureDataDirExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Zwraca jedyną instancję tej klasy (Singleton)
    public static WypisHandler getInstance() {
        if (instance == null) {
            instance = new WypisHandler();
        }
        return instance;
    }

    // Tworzy plik, jeśli nie istnieje, z nagłówkiem "json"
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

    // Wczytuje wszystkie wypisy pacjentów z pliku w formacie CSV
    @Override
    public List<Map<String, Object>> loadAll() throws IOException {
        List<Map<String, Object>> wypisy = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String json = line.replace("\"\"", "\"");
                    @SuppressWarnings("unchecked")
                    Map<String, Object> wypis = gson.fromJson(json, Map.class);
                    wypisy.add(wypis);
                }
            }
        }
        return wypisy;
    }

    // Zapisuje listę wypisów do pliku w formacie CSV
    @Override
    public void saveAll(List<Map<String, Object>> wypisy) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("json\n");
            for (Map<String, Object> wypis : wypisy) {
                String json = gson.toJson(wypis);
                writer.write(json.replace("\"", "\"\""));
                writer.write("\n");
            }
        }
    }

    // Dodaje nowy wypis pacjenta do pliku
    public void dodajWypis(Pacjent pacjent) {
        try {
            List<Map<String, Object>> wypisy = loadAll();
            Map<String, Object> wypis = new HashMap<>();
            wypis.put("dataWypisu", LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            wypis.put("idJednostki", pacjent.idJednostki);
            wypis.put("imie", pacjent.imie);
            wypis.put("nazwisko", pacjent.nazwisko);
            wypis.put("pesel", pacjent.pesel);
            wypis.put("dataUrodzenia", pacjent.dataUrodzenia != null ?
                    pacjent.dataUrodzenia.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "");
            wypis.put("numerTelefonu", pacjent.numerTelefonu);
            wypis.put("adresEmail", pacjent.adresEmail);
            wypis.put("adresZamieszkania", pacjent.adresZamieszkania);

            wypisy.add(wypis);
            saveAll(wypisy);
        } catch (IOException e) {
            System.err.println("Error saving discharge: " + e.getMessage());
        }
    }
}