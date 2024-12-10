package DAO;

import models.Athlete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AthleteDAO implements DAO<Athlete> {
    private Connection connection;

    public AthleteDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Athlete athlete) {
        String query = "INSERT INTO public.deportistas (nombre, cod_deporte) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, athlete.getName());
            statement.setInt(2, athlete.getSport());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al agregar el atleta: " + e.getMessage());
        }
    }

    @Override
    public List<Athlete> getAll() {
        List<Athlete> athletes = new ArrayList<>();
        String query = "SELECT d.nombre AS athlete_name, s.cod AS sport_id " +
                "FROM public.deportistas AS d " +
                "JOIN public.deportes AS s ON d.cod_deporte = s.cod";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("athlete_name");
                int sport = resultSet.getInt("sport_id");
                Athlete athlete = new Athlete(name, sport);
                athletes.add(athlete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los atletas: " + e.getMessage());
        }
        return athletes;
    }

    public boolean checkAthlete(String athleteName) {
        String query = "SELECT * FROM public.deportistas WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, athleteName);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Athlete> getAthletesBySport(int sportId) {
        List<Athlete> athletes = new ArrayList<>();
        String query = "SELECT * FROM public.deportistas WHERE cod_deporte = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sportId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("nombre");
                int sport = resultSet.getInt("cod_deporte");
                Athlete athlete = new Athlete(name, sport);
                athletes.add(athlete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return athletes;
    }

    public Athlete getAthlete(String athleteName) {
        String query = "SELECT * FROM public.deportistas WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, athleteName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("nombre");
                int sport = resultSet.getInt("cod_deporte");
                return new Athlete(name, sport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
