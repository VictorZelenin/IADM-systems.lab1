package ua.kpi.cad.iadms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping({"/", "/expert", "/questions"})
    public String index() {
        return "forward:/index.html";
    }
}
