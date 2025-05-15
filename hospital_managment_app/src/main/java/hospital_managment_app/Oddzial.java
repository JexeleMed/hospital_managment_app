package hospital_managment_app;

import java.time.LocalDate;
import java.util.HashMap;

public class Oddzial {
    private HashMap<String, LocalDate> listaWypisow;

    public Oddzial() {
        this.listaWypisow = new HashMap<>();
    }

    public void dodajWypis(String id, LocalDate data) {
        listaWypisow.put(id, data);
    }

    public void raportujWypisy() {
        System.out.println("Lista wypisów pacjentów:");
        listaWypisow.forEach((id, data) ->
                System.out.println("ID pacjenta: " + id + ", Data wypisu: " + data)
        );
    }
}
