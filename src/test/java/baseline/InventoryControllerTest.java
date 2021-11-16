package baseline;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InventoryControllerTest {

    private Items expected = new Items("A-aaa-11-22", "Name", BigDecimal.valueOf(20));

    @Test
    void addItem() {
    }

    @Test
    void testSaveToFile(){
        System.out.println(expected);
        assertEquals("A-aaa-11-22 Name 20", expected.toString());
    }
}