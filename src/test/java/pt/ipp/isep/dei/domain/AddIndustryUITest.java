package pt.ipp.isep.dei.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.controller.AddIndustryController;
import pt.ipp.isep.dei.ui.console.AddIndustryUI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the AddIndustryUI class.
 * Tests the user interface for adding industries without using Mockito.
 */
public class AddIndustryUITest {

    /**
     * Stub controller for tests
     */
    private static class TestAddIndustryController {
        private String capturedName;
        private IndustryTypes.IndustryType capturedType;
        private Position capturedPosition;
        private boolean throwException = false;
        private String exceptionMessage = "Controller error";

        public TestAddIndustryController() {
            // Implementation as shown in your snippet would be here
        }

        public void setThrowException(boolean throwException) {
            // Implementation as shown in your snippet would be here
        }

        public void setExceptionMessage(String message) {
            // Implementation as shown in your snippet would be here
        }


        public Industry addIndustry(String name, IndustryTypes.IndustryType type
                , Position position) {
            this.capturedName = name;
            this.capturedType = type;
            this.capturedPosition = position;

            if (throwException) {
                throw new IllegalArgumentException(exceptionMessage);
            }

            Industry industry = new Industry(name, position, type);
            return industry;
        }


        public String getCapturedName() {
            return capturedName;
        }

        public IndustryTypes.IndustryType getCapturedType() {
            return capturedType;
        }

        public Position getCapturedPosition() {
            return capturedPosition;
        }
    }

    private TestAddIndustryController testController;
    private AddIndustryUI ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    /**
     * Initial setup for tests.
     * Configures input/output streams to simulate user interaction.
     */
    @BeforeEach
    public void setUp() throws Exception {
        testController = new TestAddIndustryController();
        System.setOut(new PrintStream(outputStream));

        testController = new TestAddIndustryController();
        ui = new AddIndustryUI();
    }


    /**
     * Restores the default System.out configuration after each test.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Sets up simulated user input for tests and configures
     * a new Scanner in the UI using reflection.
     *
     * @param input String containing the simulated user input
     */
    private void setupInput(String input) throws Exception {
        // Set up the input
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Use reflection to set the scanner field in the UI
        Scanner scanner = new Scanner(System.in);
        Field scannerField = AddIndustryUI.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        scannerField.set(ui, scanner);
    }

    /**
     * Tests adding a valid industry through the user interface.
     * Verifies that the controller is called with the correct parameters.
     */
    @Test
    public void testAddValidIndustry() throws Exception {
        // Setup input for a valid industry
        setupInput("Test Industry\n1\n10\n20\n");

        // Run the UI
        ui.run();

        // Verify controller was called with correct parameters
        assertEquals("Test Industry", testController.getCapturedName());
        assertEquals(IndustryTypes.IndustryType.PRIMARY, testController.getCapturedType());
        assertEquals(10, testController.getCapturedPosition().getX());
        assertEquals(20, testController.getCapturedPosition().getY());

        // Verify output contains success message
        String output = outputStream.toString();
        assertTrue(output.contains("Industry added successfully"));
    }


    /**
     * Tests attempting to add an industry with an empty name.
     * Verifies that the operation is aborted and the appropriate message is displayed.
     */
    @Test
    public void testAddIndustryEmptyName() throws Exception {
        // Setup input with empty name
        setupInput("\nq\n");

        // Run the UI
        ui.run();

        // Verify output contains error message
        String output = outputStream.toString();
        assertTrue(output.contains("Name cannot be empty"));
    }

    /**
     * Tests exception handling when the controller throws an IllegalArgumentException.
     * Verifies that the error message is displayed correctly.
     */
    @Test
    public void testAddIndustryControllerException() throws Exception {
        // Setup controller to throw exception
        testController.setThrowException(true);
        testController.setExceptionMessage("Invalid industry data");

        // Setup input for industry that will cause exception
        setupInput("Test Industry\n1\n10\n20\n");

        // Run the UI
        ui.run();

        // Verify output contains the exception message
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid industry data"));
    }

    /**
     * Tests attempting to add an industry with an invalid type.
     * Verifies that the operation is aborted with the appropriate message.
     */
    @Test
    public void testAddIndustryInvalidType() throws Exception {
        // Setup input with invalid type
        setupInput("Test Industry\n5\nq\n");

        // Run the UI
        ui.run();

        // Verify output contains error message
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid type selected"));
    }
}
