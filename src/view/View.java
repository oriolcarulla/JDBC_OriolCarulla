package view;
import models.Athlete;
import models.Sport;

import java.util.List;
import java.util.Scanner;

public class View {
    private String Title_start_menu = "Welcome to the Sports Management System";
    private String[] start_menu = {"1. Add a Sport", "2. Add an athlete", "3. Search athlete by name", "4. List athletes by sport","5. List all Sports","6. List all Athletes" ,"7. Exit"};
    private String input_prompt = "Please enter your choice: ";
    public void displayMenu(String title, String[] menu){
        System.out.println("===================================");
        System.out.println(title);
        System.out.println("===================================");
        for (String m : menu) {
            System.out.println(m);
        }
        System.out.println("===================================");
    }

    public void displayStartMenu() {
        displayMenu(Title_start_menu, start_menu);
        System.out.print(input_prompt);
    }

    public void SportForm(){
        System.out.println("===================================");
        System.out.println("Add a Sport");
        System.out.println("===================================");
        System.out.print("Enter the name of the sport: ");
    }
    public void listSports(){
        System.out.println("===================================");
        System.out.println("List of sports");
        System.out.println("===================================");
    }
    public void displaySportList(List <Sport> sports){
        for (Sport s : sports){
            System.out.print(s);
        }
    }
    public void successSport(){
        System.out.println("Sport added successfully");
    }
    public void sportFailed(){
        System.out.println("Sport already exists");
    }
    public void sportNotFound(){
        System.out.println("Sport not found");
    }

    public void AthleteForm(){
        System.out.println("===================================");
        System.out.println("Add an athlete");
        System.out.println("===================================");
        System.out.println("Enter the name of the athlete: ");
    }
    public void athleteFailed(){
        System.out.println("Athlete already exists");
    }
    public void enterSport(){
        System.out.println("Enter the id of the sport: ");
    }
    public void successAthlete(){
        System.out.println("Athlete added successfully");
    }

    public void displaySearchAthlete(){
        System.out.println("===================================");
        System.out.println("Search athlete by name");
        System.out.println("===================================");
        System.out.print("Enter the name of the athlete: ");
    }
    public void dislayAthlete(String athleteName, String sportName) {
        System.out.println("Athlete Name: " + athleteName);
        System.out.println("Sport: " + sportName);
    }
    public void dislayAthleteBySport(List <Athlete> athletes , String sportName){
        System.out.println("=========== List for " + sportName + " =============");
        for (Athlete a : athletes){
            System.out.println("-" + a);
        }
        System.out.println("===================================");
    }
    public void athleteNotFound(){
        System.out.println("Athlete not found");
    }

    public void displayAthleteList(String athleteName, String sportName){
        System.out.println("===================================");
        System.out.println("Athlete Name: " + athleteName);
        System.out.println("Sport: " + sportName);
        System.out.println("===================================");
    }

    public void inValidOption(){
        System.out.println("Invalid option, please try again");
    }



}
