package com.example.securitypart7.controller;

import com.example.securitypart7.config.security.Demo4ConditionEvaluator;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController

public class DemoController {



    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('read')")//security access have just user with role "read"
    //hasAuthority(), hasAnyAuthority(),hasRole(),hasAnyRole()
    public String demo() {
        return "DEMO:-))";
    }

    @GetMapping("/demo2")
    @PreAuthorize("hasAnyAuthority('write','read')")//security access have just user with role "read"
    public String demo2() {
        return "DEMO-2 WOW))";
    }

    @GetMapping("/demo3/{smth}")//if we want to have access wi will write name user which we have in app
    //if you write wrong name it is will not work
    @PreAuthorize("#something == authentication.name")//authentication from securityContext
    // or we can use more parameters like---->
    //@PreAuthorize("(#something == authentication.name) or "+ "hasAnyAuthority('write','read')")
    public String demo3(@PathVariable("smth") String something){
        var a = SecurityContextHolder.getContext().getAuthentication();

        return "DEMO3 awesome";
    }
    @GetMapping("/demo4/{smth}")
    //whe we use bean we write @
    //always use methods
    @PreAuthorize("@demo4ConditionEvaluator.condition()")//here is class Demo4ConditionEvaluator and return true
    //it's mean we can write anything
    public String demo4(){
        return "DEMO 4";
    }


    @GetMapping("/demo5")
    @PostAuthorize("returnObject =='PostAuthorize'")//after the method is mainly used when we want to restrict the access to same returned variable
    public String demo5(){
        System.out.println(")");//newer use @PostAuthorize when methods that change data
        return "PostAuthorize";
    }


    //@PreFiltr=>works with either array collection

    @GetMapping("/demo6")
    @PreFilter("filterObject.contains('a')")
    public String demo6(@RequestBody List<String> values){
        System.out.println("VALUES: "+values);//return list which has element includes 'a'
        return "PreFiltr";
    }

    //@PostFilter=>the return type must be either a Collection or an arrey
    @GetMapping("/demo7")
   @PostFilter("filterObject.contains('a')")
    public List<String> demo7(){
        List<String> lisr =new ArrayList<>();
        Collections.addAll(lisr,"abcd","wert","qaay","wrety");

        return lisr;
                //List.of("abcd","wert","qaaz","wrty");//list.of creates immutable collection
    }





}
