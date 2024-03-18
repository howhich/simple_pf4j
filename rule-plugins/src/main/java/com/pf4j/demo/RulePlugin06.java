package com.pf4j.demo;

import org.pf4j.Extension;

@Extension
public class RulePlugin06 implements RuleApi{
    @Override
    public String ruleImplementationMethod(String param) {
        return "*******rule 006 has been called********";
    }
}
