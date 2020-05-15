package Restaurants;

import java.sql.Connection;
import java.sql.SQLException;

//import org.sql2o.Connection;

public abstract class Animal {


    public String name;
    public int id;
    public String type;
    public String cook;
    protected String description;

    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public String getCook(){
        return cook;
    }
    public String getDescription(){ return description; }



    public void getOdder() {
        try ( Connection con = (Connection) DB.sql2o.createStatement() ) {
            String sql = "SELECT * FROM odder WHERE animalid = :id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Odder.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
