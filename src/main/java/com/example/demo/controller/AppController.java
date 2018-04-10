package com.example.demo.controller;

import com.example.demo.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @Autowired
    private AppRepository appRepository;

    @GetMapping("/game")
    public ModelAndView listLocations() {
        return new ModelAndView("/game")
                .addObject("locations", appRepository.listLocations());
    }

}
