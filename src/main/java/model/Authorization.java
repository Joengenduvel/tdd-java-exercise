package model;

import java.util.List;

public final class Authorization{

    private boolean access;
    private List<String> reasonsForDenial;

    public Authorization(boolean access) {
        this.access = access;
    }

    public boolean hasAccess() {
        return access;
    }

    public List<String> getReasonsForDenial() {
        return reasonsForDenial;
    }
}
