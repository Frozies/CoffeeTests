import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeaTest {

    @Test
    void getRegion() {
        Tea tea = new Tea();
        assertEquals(false, tea.cream);
        assertEquals(tea.ingredients.get(1), "tea leaves");
        assertEquals("China", tea.getRegion());
    }

    @Test
    void checkExpired() {
        Tea tea = new Tea(10, "weak");
        assertEquals(false, tea.expiration);
        assertEquals("weak", tea.getStrength());
    }
}