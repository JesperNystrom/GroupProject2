package com.example.demo.controller;

import com.example.demo.domain.Answer;
import com.example.demo.domain.Location;
import com.example.demo.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class AppController {

    @Autowired
    private AppRepository appRepository;

    @GetMapping("/game")
    public ModelAndView listLocations(HttpSession session) {
        List<Integer> list = (List<Integer>) session.getAttribute("List");
        if (list == null) {
            list = new ArrayList<>();
        }
        Random rand = new Random();
        int id;
        if (list.size() == 6) {
            return new ModelAndView("Score");
        }
        do {
            id = rand.nextInt(6) + 1;
        } while (duplicateExists(list, id));
        session.setAttribute("ID", id);
        list.add(id);
        session.setAttribute("List", list);


        return new ModelAndView("/game")

                .addObject("answers", appRepository.listLocations(id))
                .addObject("place", appRepository.getQuestion(id));
    }

    @GetMapping("/game/{id}")
    public ModelAndView listLocations(@PathVariable int id, HttpSession session) {
        Integer score = (Integer) session.getAttribute("Score");
        if (score == null) {
            score = 0;
        }
        int correctID = (Integer) session.getAttribute("ID");
        Location location = appRepository.getQuestion(correctID);
        List<Answer> answers = appRepository.listLocations(correctID);
        for (Answer answer : answers) {
            if (answer.getName().equalsIgnoreCase(location.getName())) {
                if (answer.getId() == id) {
                    score++;
                    session.setAttribute("Score", score);
                }
            }

        }
        return new ModelAndView("redirect:/game");
    }

    private boolean duplicateExists(List<Integer> list, int id) {
        for (Integer integer : list) {
            if (integer == id) {
                return true;
            }

        }
        return false;
    }

}
