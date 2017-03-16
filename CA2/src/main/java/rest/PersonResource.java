package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Person;
import entity.Phone;
import facade.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import jsonMappers.PersonContact;
import jsonMappers.PersonJson;

/**
 * REST Web Service
 *
 * @author Joacim
 */
@Path("Person")
public class PersonResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() {
        List<Person> persons = new PersonFacade("PU").getPersons();
        List<PersonJson> personsJson = new ArrayList();
        for (Person person : persons) {
            personsJson.add(new PersonJson(person));
        }
        return gson.toJson(personsJson);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") int id) {
        return gson.toJson(new PersonJson(new PersonFacade("PU").getPersonById(id)));
    }

    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersonsContactInfo() {
        List<Person> persons = new PersonFacade("PU").getPersons();
        List<PersonContact> personsJson = new ArrayList();
        for (Person person : persons) {
            personsJson.add(new PersonContact(person));
        }
        return gson.toJson(personsJson);
    }
    
    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactInfoId(@PathParam("id")int id) {        
        return gson.toJson(new PersonContact(new PersonFacade("PU").getPersonById(id)));
    }

    /**
     * PUT method for updating or creating an instance of PersonResouce
     *
     * @param content representation for the resource
     * @return json
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postData(String content) {
        Person person = gson.fromJson(content, Person.class);
        for (Phone phone : person.getPhones()) {
            phone.setInfoEntity(person);
        }
        new PersonFacade("PU").createPerson(person);
        return gson.toJson(person);
    }

}
