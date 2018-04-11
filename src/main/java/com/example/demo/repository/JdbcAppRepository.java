package com.example.demo.repository;

import com.example.demo.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            while (rs.next() && locations.size() < 4){
                locations.add(rsLocation(rs));
            }

            return locations;
        } catch (SQLException e) {
            throw new AppRepositoryException(e);
        }
    }

    @Override
    public Location getLocation() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Id, Location, Image, Question FROM Locations")) {

            rs.next();
            return rsLocation(rs);

        } catch (SQLException e) {
            throw new AppRepositoryException(e);
        }
    }

    private Location rsLocation(ResultSet rs) throws SQLException {
        return new Location(rs.getInt("id"), rs.getString("location"), rs.getString("image"), rs.getString("question"));
    }
}
