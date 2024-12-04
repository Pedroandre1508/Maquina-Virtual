package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class InteractionController {

    @FXML
    private TextField inputField;

    @FXML
    private Label messageLabel;

    private Stage stage;
    private String inputValue;

    public void initialize() {
        // Adiciona um manipulador de eventos para a tecla "Enter" no campo de texto
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleEnterKey();
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setMessage(String message) {
        messageLabel.setText(messageLabel.getText() + "\n" + message);
    }

    public void enableInputField(boolean enable) {
        inputField.setDisable(!enable);
    }

    private void handleEnterKey() {
        inputValue = inputField.getText();
        inputField.clear();
        synchronized (this) {
            this.notify();
        }
    }
}