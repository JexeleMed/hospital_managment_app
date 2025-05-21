package hospital_managment_app;
import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

public class PacjentHandler implements HandlerCsv<Pacjent> {

    public List<Pacjent> loadAll() {
        List<Pacjent> lista = new ArrayList<>();
        Gson gson = new Gson();

        try(BufferedReader br = new BufferedReader(new FileReader("PacjenciBaza.csv"))) {
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

    public void saveAll(List<Pacjent> pacjenci) {
        Gson gson = new GsonBuilder().serializeNulls().create();

        try (FileWriter writer = new FileWriter("PacjenciBaza.csv")) {
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
