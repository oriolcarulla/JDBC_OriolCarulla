package controller;

import java.util.ArrayList;
import java.io.File;
import java.sql.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import DAO.AthleteDAO;
import org.w3c.dom.Document;

public class DBController {
    private String host;
    private String port;
    private String user;
    private String password;
    private String database;

    public DBController(String config_file) {
        File file = new File(config_file);

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            host = doc.getElementsByTagName("host").item(0).getTextContent();
            port = doc.getElementsByTagName("port").item(0).getTextContent();
            user = doc.getElementsByTagName("user").item(0).getTextContent();
            password = doc.getElementsByTagName("password").item(0).getTextContent();
            database = doc.getElementsByTagName("database").item(0).getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection dbConnect() {
        Connection connection = null;

        try {
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida correctamente con la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        return connection;
    }
}
