package Restaurants;

import org.sql2o.Sql2o;

import java.sql.Connection;

public class DB {

    public static Connection sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa", "Access");
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-34-195-169-25.compute-1.amazonaws.com:5432/ddl6ruj0effp39", "muenbetcegiygw", "dd9519a5f4409714b6ca6ae4c2fd38d7e04e3f3b9e7520a43405428ede638fb1");
}
