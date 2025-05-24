package hospital_managment_app;

public class PomieszczenieSerializer implements JsonSerializer<Pomieszczenie> {
    @Override
    public JsonElement serialize(Pomieszczenie src, Type typeOfSrc, JsonSerializationContext context) {
        JsonElement element = context.serialize(src);
        JsonObject object = element.getAsJsonObject();
        object.addProperty("type", src.getClass().getSimpleName());
        return object;
    }
}
