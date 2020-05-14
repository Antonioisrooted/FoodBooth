import Restaurants.Odder;
import org.sql2o.Connection;

import java.util.List;

public abstract class Animal {


    public String name;
    public int id;
    public String type;
    public String cook;
    public String descrition;

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
    public String getDescrition(){ return descrition; }



    public List<Odder> getOdder() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM odder WHERE animalid = :id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Odder.class);
        }
    }
}
