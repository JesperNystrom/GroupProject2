package com.example.demo.repository;

import com.example.demo.domain.Answer;
import com.example.demo.domain.Location;

import java.util.List;

public interface AppRepository {
    List<Answer> listLocations(int id);
    Location getQuestion(int id);
//    List<Answer> listAnswer();
}
