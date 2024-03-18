package com.example.demo.demos.web;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanTest {
    @Bean
    public User user(){
        return new User("haha",12);
    }
    public String shout(){
        System.out.println("what the hell?");
        return "hahahaha!";
    }
}
