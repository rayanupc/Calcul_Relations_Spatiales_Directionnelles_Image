package calculechelle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CouleursTest {

	@Test
    public void testViolet() {
        assertEquals(-12320428, Couleurs.VIOLET);
    }

    @Test
    public void testJaune() {
        assertEquals(-137436, Couleurs.JAUNE);
    }

    @Test
    public void testVert() {
        assertEquals(-14506108, Couleurs.VERT);
    }

}
