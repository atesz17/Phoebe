package com.gto.phoebe.util;

public class PhoebeException extends Exception {

    ErrorList errorList;

    public PhoebeException(ErrorList errorList) {
        this.errorList = errorList;
    }

    public PhoebeException(ErrorDescriber error) {
        errorList = new ErrorList();
        errorList.add(error);
    }

    public String getMessage() {
        StringBuilder message = new StringBuilder("");
        for (ErrorDescriber errorDescriber : errorList.getErrors()) {
            message.append(errorDescriber.toString() + "\n");
        }
        return message.toString();
    }

}
