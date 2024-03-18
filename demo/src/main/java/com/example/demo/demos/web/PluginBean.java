package com.example.demo.demos.web;


import com.pf4j.demo.RuleApi;
import lombok.Data;
import org.pf4j.JarPluginManager;
import org.pf4j.PluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.List;
@Data

public class PluginBean {

    public void startPlugin(String plugin,JarPluginManager pluginManager){
        // 插件ID
//        String plugin = "rule-plugin";

        // 创建插件管理对象
//        JarPluginManager pluginManager = new JarPluginManager();
        //TODO 改成redis Bean读取不了 或者 Bean懒加载？ 懒加载加载一次 后续还是无法更改 所以改为redis

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

    }
    public void stopPlugins(String plugin,JarPluginManager pluginManager){
        // 停用插件
        pluginManager.stopPlugin(plugin);
        // 卸载插件包
        pluginManager.unloadPlugin(plugin);
    }

}
