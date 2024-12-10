package controller;

import DAO.AthleteDAO;
import DAO.SportDAO;
import models.*;
import view.View;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;


public class SportManager {
    private static String DB = "database.xml";
    public static void main(String[] args){
        DBController read = new DBController(DB);
        Connection db = read.dbConnect();
        SportDAO sportDAO = new SportDAO(db);
        AthleteDAO athleteDAO = new AthleteDAO(db);
        Scanner input = new Scanner(System.in);
        View view = new View();
        boolean exit_program = false;
        while(!exit_program){
            view.displayStartMenu();
            if (!input.hasNextInt()) {
                input.next();
                view.inValidOption();
                continue;
            }
            int choice = input.nextInt();

            switch (choice){
                case 1:
                    boolean exit_sportForm = false;
                    while(!exit_sportForm) {
                        view.SportForm();
                        String sport_name = input.next();
                        if (sportDAO.checkSport(sport_name)) {
                            view.sportFailed();
                        } else {
                            Sport sport = new Sport(0,sport_name);
                            sportDAO.add(sport);
                            view.successSport();
                            exit_sportForm = true;
                        }
                    }
                    break;
                case 2:
                    boolean exit_athleteForm = false;
                    boolean checkName = false;
                    while(!exit_athleteForm){
                        String name = "";
                        while(!checkName) {
                            view.AthleteForm();
                            name = input.next();
                            if (athleteDAO.checkAthlete(name)) {
                                view.athleteFailed();
                            } else {
                                checkName = true;
                            }
                        }
                        while(true){
                            view.listSports();
                            List<Sport> sports = sportDAO.getAll();
                            view.displaySportList(sports);
                            view.enterSport();
                            int id_sport = input.nextInt();
                            Athlete athlete = new Athlete(name, id_sport);
                            athleteDAO.add(athlete);
                            view.successAthlete();
                            exit_athleteForm = true;
                            break;
                        }
                    }
                    break;
                case 3:
                    boolean exit_searchAthlete = false;
                    while(!exit_searchAthlete){
                        view.displaySearchAthlete();
                        String athlete_name = input.next();
                        Athlete athlete = athleteDAO.getAthlete(athlete_name);
                        if (athlete == null){
                            view.athleteNotFound();
                        } else {
                            athlete_name = athlete.getName();
                            String sportName = sportDAO.getSportName(athlete.getSport());
                            view.dislayAthlete(athlete_name, sportName);
                            exit_searchAthlete = true;
                        }
                    }
                    break;
                case 4:
                    List <Sport> sports = sportDAO.getAll();
                    view.displaySportList(sports);
                    view.enterSport();
                    int id_sport = input.nextInt();
                    List <Athlete> athletes = athleteDAO.getAthletesBySport(id_sport);
                    view.dislayAthleteBySport(athletes, sportDAO.getSportName(id_sport));
                    break;
                case 5:

                    List <Sport> allSports = sportDAO.getAll();
                    view.listSports();
                    view.displaySportList(allSports);
                    break;
                case 6:
                    List <Athlete> allAthletes = athleteDAO.getAll();
                    for (Athlete a : allAthletes){
                        String sportName = sportDAO.getSportName(a.getSport());
                        view.dislayAthlete(a.getName(), sportName);
                    }
                    break;
                case 7:
                    exit_program = true;
                    break;
                default:
                    view.inValidOption();
            }
        }
    }
}
