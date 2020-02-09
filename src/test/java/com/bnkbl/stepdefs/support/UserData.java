package com.bnkbl.stepdefs.support;

import java.util.List;

public class UserData {
    // Keys for accessing user data, they match table headings in the feature.
    public static final String NAME = "Name";
    public static final String AGE = "Age";

    private List<String> returnedIds;
    private List<String> returnedNames;

    public List<String> getReturnedIds() {
        if (returnedIds == null) {
            throw new IllegalStateException(
                    "returnedIds has not been initialised, are you using the step out of order?"
            );
        }
        return returnedIds;
    }

    public void setReturnedIds(List<String> returnedIds) {
        this.returnedIds = returnedIds;
    }

    public List<String> getReturnedNames() {
        if (returnedNames == null) {
            throw new IllegalStateException(
                    "returnedNames has not been initialised, are you using the step out of order?"
            );
        }
        return returnedNames;
    }

    public void setReturnedNames(List<String> returnedNames) {
        this.returnedNames = returnedNames;
    }
}
