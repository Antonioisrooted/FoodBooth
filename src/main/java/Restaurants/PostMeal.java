package Restaurants;

import org.sql2o.Connection;

import java.util.List;
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

    public static List<PostMeal> all(){
        String sql = "SELECT * FROM animals WHERE type='Post'";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(PostMeal.class);
        }
    }

    public void save(){

        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name,type,health,age) VALUES (:name,:type,:description,:cook);";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .addParameter("description",this.description)
                    .addParameter("cook",this.cook)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static PostMeal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            PostMeal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(PostMeal.class);
            return animal;
        }
    }
}

