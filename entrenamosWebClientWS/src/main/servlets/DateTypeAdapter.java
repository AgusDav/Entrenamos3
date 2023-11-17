package main.servlets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(dateFormat.format(date));
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            return dateFormat.parse(json.getAsString());
        } catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }
}
