package hospital_managment_app;
import java.io.IOException;
import java.util.*;

public interface HandlerCsv<T> {
    List<T> loadAll() throws IOException;
    void saveAll(List<T> items) throws IOException;
    Optional<T> findById(int id) throws IOException;
    void add(T item) throws IOException;
    void remove(int id) throws IOException;
}
