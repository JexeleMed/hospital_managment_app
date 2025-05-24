package hospital_managment_app;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;

public class PomieszczenieSerializer implements JsonSerializer<Pomieszczenie> {
    @Override
    public JsonElement serialize(Pomieszczenie src, Type typeOfSrc, JsonSerializationContext context) {
        JsonElement element = context.serialize(src);
        JsonObject object = element.getAsJsonObject();
        object.addProperty("type", src.getClass().getSimpleName());
        return object;
    }
}