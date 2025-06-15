package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Delivery} class.
 * <p>
 * Verifies correct initialization, accessors, and increment logic.
 */
class DeliveryTest {

    /**
     * Tests constructor initializes with count = 1 and correct type.
     */
    @Test
    void testConstructorAndGetters() {
        Delivery delivery = new Delivery(CargoType.FOOD);
        assertEquals(CargoType.FOOD, delivery.getType());
        assertEquals(1, delivery.getCount());
    }

    /**
     * Tests incrementing the delivery count.
     */
    @Test
    void testIncrement() {
        Delivery delivery = new Delivery(CargoType.MAIL);
        delivery.increment();
        assertEquals(2, delivery.getCount());

        delivery.increment();
        assertEquals(3, delivery.getCount());
    }
}
