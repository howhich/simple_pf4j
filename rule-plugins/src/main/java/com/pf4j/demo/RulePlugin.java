package com.pf4j.demo;

import org.pf4j.Extension;

import java.time.LocalDateTime;
@Extension
public class RulePlugin implements RuleApi{
    @Override
    public String ruleImplementationMethod(String param) {
        return "("+ LocalDateTime.now() + "接收到参数：" + param +")";
    }
}
