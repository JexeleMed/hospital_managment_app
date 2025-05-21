package hospital_managment_app;
import java.io.IOException;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface HandlerCsv<T> {
    List<T> loadAll() throws IOException;
    void saveAll(List<T> items) throws IOException;
    void add(T item) throws IOException;
    void remove(int id) throws IOException;
}
