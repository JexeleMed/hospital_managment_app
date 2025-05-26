package hospital_managment_app;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

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
