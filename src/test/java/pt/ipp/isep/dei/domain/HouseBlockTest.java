package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link HouseBlock} class.
 *
 * <p>These tests verify various aspects of the {@code HouseBlock} class, including:</p>
 * <ul>
 *     <li>The correct creation of a valid {@code HouseBlock} instance.</li>
 *     <li>Validation of inputs, such as ensuring the {@code position} is not {@code null}.</li>
 *     <li>Modification of the {@code position} using the setter method.</li>
 *     <li>The string representation of a {@code HouseBlock} object.</li>
 * </ul>
 */
public class HouseBlockTest {

    /**
     * Tests the creation of a valid {@link HouseBlock} and verifies its position.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>A {@code HouseBlock} is successfully created with the specified {@link Position}.</li>
     *     <li>The position of the created {@link HouseBlock} matches the provided {@link Position}.</li>
     * </ul>
     *
     * @throws AssertionError if the position of the {@link HouseBlock} does not match the expected value.
     */
    @Test
    void testValidHouseBlockCreation() {
        Position position = new Position(2, 3);
        HouseBlock houseBlock = new HouseBlock(position);
        assertEquals(position, houseBlock.getPosition());
    }

    /**
     * Tests that creating a {@link HouseBlock} with a {@code null} {@link Position}
     * throws an {@link IllegalArgumentException}.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>Construction fails when a {@code null} position is passed.</li>
     *     <li>An {@link IllegalArgumentException} is thrown with a message indicating that
     *         the position must not be {@code null}.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if the {@link Position} is {@code null}.
     */
    @Test
    void testConstructorNullPositionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new HouseBlock(null));
    }

    /**
     * Tests setting a valid {@link Position} for a {@link HouseBlock}.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>The position of the {@link HouseBlock} is successfully updated to the provided valid value.</li>
     *     <li>The {@link HouseBlock#getPosition()} correctly returns the newly set {@link Position}.</li>
     * </ul>
     *
     * @throws AssertionError if the position of the {@link HouseBlock} is not updated as expected.
     */
    @Test
    void testSetPositionValid() {
        Position position1 = new Position(1, 1);
        Position position2 = new Position(5, 6);
        HouseBlock houseBlock = new HouseBlock(position1);
        houseBlock.setPosition(position2);
        assertEquals(position2, houseBlock.getPosition());
    }

    /**
     * Tests that setting a {@code null} {@link Position} for a {@link HouseBlock}
     * throws an {@link IllegalArgumentException}.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>Setting the {@link Position} to {@code null} fails.</li>
     *     <li>An {@link IllegalArgumentException} is thrown with a message indicating that
     *         the position must not be {@code null}.</li>
     * </ul>
     *
     * @throws IllegalArgumentException if attempting to set the {@link Position} to {@code null}.
     */
    @Test
    void testSetPositionNullThrowsException() {
        Position position = new Position(1, 1);
        HouseBlock houseBlock = new HouseBlock(position);
        assertThrows(IllegalArgumentException.class, () -> houseBlock.setPosition(null));
    }

    /**
     * Tests the string representation of a {@link HouseBlock}.
     *
     * <p>Expected Behavior:</p>
     * <ul>
     *     <li>The {@link HouseBlock#toString()} method includes the class name and the current position.</li>
     *     <li>The string contains the word "HouseBlock" and the {@link Position} details.</li>
     * </ul>
     *
     * @throws AssertionError if the string representation does not include the expected information.
     */
    @Test
    void testToString() {
        Position position = new Position(3, 4);
        HouseBlock houseBlock = new HouseBlock(position);
        String str = houseBlock.toString();
        assertTrue(str.contains("HouseBlock"));
        assertTrue(str.contains("position=" + position));
    }
}
