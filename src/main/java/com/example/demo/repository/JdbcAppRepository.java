package com.example.demo.repository;

import com.example.demo.domain.Answer;
import com.example.demo.domain.Location;
import com.example.demo.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class JdbcAppRepository implements AppRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Location> listLocations() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Id, Location, Image, Question FROM Locations")) {
            List<Location> locations = new ArrayList<>();
            while (rs.next() && locations.size() < 4) {
                locations.add(rsLocation(rs));
            }

            return locations;
        } catch (SQLException e) {
            throw new AppRepositoryException(e);
        }
    }

    @Override
    public Location getQuestion() {


        Random rand = new Random();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        int randomQuestion = 0;

        for (int i = 0; i < list.size(); i++) {
            int randomIndex = rand.nextInt(list.size());
            randomQuestion = list.get(randomIndex);


            try (Connection conn = dataSource.getConnection();
                 PreparedStatement ps = conn.prepareStatement("SELECT ?, Location, Image, Question FROM Locations")) {
                ps.setInt(1, randomQuestion);
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    list.remove(randomQuestion);
                    return rsLocation(rs);

                }

            } catch (SQLException e) {
                throw new AppRepositoryException(e);
            }
        }
        return null;
    }



    /*@Override
    public List<Answer> listAnswer() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from Locations inner join Answers\n" +
                     "on Answers.LocationID=Locations.ID")) {

            List<Answer> answers = new ArrayList<>();

            while (rs.next() && ){
                answers.add(rsAnswer(rs));
            }

            return answers;
        } catch (SQLException e) {
            throw new AppRepositoryException(e);
        }
    }*/




    private Location rsLocation(ResultSet rs) throws SQLException {
        return new Location(rs.getInt("id"), rs.getString("location"), rs.getString("image"), rs.getString("question"));

        private Answer rsAnswer (ResultSet rs) throws SQLException {
            return new Answer(rs.getString("answer"), rs.getInt("locationId"));
        }

        private Location rsLocation (ResultSet rs) throws SQLException {
            return new Location(rs.getInt("id"), rs.getString("location"), rs.getString("image"), rs.getString("question"));
        }

        private Question rsQuestion (ResultSet rs) throws SQLException {
            return new Question(rs.getString("Question"));
        }


    }

