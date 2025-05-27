package hospital_managment_app;

import com.google.gson.*;

import java.lang.reflect.Type;

public class PomieszczenieDeserializer implements JsonDeserializer<Pomieszczenie> {

    // Deserializuje obiekt Pomieszczenie z formatu JSON
    @Override
    public Pomieszczenie deserialize(JsonElement json,
                                     Type typeOfT,
                                     JsonDeserializationContext ctx) throws JsonParseException {
        // Sprawdzenie, czy przekazany element jest obiektem JSON
        JsonObject jo   = json.getAsJsonObject();
        String  typ     = jo.get("typPomieszczenia").getAsString();
        int     numer   = jo.get("numer").getAsInt();
        int     pietro  = jo.get("pietro").getAsInt();
        int     pojSala = jo.get("pojemnoscSali").getAsInt();

        Pomieszczenie pom;

        // Wybor odpowiedniego typu pomieszczenia na podstawie pola "typPomieszczenia"
        switch (typ) {
            case "Poczekalnia" -> pom = new Poczekalnia(numer, pietro, pojSala);

            case "SalaZabiegowa" -> {
                String opis = jo.has("opisOperacji") ? jo.get("opisOperacji").getAsString() : "";
                String apar = jo.has("specjalnaAparatura") ? jo.get("specjalnaAparatura").getAsString() : "";
                pom = new SalaZabiegowa(numer, pietro, pojSala, opis, apar);
            }

            case "SalaHybrydowa" -> {
                // jesli w JSON-ie nie ma info o oddziale – tworzymy tymczasowy
                Oddzial oddz = jo.has("oddzialNazwa")
                        ? new Oddzial(jo.get("oddzialNazwa").getAsString())
                        : new Oddzial("Tymczasowy");
                pom = new SalaHybrydowa(numer, pietro, pojSala, oddz);
            }

            default -> throw new JsonParseException("Nieznany typ: " + typ);
        }

        // Wspolne pola
        if (jo.has("aktualnaPojemnosc"))
            pom.aktualnaPojemnosc = jo.get("aktualnaPojemnosc").getAsInt();

        if (jo.has("pacjenci")) {
            for (JsonElement el : jo.getAsJsonArray("pacjenci"))
                pom.pacjenci.add(el.getAsInt());
        }
        return pom;
    }
}
