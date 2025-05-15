package hospital_managment_app;

import java.time.LocalDate;
import java.util.HashMap;

public class SalaHybrydowa extends Pomieszczenie{
    //zamiast z listy na hashampa
    private HashMap<String, LocalDate> listaWypisow = new HashMap<>();
    //zmiana wypisujemy pacjenta po id
    public void dodajWypis(String id){
        listaWypisow.put(id, LocalDate.now());
        System.out.println("Wypisano pacjenta o id: " + id + "w dniu: " + LocalDate.now());

    }
    //zapis wypisow do klasy oddzial
    public void raportujWypis(){
        System.out.println("Lista wypisow pacjentow:");
        listaWypisow.forEach((id, data) ->
                System.out.println("ID pacjenta: " + id + ", Data wypisu: " + data)
        );
    }

    @Override
    public void generujRaport(){
        super.generujRaport();
//        co tu dac
    }
}
