/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Person;
import facade.CompanyFacade;
import facade.PersonFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Joacim
 */
@Path("Person")
public class PersonResource {

    Gson gson = new Gson();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() {
        List<Person> persons = new PersonFacade("PU").getPersons();
        JsonArray names = new JsonArray();
        for (int i = 0; i < persons.size(); i++) {        
            JsonObject person = new JsonObject();
                person.addProperty("fName", persons.get(i).getFirstName());
                person.addProperty("lName", persons.get(i).getLastName());
                person.addProperty("street", persons.get(i).getHobbies().get(0).toString());
//                person.addProperty("city", city[ran.nextInt(50)]);
            names.add(person);
        }
        return gson.toJson(names);
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
