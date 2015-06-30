package model;

import java.util.List;

public final class Authorization{

    private final boolean access;
    private final List<String> reasonsForDenial;

    public Authorization(boolean access) {
        this(access, null);
    }

    public Authorization(boolean access, List<String> reasonsForDenial) {
        this.access = access;
        this.reasonsForDenial = reasonsForDenial;
    }

    public boolean hasAccess() {
        return access;
    }

    public List<String> getReasonsForDenial() {
        return reasonsForDenial;
    }
}
