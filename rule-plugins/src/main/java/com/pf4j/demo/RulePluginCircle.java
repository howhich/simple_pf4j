package com.pf4j.demo;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import java.time.LocalDateTime;

public class RulePluginCircle extends Plugin {
    public RulePluginCircle(PluginWrapper wrapper) {
        super(wrapper);
    }
    @Override
    public void start() {
        System.out.println("WelcomePlugin.start()");
    }

    @Override
    public void stop() {
        System.out.println("WelcomePlugin.stop()");
    }

    @Override
    public void delete() {
        System.out.println("WelcomePlugin.delete()");
    }
}
