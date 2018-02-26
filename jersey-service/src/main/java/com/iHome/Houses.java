/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iHome;


import javax.json.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kskoul
 */
public class Houses {

   private   DB db;

    public Houses() {
        db = new DB();
    }

    public List<House> searchBar(String location) throws Exception {
        Connection con = null;
        String sqlquery = "SELECT location FROM house WHERE location LIKE ?";
        List<House> houses = new ArrayList<House>();
        try {
            db.open();
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlquery);
            stmt.setString(1, "%" + location + "%" );
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                houses.add(new House(rs.getString("location")));
            }

            rs.close(); //closing ResultSet
            stmt.close(); // closing PreparedStatement
            db.close(); // closing connection

            return houses;

        } catch (Exception e) {

            throw new Exception(e.getMessage());

        } finally {
            try {
                db.close();
            } catch (Exception e) {
                //no need to do anything...
            }
        }
    }

    public JsonArray getAllHousesFromSearch(String location, int number_of_people, String checkIn, String checkOut) throws Exception {

        Connection con = null;
        //AND availabilty between checkIn and checkOut AND number_of_people > ?
        String sqlquery = "SELECT * FROM house WHERE location = ? AND number_of_people >= ? AND from_date <= ? AND to_date >= ? AND availability = 1";

        DB db = new DB();
        List<House> houses = new ArrayList<House>();

        JsonArrayBuilder object = Json.createArrayBuilder();
        try {
            db.open();

            con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement(sqlquery);
            stmt.setString(1, location);
            stmt.setInt(2, number_of_people);
            stmt.setString(3, checkIn);
            stmt.setString(4, checkOut);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                object.add(Json.createObjectBuilder()
                        .add("idhouse", rs.getInt("idHouse"))
                        .add("adress", rs.getString("adress"))
                        .add("telephone", rs.getString("telephone"))
                        .add("location", rs.getString("location"))
                        .add("square_feet", rs.getString("square_feet"))
                        .add("availability", rs.getInt("availability"))
                        .add("number_of_people", rs.getInt("number_of_people"))
                        .add("price_per_day", rs.getString("price_per_day"))
                        .add("payment_credit", rs.getInt("payment_credit"))
                        .add("payment_cash", rs.getInt("payment_cash"))
                        .add("county", rs.getString("county"))
                        .add("iduser", rs.getInt("iduser"))
                        .add("total_rating", rs.getFloat("total_rating"))
                        .add("from_date",  rs.getDate("from_date").toString())
                        .add("to_date", rs.getDate("to_date").toString())
                        .add("house_cat", rs.getString("house_cat"))
                        .add("house_pers_desc", rs.getString("house_pers_desc"))

                );
//                houses.add(new House(
//                        rs.getInt("idHouse"), rs.getString("adress"), rs.getString("telephone"),
//                        rs.getString("location"), rs.getString("square_feet"),
//                        rs.getInt("availability"), rs.getInt("number_of_people"), rs.getString("price_per_day"),
//                        rs.getInt("payment_credit"), rs.getInt("payment_cash"), rs.getString("county"), rs.getInt("owner"),
//                        rs.getFloat("total_rating"), rs.getDate("from_date"), rs.getDate("to_date")
//                ));
            }

            rs.close(); //closing ResultSet
            stmt.close(); // closing PreparedStatement
            db.close(); // closing connection

            JsonArray jsonObject = object.build();

            return jsonObject;
                    
        } catch (Exception e) {

            throw new Exception(e.getMessage());

        } finally {
            try {
                db.close();
            } catch (Exception e) {
                //no need to do anything...
            }
        }

    }

    public JsonObject getHouse(int idhouse) throws Exception {
        Connection con = null;
        String sqlquery = "SELECT * FROM house WHERE idhouse = ?";
        String sqlquery1 = "SELECT * FROM evaluation WHERE idhouse = ?";
        JsonObjectBuilder object = Json.createObjectBuilder();
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        try {
            db.open();
            con = db.getConnection();

            PreparedStatement stmt1 = con.prepareStatement(sqlquery1);

            stmt1.setInt(1, idhouse);
            ResultSet rs1 = stmt1.executeQuery();

            while (rs1.next()) {
                jsonArray.add(Json.createObjectBuilder()
                        .add("idevaluation", rs1.getInt("idevaluation"))
                        .add("date", rs1.getDate("date").toString())
                        .add("rating", rs1.getFloat("rating"))
                        .add("comment", rs1.getString("comment"))
                        .add("idhouse", rs1.getInt("idHouse"))
                        .add("iduser", rs1.getInt("iduser"))
                );
            }
//            System.out.println(jsonArray.build());

            rs1.close();
            stmt1.close();

            PreparedStatement stmt = con.prepareStatement(sqlquery);
            stmt.setInt(1,  idhouse);
            ResultSet rs = stmt.executeQuery();
//            System.out.println(jsonArray.build());
            while (rs.next()) {
                object.add("idhouse", rs.getInt("idHouse"))
                        .add("adress", rs.getString("adress"))
                        .add("telephone", rs.getString("telephone"))
                        .add("location", rs.getString("location"))
                        .add("square_feet", rs.getString("square_feet"))
                        .add("availability", rs.getInt("availability"))
                        .add("number_of_people", rs.getInt("number_of_people"))
                        .add("price_per_day", rs.getString("price_per_day"))
                        .add("payment_credit", rs.getInt("payment_credit"))
                        .add("payment_cash", rs.getInt("payment_cash"))
                        .add("county", rs.getString("county"))
                        .add("owner", rs.getInt("owner"))
                        .add("total_rating", rs.getFloat("total_rating"))
                        .add("from_date",  rs.getDate("from_date").toString())
                        .add("to_date", rs.getDate("to_date").toString())
                        .add("house_cat", rs.getString("house_cat"))
                        .add("house_pers_desc", rs.getString("house_pers_desc"))
                        .add("evaluation", jsonArray.build());



            }

            rs.close(); //closing ResultSet
            stmt.close(); // closing PreparedStatement.

            db.close(); // closing connection

            return object.build();

        } catch (Exception e) {

            throw new Exception(e.getMessage());

        } finally {
            try {
                db.close();
            } catch (Exception e) {
                //no need to do anything...
            }
        }
    }
    ////                            object.add("evaluation", Json.createArrayBuilder()
////                                    .add(Json.createObjectBuilder()
////                                            .add("idevaluation", rs.getInt("idevaluation"))
////                                            .add("date", rs.getDate("date").toString())
////                                            .add("rating", rs.getFloat("rating"))
////                                            .add("comment", rs.getString("comment"))
////                                            .add("idhouse", rs.getInt("idHouse"))
////                                            .add("iduser", rs.getInt("iduser"))
////                                    )
////                            );
   
}
