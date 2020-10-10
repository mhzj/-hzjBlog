package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;

/**
 * @author  hzj
 * @date 2020年8月24日17:15:20
 */
@RestController
public class HelloWorldController {
    @RequestMapping(value = "/public",method = RequestMethod.POST)
    public String Index(){
        return "form.html";
    }

}
