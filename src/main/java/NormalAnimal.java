import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class NormalAnimal extends Animal {
    public static final String DATABASE_TYPE = "Current Order";

    public NormalAnimal(String name) {
        this.name = name;
        type = DATABASE_TYPE;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalAnimal animal = (NormalAnimal ) o;
        return id == animal.id &&
                name.equals(animal.name) &&
                type.equals(animal.type);
    }
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name,type) VALUES (:name,:type);";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .executeUpdate()
                    .getKey();
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash( name, id, type);
    }

    public static List<NormalAnimal> all() {
        String sql = "SELECT * FROM animals WHERE type='Current Order'";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(NormalAnimal.class);
        }
    }

    public static NormalAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            NormalAnimal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(NormalAnimal.class);
            return animal;
        }
    }
}
