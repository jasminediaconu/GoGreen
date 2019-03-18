package server.helper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
    /**
     * This function will make sure that the LocalDate clas will be properly deserialized,
     * to prepare for JSON requests.
     *
     * @param json    JsonElement type
     * @param typeOfT Type type
     * @param context JsonDeserialization type
     * @return a LocalDate class
     * @throws JsonParseException when JSON cannot be parsed
     */
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH));
    }
}
