package com.pf4j.demo;

import org.pf4j.Extension;

import java.time.LocalDateTime;

@Extension
public class RulePlugin02 implements RuleApi{
    @Override
    public String ruleImplementationMethod(String param) {
        return "*******rule 002 has been called********";
    }
}
