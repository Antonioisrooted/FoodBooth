package Restaurants;

import org.sql2o.Connection;
import java.util.List;
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
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name,type,description,cook) VALUES (:name,:type,:description,:cook);";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .addParameter("description",this.description)
                    .addParameter("cook",this.cook)
                    .executeUpdate()
                    .getKey();
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash( name, id, type);
    }

    public static List<PostRecipe> all() {
        String sql = "SELECT * FROM animals WHERE type='Not Post'";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(PostRecipe.class);
        }
    }

    public static PostRecipe find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            PostRecipe animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(PostRecipe.class);
            return animal;
        }
    }
}