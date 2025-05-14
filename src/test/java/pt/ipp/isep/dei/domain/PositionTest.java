package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    void testEquals_SameAttributes_ShouldReturnTrue() {
        Position pos1 = new Position(10, 20);
        Position pos2 = new Position(10, 20);

        assertEquals(pos1, pos2);
    }

    @Test
    void testEquals_DifferentAttributes_ShouldReturnFalse() {
        Position pos1 = new Position(10, 20);
        Position pos2 = new Position(15, 25);

        assertNotEquals(pos1, pos2);
    }

    @Test
    void testToString_ShouldReturnFormattedString() {
        Position position = new Position(10, 20);

        assertEquals("(10, 20)", position.toString());
    }


}
