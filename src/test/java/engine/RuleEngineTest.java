package engine;


import model.Authorization;
import model.Context;
import model.rules.Rule;
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
    public void setup(){
        mock = new MockHelper();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void whenAllRulesAllowAccess_AccessIsGranted(){
        mock.allRulesAllowAccess();
        Authorization authorization = ruleEngine.retrieveAuthorizationForContext(rules, context);
        assertThat(authorization.hasAccess(),is(true));
    }

    @Test
    public void whenOneRuleDeniesAccess_AccessIsDenied(){
        mock.oneRuleDeniesAccess();
        Authorization authorization = ruleEngine.retrieveAuthorizationForContext(rules, context);
        assertThat(authorization.hasAccess(),is(false));
    }

    private class MockHelper{

        private void allRulesAllowAccess() {
            rules = new ArrayList<Rule>();
            Rule rule = mockRule(true);
            rules.add(rule);
        }

        private void oneRuleDeniesAccess() {
            rules = new ArrayList<Rule>();
            Rule rule = mockRule(false);
            rules.add(rule);
        }

        private Rule mockRule(final boolean access) {
            Rule rule = Mockito.mock(Rule.class);
            when(rule.isFulfilledIn(any(Context.class))).thenReturn(access);
            return rule;
        }
    }

}