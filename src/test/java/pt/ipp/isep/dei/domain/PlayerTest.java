package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Player} class.
 * <p>
 * Covers constructor, getters, fund operations, and string output.
 */
class PlayerTest {

    private Player player;

    /**
     * Initializes a player before each test.
     */
    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer", 1000.0);
    }

    /**
     * Tests the constructor and getters.
     */
    @Test
    void testConstructorAndGetters() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(1000.0, player.getBalance(), 0.001);
    }

    /**
     * Tests if the player can afford a given cost.
     */
    @Test
    void testCanAfford() {
        assertTrue(player.canAfford(500));
        assertTrue(player.canAfford(1000));
        assertFalse(player.canAfford(1500));
    }

    /**
     * Tests deducting funds when balance is sufficient.
     */
    @Test
    void testDeductFundsSuccess() {
        boolean result = player.deductFunds(500);
        assertTrue(result);
        assertEquals(500.0, player.getBalance(), 0.001);
    }

    /**
     * Tests deducting funds when balance is insufficient.
     */
    @Test
    void testDeductFundsFailure() {
        boolean result = player.deductFunds(1500);
        assertFalse(result);
        assertEquals(1000.0, player.getBalance(), 0.001);
    }

    /**
     * Tests adding funds to the balance.
     */
    @Test
    void testAddToBudget() {
        player.addToBudget(250.0);
        assertEquals(1250.0, player.getBalance(), 0.001);
    }

    /**
     * Tests the string representation of the player.
     */
    @Test
    void testToString() {
        String expected = "Player{name='TestPlayer', balance=1000.00}";
        assertEquals(expected, player.toString());
    }
}
