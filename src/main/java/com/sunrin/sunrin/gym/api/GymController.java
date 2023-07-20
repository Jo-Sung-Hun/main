package com.sunrin.sunrin.gym.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GymController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getGymList() {
        return "Hello World!";
    }
}
