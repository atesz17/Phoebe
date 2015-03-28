package com.gto.phoebe.util;

import java.util.ArrayList;
import java.util.List;

public class ErrorList {

    List<ErrorDescriber> errors = new ArrayList<ErrorDescriber>();


    public void add(ErrorDescriber errorDescriber) {
        errors.add(errorDescriber);
    }

    public void addAll(List<ErrorDescriber> errorDescribers) {
        errors.addAll(errorDescribers);
    }

    public List<ErrorDescriber> getErrors() {
        return errors;
    }
}
