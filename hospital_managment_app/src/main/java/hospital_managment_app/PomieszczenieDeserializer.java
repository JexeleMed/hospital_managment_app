package hospital_managment_app;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class PomieszczenieDeserializer implements JsonDeserializer<Pomieszczenie> {
    @Override
    public Pomieszczenie deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        switch(type) {
            case "SalaZabiegowa":
                return context.deserialize(json, SalaZabiegowa.class);
            case "SalaHybrydowa":
                return context.deserialize(json, SalaHybrydowa.class);
            case "Poczekalnia":
                return context.deserialize(json, Poczekalnia.class);
            default:
                throw new JsonParseException("Unknown type: " + type);
        }
    }
}