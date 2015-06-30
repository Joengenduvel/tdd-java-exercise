package engine;

import model.Authorization;
import model.Context;
import model.rules.Rule;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {

    public Authorization retrieveAuthorizationForContext(List<Rule> rules, Context context) {
        List<String> reasonsForDenial = new ArrayList<String>();
        for (Rule rule : rules) {
            if (!rule.isFulfilledIn(context)) {
                reasonsForDenial.add(rule.getReasonForDenial());
            }
        }
        return new Authorization(reasonsForDenial);
    }
}
