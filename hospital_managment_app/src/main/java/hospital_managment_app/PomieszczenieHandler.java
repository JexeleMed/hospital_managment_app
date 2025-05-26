package hospital_managment_app;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PomieszczenieHandler implements HandlerCsv<Pomieszczenie> {

    private static final String FILE_NAME = "pomieszczenia.json";
    private static PomieszczenieHandler instance;

    /* ---------- konfiguracja GSON ---------- */
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Pomieszczenie.class, new PomieszczenieSerializer())
            .registerTypeAdapter(Pomieszczenie.class, new PomieszczenieDeserializer())
            .setPrettyPrinting()
            .create();

    private final Type listType = new TypeToken<List<Pomieszczenie>>(){}.getType();

    /* --------------- singleton ------------- */
    private PomieszczenieHandler() { ensureFileExists(); }

    public static synchronized PomieszczenieHandler getInstance() {
        if (instance == null)
            instance = new PomieszczenieHandler();
        return instance;
    }

    /* --- utworzenie pustego pliku ([ ]) przy pierwszym uruchomieniu --- */
    private void ensureFileExists() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            try (Writer w = new FileWriter(f)) { w.write("[]"); }
            catch (IOException e) { System.err.println("Nie mogę utworzyć " + FILE_NAME + ": " + e.getMessage()); }
        }
    }

    /* -------------------- API -------------------- */
    @Override
    public List<Pomieszczenie> loadAll() {
        try (Reader r = new FileReader(FILE_NAME)) {
            List<Pomieszczenie> list = gson.fromJson(r, listType);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Błąd odczytu " + FILE_NAME + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void saveAll(List<Pomieszczenie> pomieszczenia) throws IOException {
        try (Writer w = new FileWriter(FILE_NAME)) {
            gson.toJson(pomieszczenia, listType, w);
        }
    }
}
