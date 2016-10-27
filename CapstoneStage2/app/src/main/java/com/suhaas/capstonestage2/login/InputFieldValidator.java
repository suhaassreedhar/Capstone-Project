package com.suhaas.capstonestage2.login;



public class InputFieldValidator {

    public boolean isValid(String input) {
        return isNotNull(input) && isNotBlank(input);
    }

    private boolean isNotNull(String input) {
        return input != null;
    }

    private boolean isNotBlank(String input) {
        return input.length() > 0;
    }

}
