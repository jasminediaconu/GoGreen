package server.helper;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class LocalDateSerializerTest {

    @Test
    void serialize() {
        LocalDate localDate = LocalDate.now().minusDays(12);
        String json = new Gson().toJson(localDate);
        LocalDate deserialized = new Gson().fromJson(json, LocalDate.class);
        Assert.assertEquals(localDate, deserialized);
    }
}