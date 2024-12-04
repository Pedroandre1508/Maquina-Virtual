package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class InteractionController {

    @FXML
    private TextArea messageTextArea;

    @FXML
    private TextField inputTextField;

    @FXML
    private Button sendButton;

    private Stage stage;
    private String inputValue;

    public void initialize() {
        // Adiciona um manipulador de eventos para a tecla "Enter" no campo de texto
        inputTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSendButton();
            }
        });

        // Adiciona um listener para monitorar mudanças no texto do campo de entrada
        inputTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            sendButton.setDisable(newValue.trim().isEmpty());
        });

        // Inicialmente, desabilita o botão de envio
        sendButton.setDisable(true);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setMessage(String message) {
        messageTextArea.appendText("\n" + message);
    }

    public void enableInputField(boolean enable) {
        inputTextField.setDisable(!enable);
    }

    @FXML
    private void handleSendButton() {
        inputValue = inputTextField.getText();
        inputTextField.clear();
        synchronized (this) {
            this.notify();
        }
    }
}