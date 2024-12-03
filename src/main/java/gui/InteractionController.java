package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InteractionController {

    @FXML
    private TextField inputField;

    private Stage stage;
    private int inputValue;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getInputValue() {
        return inputValue;
    }

    @FXML
    private void handleOkButton() {
        try {
            inputValue = Integer.parseInt(inputField.getText());
            stage.close();
        } catch (NumberFormatException e) {
            // Handle invalid input
            inputField.setText("");
            inputField.setPromptText("Por favor, insira um número inteiro.");
        }
    }
}