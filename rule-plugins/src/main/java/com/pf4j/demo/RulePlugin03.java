package com.pf4j.demo;

import org.pf4j.Extension;

@Extension
public class RulePlugin03 implements RuleApi{
    @Override
    public String ruleImplementationMethod(String param) {
        return "*******rule 003 has been called********";
    }
}
