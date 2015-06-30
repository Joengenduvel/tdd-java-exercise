package engine;

import model.Authorization;
import model.Context;
import model.rules.Rule;

import java.util.Arrays;
import java.util.List;

public class RuleEngine {

    public Authorization retrieveAuthorizationForContext(List<Rule> rules, Context context) {
        for(Rule rule: rules){
            if(!rule.isFulfilledIn(context)){
                return new Authorization(false, Arrays.asList(rule.getReasonForDenial()));
            }
        }
        return new Authorization(true);
    }
}
