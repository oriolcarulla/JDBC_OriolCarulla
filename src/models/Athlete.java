package models;

import DAO.SportDAO;

public class Athlete {
    private String name;
    private int sport;

    public Athlete(String name, int sport){
        this.name = name;
        this.sport = sport;
    }

    //getters and setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getSport(){
        return sport;
    }
    public void setSport(int sport){
        this.sport = sport;
    }

    @Override
    public String toString(){
        return name;
    }
}
