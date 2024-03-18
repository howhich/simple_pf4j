package com.pf4j.demo;

import org.pf4j.JarPluginManager;

import java.nio.file.Paths;
import java.util.List;

/**
 * @author unidentifiable
 * @date 2022/4/7
 */
public class RuleService {
    public static void main(String[] args) {
        // 插件ID
        String plugin = "rule-plugin";

        // 创建插件管理对象
        JarPluginManager pluginManager = new JarPluginManager();

        // 加载插件包
        pluginManager.loadPlugin(Paths.get("F:\\fuchuang\\pf4jTset\\rule-plugins\\target\\rule-plugins-1.0-SNAPSHOT.jar"));
        // 启动指定的插件及其依赖项
        pluginManager.startPlugin(plugin);

        // 得到插件中定义的扩展类集合
        List<RuleApi> extensions = pluginManager.getExtensions(RuleApi.class);

        for (RuleApi ruleApi : extensions) {
            // 执行方法
            System.out.println(ruleApi.ruleImplementationMethod("测试插件"));
        }

        // 停用插件
        pluginManager.stopPlugin(plugin);
        // 卸载插件包
        pluginManager.unloadPlugin(plugin);
    }
}

