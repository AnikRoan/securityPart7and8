package com.example.securitypart7.config.security;

import org.springframework.stereotype.Component;

@Component
public class Demo4ConditionEvaluator {
    public boolean condition(){

        return true;//your complex authorization condition
    }
}
