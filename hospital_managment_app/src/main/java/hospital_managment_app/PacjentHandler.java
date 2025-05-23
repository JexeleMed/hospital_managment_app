package hospital_managment_app;
import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.List;

public class PacjentHandler implements HandlerCsv<Pacjent> {
    private static PacjentHandler instance;
    private static final String FILE_NAME = "PacjenciBaza.csv";

    private PacjentHandler() {}

    public static PacjentHandler getInstance() {
        if (instance == null) {
            instance = new PacjentHandler();
        }
        return instance;
    }

    @Override
    public List<Pacjent> loadAll() {
        List<Pacjent> lista = new ArrayList<>();
        Gson gson = new Gson();

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = br.readLine();
            while((line = br.readLine()) != null) {
                String json = line.replace("\"\"", "\"");
                Pacjent p = gson.fromJson(json, Pacjent.class);
                lista.add(p);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return lista;
    }
    @Override
    public void saveAll(List<Pacjent> pacjenci) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).serializeNulls().create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.append("json\n");
            for (Pacjent p : pacjenci) {
                String json = gson.toJson(p);
                writer.append(json.replace("\"", "\"\""));
                writer.append("\n");
            }
            System.out.println("Zapisano baze pacjentow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
