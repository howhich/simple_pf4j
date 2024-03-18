package com.pf4j.demo;

import org.pf4j.Extension;
import org.pf4j.ExtensionPoint;

public interface RuleApi extends ExtensionPoint {

    /**
     * 规则实现方法
     *
     * @param param 入参
     * @return String
     * @author unidentifiable
     * @date 2022/4/7 14:37
     */
    public String ruleImplementationMethod(String param);
//    public void wirte()

}
