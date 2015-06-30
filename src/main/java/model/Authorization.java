package model;

import java.util.List;

public final class Authorization{

    private final List<String> reasonsForDenial;

    public Authorization( List<String> reasonsForDenial) {
        this.reasonsForDenial = reasonsForDenial;
    }

    public boolean hasAccess() {
        return reasonsForDenial == null || reasonsForDenial.isEmpty();
    }

    public List<String> getReasonsForDenial() {
        return reasonsForDenial;
    }
}
