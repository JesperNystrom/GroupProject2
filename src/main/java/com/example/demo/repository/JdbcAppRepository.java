package com.example.demo.repository;

import com.example.demo.domain.Answer;
import com.example.demo.domain.Location;
import com.example.demo.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class JdbcAppRepository implements AppRepository {

    @Autowired
    private DataSource dataSource;


    @Override
    public List<Answer> listLocations(int id) {


        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id, answer, locationId FROM Answers WHERE LocationId = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                List<Answer> answers = new ArrayList<>();
                while (rs.next()) {
                    answers.add(rsAnswer(rs));
                }
                Collections.shuffle(answers);
                return answers;
            }

        } catch (SQLException e) {
            throw new AppRepositoryException(e);
        }

    }

    @Override
    public Location getQuestion(int id) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id, Location, Image, Question FROM Locations " +
                     "WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rsLocation(rs);

            }

        } catch (SQLException e) {
            throw new AppRepositoryException(e);
        }
    }


    private Answer rsAnswer(ResultSet rs) throws SQLException {
        return new Answer(rs.getString("answer"), rs.getInt("LocationId"), rs.getInt("id"));
    }


    private Location rsLocation(ResultSet rs) throws SQLException {
        return new Location(rs.getInt("id"), rs.getString("location"), rs.getString("image"), rs.getString("question"));
    }

    private Question rsQuestion(ResultSet rs) throws SQLException {
        return new Question(rs.getString("Question"));
    }


}