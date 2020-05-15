package Restaurants;

//import org.sql2o.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Odder {
    private static final DB DB = null;
    private int id;
    private int animalId;
    private String sightLocation;
    private String rangerName;
    private Timestamp viewed;
    private Object con;

    public Odder(int animalId, String sightLocation, String rangerName){
        this.animalId = animalId;
        this.sightLocation = sightLocation;
        this.rangerName = rangerName;
    }

    public int getAnimalId(){
        return animalId;
    }
    public String getSightLocation(){
        return sightLocation;
    }
    public String getRangerName(){
        return rangerName;
    }

    public int getId() {
        return id;
    }
    public Timestamp getViewed(){

        return viewed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Odder sightings = (Odder) o;
        return id == sightings.id &&
                this.animalId == sightings.animalId &&
                this.sightLocation.equals(sightings.sightLocation) &&
                this.rangerName.equals(sightings.rangerName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animalId, sightLocation, rangerName );
    }

    public void save(){
        try(Connection con = DB.sql2o;) {
        } catch (SQLException e) {
            e.printStackTrace();
        }{
            String sql = "INSERT INTO sightings (animalid, sightlocation, rangername) VALUES (:animalId, :sightLocation, :rangerName)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("animalId",this.animalId)
                    .addParameter("sightLocation",this.sightLocation)
                    .addParameter("rangerName",this.rangerName)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Odder> all(){
        String sql = "SELECT * FROM sightings";
        try(Connection con = (Connection) DB.sql2o.createStatement() ){
            return con.createQuery(sql)
                    .executeAndFetch(Odder.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Odder find(int id){
        try(Connection con = (Connection) DB.sql2o.createStatement() ) {
            String sql = "SELECT * FROM sightings WHERE id = :id";
            Odder sighting = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Odder.class);
            return sighting;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}