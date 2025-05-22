package hospital_managment_app;
import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.List;

public class PomieszczenieHandler implements HandlerCsv<Pomieszczenie>{
    private static final String FILE_NAME = "pomieszczenia.csv";

    @Override
    public List<Pomieszczenie> loadAll() {
        List<Pomieszczenie> lista = new ArrayList<>();
        Gson gson = new Gson();

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = br.readLine();
            while((line = br.readLine()) != null) {
                String json = line.replace("\"\"", "\"");
                Pomieszczenie p = gson.fromJson(json, Pomieszczenie.class);
                lista.add(p);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void saveAll(List<Pomieszczenie> pomieszczenia) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).serializeNulls().create();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.append("json\n");
            for (Pomieszczenie p : pomieszczenia) {
                String json = gson.toJson(p);
                writer.append(json.replace("\"", "\"\""));
                writer.append("\n");
            }
            System.out.println("Zapisano pomieszczenia do pliku " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
