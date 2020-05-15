package Restaurants;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;


public class PostRecipe extends Animal {
    public static final String DATABASE_TYPE = "Not Post";

    public PostRecipe(String name, String cook, String description) {
        this.name = name;
        this.cook = cook;
        this.description = description;
        type = DATABASE_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostRecipe animal = (PostRecipe ) o;
        return id == animal.id &&
                name.equals(animal.name) &&
                type.equals(animal.type);
    }
    public void save(){
        try( Connection con = (Connection) DB.sql2o.createStatement() ){
            String sql = "INSERT INTO animals (name,type,description,cook) VALUES (:name,:type,:description,:cook);";
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
    @Override
    public int hashCode() {
        return Objects.hash( name, id, type);
    }

    public static void all() {
        String sql = "SELECT * FROM animals WHERE type='Not Post'";
        try (Connection con = (Connection) DB.sql2o.createStatement() ) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(PostRecipe.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PostRecipe find(int id) {
        try (Connection con = (Connection) DB.sql2o.createStatement() ) {
            String sql = "SELECT * FROM animals where id=:id";
            PostRecipe animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(PostRecipe.class);
            return animal;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return 0;
    }

    public String getName() {
        return null;
    }

    public String getType() {
        return null;
    }

    public String getCook() {
        return null;
    }

    public String getDescription() {
        return null;
    }
}