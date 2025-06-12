package pt.ipp.isep.dei.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.domain.*;
import pt.ipp.isep.dei.repository.Repositories;
import pt.ipp.isep.dei.ui.ApplicationSession;
import pt.ipp.isep.dei.ui.MainFX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateScenarioFXController {

    @FXML private TextField scenarioNameField;
    @FXML private TextField startYearField;
    @FXML private TextField endYearField;
    @FXML private TextField industryFactorField;
    @FXML private CheckBox steamCheckBox;
    @FXML private CheckBox dieselCheckBox;
    @FXML private CheckBox electricCheckBox;
    @FXML private TextField portNameField;
    @FXML private TextArea importArea;
    @FXML private TextArea exportArea;
    @FXML private TextArea transformArea;
    @FXML private Label feedbackLabel;

    private final List<Port> ports = new ArrayList<>();
    private final CreateScenarioController ctrl = new CreateScenarioController();

    @FXML
    public void handleAddPort() {
        try {
            String name = portNameField.getText().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Port name is required.");

            List<String> imports = parseList(importArea.getText());
            List<String> exports = parseList(exportArea.getText());
            List<String> transforms = parseList(transformArea.getText());

            Port port = new Port(name);
            imports.forEach(port::addImport);
            exports.forEach(port::addExport);
            transforms.forEach(port::addTransform);

            ports.add(port);
            feedbackLabel.setText("✅ Port added: " + name);
            feedbackLabel.setStyle("-fx-text-fill: green;");

            portNameField.clear();
            importArea.clear();
            exportArea.clear();
            transformArea.clear();
        } catch (IllegalArgumentException e) {
            feedbackLabel.setText(e.getMessage());
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void handleCreateScenario() {
        try {
            String name = scenarioNameField.getText().trim();
            int start = Integer.parseInt(startYearField.getText().trim());
            int end = Integer.parseInt(endYearField.getText().trim());
            double factor = Double.parseDouble(industryFactorField.getText().trim());

            Map map = ApplicationSession.getInstance().getCurrentMap();
            if (map == null) throw new IllegalStateException("No map is loaded.");

            PeriodTime period = new PeriodTime(start, end);
            Scenario scenario = ctrl.createScenario(name, map, period);
            ctrl.setIndustryGenerationFactor(scenario, factor);

            if (steamCheckBox.isSelected()) addAllMatching(scenario, Steam.class);
            if (dieselCheckBox.isSelected()) addAllMatching(scenario, Diesel.class);
            if (electricCheckBox.isSelected()) addAllMatching(scenario, Electric.class);

            ports.forEach(p -> ctrl.addPort(scenario, p));

            if (ctrl.saveScenario(scenario)) {
                ApplicationSession.getInstance().setCurrentScenario(scenario);
                Repositories.getInstance().getScenarioRepository().setActiveScenario(scenario);
                feedbackLabel.setText(" Scenario created successfully!");
                feedbackLabel.setStyle("-fx-text-fill: green;");
            } else {
                feedbackLabel.setText("Failed to save scenario.");
                feedbackLabel.setStyle("-fx-text-fill: red;");
            }

        } catch (Exception e) {
            feedbackLabel.setText(e.getMessage());
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void addAllMatching(Scenario scenario, Class<? extends FuelType> clazz) {
        Arrays.stream(LocomotiveType.values())
                .filter(l -> l.getFuelType().getClass().equals(clazz))
                .forEach(l -> ctrl.addLocomotiveType(scenario, l));
    }

    private List<String> parseList(String input) {
        if (input == null || input.trim().isEmpty()) return new ArrayList<>();
        return Arrays.asList(input.trim().split("\\s*,\\s*|\\s+"));
    }

    @FXML
    public void handleBack() {
        MainFX.goToEditorMenu();
    }
}
