package com.iHome;

import javax.json.*;
import javax.ws.rs.*;
import java.util.List;

@Path("/search/{location}")
public class Search {

    Houses houses = new Houses();


    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public JsonArray searchLocResults(@PathParam("location") String location) throws Exception {
        System.out.println("///////////////////////////////////");
        System.out.println("auto deployment KOSTASSKOUL THE ONLY 7");
        List<House> listHouses = houses.searchBar(location);

        System.out.println("Empty: " + listHouses.isEmpty());

        JsonArrayBuilder object = Json.createArrayBuilder();

        for (House obj: listHouses) {
            System.out.println(obj.getLocation());
            object.add(Json.createObjectBuilder().add("Location", obj.getLocation()));
        }

        JsonArray jsonObject = object.build();
        return jsonObject;
    }
}