package models;

public class Sport {
    private String name;
    private int id;

    public Sport(int id,String name){

        this.name = name;
        this.id = id;
    }

    //getters and setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString(){
        return "Id: " + id + ". " + name + "\n";
    }
}
