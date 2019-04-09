package se.BTH.ITProjectManagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @GetMapping(value={"/","/api/", "/api/hello"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="Administrator") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
}
