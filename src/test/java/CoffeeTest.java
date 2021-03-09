import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeTest {

    @Test
    void getCaffeine() {
        Coffee c = new Coffee();
        assertEquals("water", c.ingredients.get(0));
        assertEquals("coffee beans", c.ingredients.get(1));
        assertEquals(5, c.getTaste());
        assertEquals(2, c.getCaffeine());
    }

    @Test
    void getAllergens() {
        Coffee c = new Coffee("strong", "espresso", "cream");
        assertEquals("espresso", c.ingredients.get(1));
        assertEquals("cream", c.ingredients.get(2));
        assertEquals("strong", c.getStrength());
        assertEquals("caffeine dairy", c.getAllergens());
    }
}