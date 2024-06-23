package demo.springboot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping(path="/healthcheck")
    public String healthCheck(){
        return "OK";
    }
}
