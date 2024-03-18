package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void shellTest(){
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("notepad.exe");
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("我想被打印...");
    }
    @Test
    void ShellTest01(){
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("cmd /c python C:\\Users\\howhich\\Desktop\\shellTest\\pytest.py");
            int exitValue = process.waitFor();
            if(0!=exitValue){
                System.err.println("Error!" + exitValue);
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"gbk"));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s = null;

            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            while ((s = in.readLine()) != null) {
                System.err.println(s);
            }


        }catch (Exception e){
            System.err.println(e);
        }
    }
}
