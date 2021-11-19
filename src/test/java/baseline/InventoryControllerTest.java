package baseline;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InventoryControllerTest {

    private Items expected = new Items("A-1C2-5GG-1K5", "PS5", "$599.99");

    @Test
    void addItem() {
        String number = "A-1C2-5GG-1K5";
        String name = "PS5";
        String value = "$599.99";

        assertEquals(number, expected.getSerialNum());
        assertEquals(name, expected.getName());
        assertEquals(value, expected.getValue());

        System.out.println();
    }

    @Test
    void testSaveToFile(){
        System.out.println(expected);
        assertEquals("A-1C2-5GG-1K5\tPS5\t$599.99", expected.toString());
    }
}