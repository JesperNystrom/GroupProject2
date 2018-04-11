package com.example.demo.repository;

import com.example.demo.domain.Location;

import java.util.List;

public interface AppRepository {
    List<Location> listLocations();
    Location getLocation();
}
