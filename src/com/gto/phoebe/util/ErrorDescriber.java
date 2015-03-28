package com.gto.phoebe.util;

import java.util.Arrays;
import java.util.List;

public class ErrorDescriber {
    private String message;
    private List<String> parameters;

    public ErrorDescriber(String message, String... parameters) {
        this.message = message;
        this.parameters = Arrays.asList(parameters);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("Error: ");
        ret.append(message);
        for (String string : parameters) {
            ret.append(string + " ");
        }
        return ret.toString();
    }
}
