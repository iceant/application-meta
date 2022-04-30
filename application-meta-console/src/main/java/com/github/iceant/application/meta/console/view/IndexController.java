package com.github.iceant.application.meta.console.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(path = {"", "/", "/index", "/index.htm"})
    public String index(){
        return "index.html";
    }
}
