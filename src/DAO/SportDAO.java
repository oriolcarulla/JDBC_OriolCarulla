package DAO;

import models.Sport;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SportDAO implements DAO<Sport> {
    private Connection connection;

    public SportDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Sport sport) {
        String query = "INSERT INTO public.deportes (nombre) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, sport.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al agregar el deporte: " + e.getMessage());
        }

    }

    @Override
    public List<Sport> getAll() {
        List<Sport> sports = new ArrayList<>();
        String query = "SELECT * FROM public.deportes";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sport sport = new Sport(resultSet.getInt("cod"),resultSet.getString("nombre"));
                sports.add(sport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los deportes: " + e.getMessage());
        }
        return sports;
    }

    public boolean checkSport(String sportName) {
        String query = "SELECT * FROM public.deportes WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, sportName);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getSportName(int id) {
        String query = "SELECT nombre FROM public.deportes WHERE cod = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getSportId(String sportName) {
        String query = "SELECT cod FROM public.deportes WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, sportName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("cod");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
