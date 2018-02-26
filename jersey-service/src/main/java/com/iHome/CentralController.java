/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iHome;

import org.json.JSONObject;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/*import com.g.DB;*/
/**
 *
 * @author kskoul
 */
@Path("iHome")
public class CentralController {

    Houses houses = new Houses();
    User user = new User();

    SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
    final String OLD_FORMAT = "dd/MM/yyyy";
    final String NEW_FORMAT = "yyyy-MM-dd";



            @Path("/house/{id}")
            @GET
            @Produces("application/json")
            @Consumes("application/json")
            public JsonObject oneHouseInformation(@PathParam("id") int idhouse) throws Exception {
                try {
                    return houses.getHouse(idhouse);
                } catch (Exception e) {

                    throw new Exception(e.getMessage());

                } finally {
                    try {
                        System.out.println("asd");
                    } catch (Exception e) {
                        //no need to do anything...
                    }
                }
            }





    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public JsonArray getAllHousesAfterSearch(JsonObject inputJsonObj) throws Exception {

        try {
            if (!inputJsonObj.isEmpty()) {
                System.out.println(inputJsonObj);
                System.out.println("//////////////asdasdasdasdasdasdasdasd asdasd");
                JSONObject obj = new JSONObject(inputJsonObj.getString("body"));
                String location = (String) obj.getString("location");
                String checkInDate = obj.getString("checkIn");
                String checkOutDate = obj.getString("checkOut");
                Date checkInD = fromUser.parse(checkInDate);
                Date checkOutD = fromUser.parse(checkOutDate);
                fromUser.applyPattern(NEW_FORMAT);
                String checkIn = fromUser.format(checkInD);
                String checkOut = fromUser.format(checkOutD);
                Integer number_of_people = obj.getInt("number_of_people");
                System.out.println(location);
                System.out.println("CHECK IN: " + checkIn);
                System.out.println("CHECK OUT: " + checkOut);
                System.out.println(number_of_people);
                return houses.getAllHousesFromSearch(location, number_of_people, checkIn, checkOut);
            } else {
                throw new Exception("No Data Received");
            }


        } catch (Exception e) {

            throw new Exception(e.getMessage());

        } finally {
            try {
                System.out.println("asd");
            } catch (Exception e) {
                //no need to do anything...
            }
        }
    }

        @Path("/signup")
        @POST
        public boolean enrollUser(JsonObject inputJsonObj) throws Exception {
            System.out.println("EISAI KIOFTES" + inputJsonObj);
            JSONObject obj = new JSONObject(inputJsonObj.getString("body"));
            String email = obj.getString("email");
            String password = obj.getString("password");
            String firstName = obj.getString("firstName");
            String lastName = obj.getString("lastName");
            String currentadress = obj.getString("currentAdress");
            try {

                return user.enrollUser(firstName, lastName, currentadress, email, password);
            } catch (Exception e) {

                throw new Exception(e.getMessage());

            }
        }



        @Path("/login")
        @POST
        @Produces("application/json")
        @Consumes("application/json")
        public JsonObject login(JsonObject inputJsonObj) throws Exception {
            System.out.println("EISAI KIOFTES" + inputJsonObj);
            JSONObject obj = new JSONObject(inputJsonObj.getString("body"));
            String email = obj.getString("email");
            String password = obj.getString("password");
            System.out.println("this is" + email + "This is" + password);
            try {

                return user.checkUser(email,password);
            } catch (Exception e) {

                throw new Exception("Some error occured");

            }
        }






}
