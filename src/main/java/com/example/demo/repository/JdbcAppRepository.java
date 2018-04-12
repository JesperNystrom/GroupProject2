package com.example.demo.repository;

import com.example.demo.domain.Answer;
import com.example.demo.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAppRepository implements AppRepository {

    @Autowired
    private DataSource dataSource;

    // Backup
//    @Override
//    public List<Location> listLocations() {
//        try (Connection conn = dataSource.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT Id, Location, Image, Question FROM Locations")) {
//            List<Location> locations = new ArrayList<>();
//            while (rs.next()){
//                locations.add(rsLocation(rs));
//            }
//
//            return locations;
//        } catch (SQLException e) {
//            throw new AppRepositoryException(e);
//        }
//    }

    @Override
    public List<Answer> listLocations() {

        int randomQuestion = 4; // temporary, should come as an input variable or something..

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Answer FROM Answers " +
                     "WHERE LocationId = randomQuestion")) {
            List<Answer> locations = new ArrayList<>();

            while (rs.next()){
                locations.add(rsAnswer(rs));
            }

            return locations;
        } catch (SQLException e) {
            throw new AppRepositoryException(e);
        }
    }

    @Override
    public Location getQuestion() {

        // ?
        int randomQuestion = 4; // getRandom()

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id, Location, Image, Question FROM Locations " +
                     "WHERE id = ?")) {
            ps.setInt(1, randomQuestion);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rsLocation(rs);
            }

        } catch (SQLException e) {
            throw new AppRepositoryException(e);
        }
    }

//    @Override
//    public List<Answer> listAnswer() {
//        try (Connection conn = dataSource.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("select * from Locations inner join Answers\n" +
//                     "on Answers.LocationID=Locations.ID")) {
//
//            List<Answer> answers = new ArrayList<>();
//
//            while (rs.next() && ){
//                answers.add(rsAnswer(rs));
//            }
//
//            return answers;
//        } catch (SQLException e) {
//            throw new AppRepositoryException(e);
//        }
//    }

    private Answer rsAnswer(ResultSet rs) throws SQLException {
        return new Answer(rs.getString("answer"), rs.getInt("locationId"), rs.getInt("id"));
    }

    private Location rsLocation(ResultSet rs) throws SQLException {
        return new Location(rs.getInt("id"), rs.getString("location"), rs.getString("image"), rs.getString("question"));
    }
}
