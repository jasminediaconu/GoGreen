package server.helper;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer implements JsonSerializer<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * This function will make sure that the LocalDate class will be properly serialized, given the yyyyMMdd format,
     * to prepare for JSON requests.
     * @param localDate LocalDate type
     * @param srcType Type type
     * @param context JsonSerialization type
     * @return a JsonElement with the LocalDate, but then serialized
     */
    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType,
                                 JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDate));
    }
}
