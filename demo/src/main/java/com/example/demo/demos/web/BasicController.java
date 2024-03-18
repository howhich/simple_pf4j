/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.demos.web;


import com.pf4j.demo.RuleApi;
import org.pf4j.JarPluginManager;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
public class BasicController {
    @Autowired
    private PluginBean pluginBean;
    @Autowired
    private User user;
    @Autowired
    private BeanTest beanTest;
    @Autowired
    private BeanConfig beanConfig;
    @Autowired
    private JarPluginManager jarPluginManager;


    // http://127.0.0.1:8080/plugins
    //该接口用于动态添加插件
    @RequestMapping("/plugins")
    public String plugins() throws IOException {
        JarPluginManager pluginManager = new JarPluginManager();

        byte[] bytes = Files.readAllBytes(Paths.get("F:\\fuchuang\\pf4jTset\\demo\\src\\main\\java\\com\\example\\demo\\plugin.txt"));
        String pluginName = new String(bytes, StandardCharsets.UTF_8);
        pluginBean.startPlugin(pluginName,pluginManager);

        pluginBean.stopPlugins(pluginName,pluginManager);

//        Plugins.startPlugin("rule-plugin");
//        Plugins.stopPlugins("rule-plugin");
        return "done!";
    }
    @RequestMapping("/plgLoad")
    public String plgLoad(){
        String pluginPath = "";
        jarPluginManager.loadPlugin(Paths.get(pluginPath));
        return "Load Done";
    }
    @RequestMapping("/plgStart")
    public String plgStart(@RequestParam String pluginName){
        jarPluginManager.startPlugin(pluginName);
        // 得到插件中定义的扩展类集合
        List<RuleApi> extensions = jarPluginManager.getExtensions(RuleApi.class);
        for (RuleApi ruleApi:extensions){
            System.out.println(ruleApi.ruleImplementationMethod("插件(args here)测试"));
        }
        return "Started!";
    }
    @RequestMapping("/plgStop")
    public String plgStop(@RequestParam String pluginName){
        jarPluginManager.stopPlugin(pluginName);
        return "plgStopped";
    }
    @RequestMapping("/plgUnload")
    public String plgUnload(@RequestParam String pluginName){
        jarPluginManager.unloadPlugin(pluginName);
        return "plgUnloaded";
    }

    @RequestMapping("/shellTest/{command}")
    public List<String> execShell(@PathVariable String command){
        String commandOrPath = "cmd /c ping 127.0.0.1";
//        String commandOrPath = "cmd/ C:\\Users\\howhich\\Desktop\\shellTest.sh";
        List<String> result = new ArrayList<>();
        try {
            Process ps = Runtime.getRuntime().exec(commandOrPath);
            int exitValue = ps.waitFor();
            if(0!=exitValue){
                System.err.println("error! code is :" + exitValue);
            }

            InputStream in = ps.getInputStream();

//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer))!=-1){
                System.out.println("脚本返回的数据如下"+new String(buffer,0,len));
            }
//            String line;
//            while ((line = br.readLine()) != null ) {
//                System.out.println("脚本返回的数据如下： " + line);
//                result.add(line);
//            }
            in.close();
//            br.close();
        }catch (Exception e){
            System.err.println(e);
        }
        return result;
    }

    // http://127.0.0.1:8080/users
    @RequestMapping("/users")
    public String users(){
        Integer age = user.getAge();
//        Plugins.startPlugin("rule-plugin");
//        Plugins.stopPlugins("rule-plugin");
        System.out.println(age);
        String shout = beanTest.shout();
        System.out.println("shouted: " + shout);
        return age.toString();
    }
}
