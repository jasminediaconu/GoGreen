package client.objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemTest {

    @Mock
    Item item1;
    @Mock
    Item item2;

    @BeforeEach
    void setUp() {
        item1 = mock(Item.class);
        item2 = mock(Item.class);

        when(item1.equals(item2)).thenReturn(true);
    }

    @Test
    void getItemID() {
    }

    @Test
    void getName() {
    }

    @Test
    void getType() {
    }

    @Test
    void getCo2() {
    }

    @Test
    void equals() {
    }
}