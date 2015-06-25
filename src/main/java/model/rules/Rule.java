package model.rules;

import model.Context;

public interface Rule {

    public String getReasonForDenial();

    public boolean isFulfilledIn(Context context);
}
