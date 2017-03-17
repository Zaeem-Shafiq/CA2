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
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    PersonFacade personFacade = new PersonFacade("PU");

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
        List<Person> persons = personFacade.getPersons();
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
        return gson.toJson(new PersonJson(personFacade.getPersonById(id)));
    }

    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersonsContactInfo() {
        List<Person> persons = personFacade.getPersons();
        List<PersonContact> personsJson = new ArrayList();
        for (Person person : persons) {
            personsJson.add(new PersonContact(person));
        }
        return gson.toJson(personsJson);
    }

    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactInfoId(@PathParam("id") int id) {
        return gson.toJson(new PersonContact(personFacade.getPersonById(id)));
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id) {
        personFacade.deletePerson(id);
        return gson.toJson("Succes");
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePerson(String content){
        Person person = gson.fromJson(content, Person.class);
        personFacade.updatePerson(person);
        return gson.toJson("person updated");
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
        personFacade.createPerson(person);
        return gson.toJson(person);
    }

}
