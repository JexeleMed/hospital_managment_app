// Kontakr dla Handlerow operujacych na plikach JSON
package hospital_managment_app;

import java.io.IOException;
import java.util.List;

public interface HandlerCsv<T> {
    List<T> loadAll() throws IOException;
    void saveAll(List<T> items) throws IOException;
}
