package client.objects;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {

    Item item1;
    Item item2;

    @BeforeEach
    void setUp() {
        item1 = new Item(1,"pizza","food", 10);
        item2 = new Item(1,"pizza", "food", 10);
    }

    @Test
    void getItemID() {
        Assert.assertEquals(item1.getItemID() == 1, true);
    }

    @Test
    void getName() {
        Assert.assertEquals(item1.getName().equals("pizza"), true);
    }

    @Test
    void getType() {
        Assert.assertEquals(item1.getType().equals("food"), true);
    }

    @Test
    void getCo2() {
        Assert.assertEquals(item1.getCo2() == 10, true);
    }

    @Test
    void equalsNull() {
        Assert.assertEquals(item1.equals(null), false);
    }

    @Test
    void equalsSelf() {
        Assert.assertEquals(item1.equals(item1), true);
    }

    @Test
    void equalsSame() {
        Assert.assertEquals(item1.equals(item2), true);
    }

    @Test
    void equalsOther() {
        Item item3 = new Item(2,"publictransport", "transport", 100);
        Assert.assertEquals(item1.equals(item3), false);
    }
}