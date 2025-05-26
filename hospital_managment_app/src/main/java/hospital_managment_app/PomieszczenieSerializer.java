package hospital_managment_app;

import com.google.gson.*;

import java.lang.reflect.Type;

public class PomieszczenieSerializer implements JsonSerializer<Pomieszczenie> {
// Klasa odpowiedzialna za serializację obiektów Pomieszczenie do formatu JSON
    @Override
    public JsonElement serialize(Pomieszczenie src,
                                 Type typeOfSrc,
                                 JsonSerializationContext context) {

        JsonObject obj = context.serialize(src).getAsJsonObject();
        // Pole identyfikujące konkretne pod-klasy
        obj.addProperty("typPomieszczenia", src.getClass().getSimpleName());
        return obj;
    }
}
