package com.codeconverter.fscodeconverter.controller;

import com.codeconverter.fscodeconverter.model.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainController {
    @FXML
    AnchorPane anchorPainMain;
    @FXML
    Button btnAddToHistory, btnClearFields, btnClearHistory, btnSaveToFile;
    @FXML
    CheckBox checkBoxAutoClear;
    @FXML
    TextArea textAreaHistory;
    @FXML
    TextField textFieldInput, textFieldOutput;

    long MIN_VALUE = 1000;
    long MAX_VALUE = 4294967295L;

    ArrayList<Card> listCards = new ArrayList<>();

    public void initialize() {
        textFieldInput.setFocusTraversable(true);
        textFieldInput.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.equals("")) {
                btnAddToHistory.setDisable(Long.parseLong(t1) < MIN_VALUE || Long.parseLong(t1) > MAX_VALUE || !t1.matches("\\d+"));
                if (Long.parseLong(t1) < MIN_VALUE)
                    textFieldOutput.clear();
                if (Long.parseLong(textFieldInput.getText()) > MAX_VALUE)
                    textFieldInput.setText(textFieldInput.getText().replaceAll(".$", ""));
                if (Long.parseLong(t1) >= MIN_VALUE)
                    textFieldOutput.setText(String.valueOf(convertToNewHexCode(textFieldInput.getText())));
            }
        });
    }

    public int convertToNewHexCode(String inputCode) {
        String oldDecCode = inputCode.replaceAll("\\s+", "");
        String olcHexCode = Long.toHexString(Long.parseLong(oldDecCode));
        String newHexCode = olcHexCode.substring(2);
        return Integer.parseInt(newHexCode, 16);
    }

    private void addToHistory() {
        listCards.add(new Card(textFieldInput.getText(), textFieldOutput.getText()));
        if (textAreaHistory.getText().equals(""))
            textAreaHistory.setText(textFieldOutput.getText());
        else {
            textAreaHistory.setText(textAreaHistory.getText() + "\n" + textFieldOutput.getText());
            textAreaHistory.appendText("");
        }
    }

    public void onButtonAddToHistoryClick() {
        addToHistory();
        if (checkBoxAutoClear.isSelected())
            clearInputOutputFields();
    }

    public void onButtonClearFieldsClick() {
        clearInputOutputFields();
    }

    public void onCheckBoxAutoClearClick() {
        clearInputOutputFields();
        btnClearFields.setDisable(checkBoxAutoClear.isSelected());
    }

    private void clearInputOutputFields() {
        textFieldInput.clear();
        textFieldOutput.clear();
        textFieldInput.requestFocus();
    }

    public void onButtonSaveToFileClick() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Window stage = anchorPainMain.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select directory for save:");
        fileChooser.setInitialFileName("convertedCards_" + formatter.format(new Date()));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                PrintWriter writer;
                writer = new PrintWriter(file);
                writer.println(textAreaHistory.getText());
                writer.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        textFieldInput.requestFocus();
    }

    public void onButtonClearHistoryClick() {
        textAreaHistory.clear();
        textFieldInput.requestFocus();
    }
}