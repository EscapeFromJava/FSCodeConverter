package com.codeconverter.fscodeconverter.model;

public class Card {
    private String inputCode;
    private String outputCode;

    public Card(String inputCode, String outputCode) {
        this.inputCode = inputCode;
        this.outputCode = outputCode;
    }

    @Override
    public String toString() {
        return "Card{" + inputCode + " | " + outputCode + "}";
    }
}
