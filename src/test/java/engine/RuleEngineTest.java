package engine;


import model.Authorization;
import model.Context;
import model.rules.Rule;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class RuleEngineTest {

    private MockHelper mock;

    private RuleEngine ruleEngine;

    private List<Rule> rules;

    private Context context;

    @Before
    public void setup() {
        mock = new MockHelper();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void whenAllRulesAllowAccess_AccessIsGranted() {
        mock.allRulesAllowAccess();
        Authorization authorization = ruleEngine.retrieveAuthorizationForContext(rules, context);
        assertThat(authorization.hasAccess(), is(true));
    }

    @Test
    public void whenOneRuleDeniesAccess_AccessIsDenied() {
        mock.oneRuleDeniesAccess();
        Authorization authorization = ruleEngine.retrieveAuthorizationForContext(rules, context);
        assertThat(authorization.hasAccess(), is(false));
    }

    @Test
    public void whenMultipleRulesFail_AllReasonsForDenialAreAvailable() {
        String reason1 = "Underage";
        String reason2 = "Not a working day";
        mock.multipleRulesFailWithReasons(reason1, reason2);
        Authorization authorization = ruleEngine.retrieveAuthorizationForContext(rules, context);
        assertThat(authorization.getReasonsForDenial(), IsIterableContainingInAnyOrder.containsInAnyOrder(reason1, reason2));
    }

    private class MockHelper {

        private void allRulesAllowAccess() {
            rules = new ArrayList<Rule>();
            Rule rule = mockRule(true, null);
            rules.add(rule);
        }

        private void oneRuleDeniesAccess() {
            rules = new ArrayList<Rule>();
            Rule rule = mockRule(false, null);
            rules.add(rule);
        }

        private Rule mockRule(final boolean access, String reason) {
            Rule rule = Mockito.mock(Rule.class);
            when(rule.isFulfilledIn(any(Context.class))).thenReturn(access);
            when(rule.getReasonForDenial()).thenReturn(reason);
            return rule;
        }

        public void multipleRulesFailWithReasons(String... reasons) {
            rules = new ArrayList<Rule>();
            for (String reason : reasons) {
                Rule rule = mockRule(false, reason);
                rules.add(rule);
            }
        }
    }

}