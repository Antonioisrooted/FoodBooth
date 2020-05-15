package Restaurants;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class PostMeal extends Animal{
    public static final String DATABASE_TYPE = "Post";

    public PostMeal(String name, String description, String cook){
        this.name = name;
        this.description = description;
        this.cook = cook;
        type = DATABASE_TYPE;
    }

    public String getDescription(){

        return description;
    }
    public String getCook(){
        return cook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostMeal animal = (PostMeal) o;
        return id == animal.id &&
                description.equals(animal.description) &&
                cook.equals(animal.cook) &&
                name.equals(animal.name) &&
                type.equals(animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, cook, name, id, type);
    }

    public static void all() {
        String sql = "SELECT * FROM animals WHERE type='Post'";
        try ( Connection con = (Connection) DB.sql2o.createStatement() ) {
            return con.createQuery(sql)
                    .executeAndFetch(PostMeal.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(){

        try(Connection con = (Connection) DB.sql2o.createStatement() ){
            String sql = "INSERT INTO animals (name,type,health,age) VALUES (:name,:type,:description,:cook);";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .addParameter("description",this.description)
                    .addParameter("cook",this.cook)
                    .executeUpdate()
                    .getKey();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static PostMeal find(int id) {
        try (Connection con = (Connection) DB.sql2o.createStatement() ) {
            String sql = "SELECT * FROM animals where id=:id";
            PostMeal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(PostMeal.class);
            return animal;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        return null;
    }

    public int getId() {
        return 0;
    }
}

