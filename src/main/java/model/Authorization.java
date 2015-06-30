package model;

import java.util.List;

public final class Authorization{

    private boolean hasAccess;
    private List<String> reasonsForDenial;

    public boolean hasAccess() {
        return hasAccess;
    }
}
