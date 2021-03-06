package client.serializer;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class LocalDateDeserializerTest {

    @Test
    void deserialize() {
        LocalDate localDate = LocalDate.now().minusMonths(34);
        String json = new Gson().toJson(localDate);
        LocalDate deserialized = new Gson().fromJson(json, LocalDate.class);
        Assert.assertEquals(localDate, deserialized);
    }
}