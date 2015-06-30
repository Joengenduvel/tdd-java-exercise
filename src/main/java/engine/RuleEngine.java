package engine;

import model.Authorization;
import model.Context;
import model.rules.Rule;

import java.util.List;

public class RuleEngine {

    public Authorization retrieveAuthorizationForContext(List<Rule> rules, Context context) {
        return new Authorization(true);
    }
}
