package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import entity.Person;
import entity.Phone;
import exception.PersonNotFoundException;
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

@Path("Person")
public class PersonResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    PersonFacade personFacade = new PersonFacade("PU");

    @Context
    private UriInfo context;

    public PersonResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() throws PersonNotFoundException {
        try {
            List<Person> persons = personFacade.getPersons();
            List<PersonJson> personsJson = new ArrayList();
            for (Person person : persons) {
                personsJson.add(new PersonJson(person));
            }
            return gson.toJson(personsJson);
        } catch (Exception e) {
            throw new PersonNotFoundException("No persons exist");
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") int id) throws PersonNotFoundException {
        try {
            return gson.toJson(new PersonJson(personFacade.getPersonById(id)));
        } catch (Exception e) {
            throw new PersonNotFoundException("Person with requested id not found");
        }
    }

    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersonsContactInfo() throws PersonNotFoundException {
        try {
            List<Person> persons = personFacade.getPersons();
            List<PersonContact> personsJson = new ArrayList();
            for (Person person : persons) {
                personsJson.add(new PersonContact(person));
            }
            return gson.toJson(personsJson);
        } catch (Exception e) {
            throw new PersonNotFoundException("No persons exist");
        }
    }

    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactInfoId(@PathParam("id") int id) throws PersonNotFoundException {
        try {
            return gson.toJson(new PersonContact(personFacade.getPersonById(id)));
        } catch (Exception e) {
            throw new PersonNotFoundException("Person with requested id not found");
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id) throws PersonNotFoundException {
        try {
            personFacade.deletePerson(id);
            return "{\"isSucced\" : \"Deleted\"}";
        } catch (Exception e) {
            throw new PersonNotFoundException("Person with requested id could not be deleted");
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePerson(String content) throws PersonNotFoundException {
        try {
            Person person = gson.fromJson(content, Person.class);
            System.out.println(person);
            for (Phone phone : person.getPhones()) {
                phone.setInfoEntity(person);
            }
            personFacade.updatePerson(person);
            return "{\"isSucced\" : \"Updated\"}";
        } catch (JsonSyntaxException e) {
            throw new PersonNotFoundException("Person could not be updated");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postData(String content) throws PersonNotFoundException {
        try {
            Person person = gson.fromJson(content, Person.class);
            for (Phone phone : person.getPhones()) {
                phone.setInfoEntity(person);
            }
            personFacade.createPerson(person);
            return gson.toJson(person);
        } catch (JsonSyntaxException e) {
            throw new PersonNotFoundException("Person could not be added");
        }
    }

}
