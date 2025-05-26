package hospital_managment_app;

import com.google.gson.*;
import java.lang.reflect.Type;

public class PomieszczenieDeserializer implements JsonDeserializer<Pomieszczenie> {
    @Override
    public Pomieszczenie deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("typPomieszczenia").getAsString();

        int numer = jsonObject.get("numer").getAsInt();
        int pietro = jsonObject.get("pietro").getAsInt();
        int pojemnoscSali = jsonObject.get("pojemnoscSali").getAsInt();

        Pomieszczenie pomieszczenie;

        switch (type) {
            case "Poczekalnia":
                pomieszczenie = new Poczekalnia(numer, pietro, pojemnoscSali);
                break;

            case "SalaZabiegowa":
                String opisOperacji = jsonObject.has("opisOperacji") ?
                        jsonObject.get("opisOperacji").getAsString() : "";
                String specjalnaAparatura = jsonObject.has("specjalnaAparatura") ?
                        jsonObject.get("specjalnaAparatura").getAsString() : "";
                pomieszczenie = new SalaZabiegowa(numer, pietro, pojemnoscSali,
                        opisOperacji, specjalnaAparatura);
                break;

            case "SalaHybrydowa":
                // For SalaHybrydowa we need to create a temporary Oddzial
                Oddzial tempOddzial = new Oddzial("Temp");
                pomieszczenie = new SalaHybrydowa(numer, pietro, pojemnoscSali, tempOddzial);
                break;

            default:
                throw new JsonParseException("Unknown room type: " + type);
        }

        // Set common fields
        if (jsonObject.has("aktualnaPojemnosc")) {
            ((Pomieszczenie) pomieszczenie).aktualnaPojemnosc =
                    jsonObject.get("aktualnaPojemnosc").getAsInt();
        }

        // Handle pacjenci array if it exists
        if (jsonObject.has("pacjenci")) {
            JsonArray pacjenciArray = jsonObject.getAsJsonArray("pacjenci");
            for (JsonElement element : pacjenciArray) {
                ((Pomieszczenie) pomieszczenie).pacjenci.add(element.getAsInt());
            }
        }

        return pomieszczenie;
    }
}