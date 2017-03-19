package rest;

import entity.Person;
import facade.PersonFacade;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class RestIntegrationTest {

    public RestIntegrationTest() {
    }

    @BeforeClass
    public static void setUpBeforeAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8084;
        RestAssured.basePath = "/CA2";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void serverIsRunning() {
        given().when().get().then().statusCode(200);
    }

    @Test
    public void getPersonByID() {
        given().pathParam("id", 13).when().get("api/Person/{id}").then().statusCode(200).body("id", equalTo(13));
    }

    @Test
    public void getPersonsContactInfo() {
        given().when().get("api/Person/contactinfo").then().statusCode(200);
    }

    @Test
    public void getPersonContactInfoById() {
        given().pathParam("id", 13).when().get("api/Person/contactinfo/{id}").then().statusCode(200).body("id", equalTo(13));
    }

    @Test
    public void deletePersonById() {
        PersonFacade pf = new PersonFacade("PU");
        Person p = pf.createPerson(new Person("Bob", "Bobby", null, "bob@gmail.com", null, null));
        given().pathParam("id", p.getId()).when().delete("api/Person/{id}").then().statusCode(200).body("isSucced", equalTo("Deleted"));
    }

    @Test
    public void updatePersonById() {
        String person = "{\n"
                + "  \"id\": 13,\n"
                + "  \"firstName\": \"Laurette\",\n"
                + "  \"lastName\": \"Dougherty\",\n"
                + "  \"email\": \"ccohen123@icloud.com\",\n"
                + "  \"address\": {\n"
                + "    \"street\": \" 8088 Clark Court 22\",\n"
                + "    \"additionalInfo\": \"desc\"\n"
                + "  },\n"
                + "  \"hobbies\": [\n"
                + "    {\n"
                + "      \"description\": \"run around\",\n"
                + "      \"name\": \"football\"\n"
                + "    }\n"
                + "  ],\n"
                + "  \"phones\": [\n"
                + "    {\n"
                + "      \"description\": \"Mobile\",\n"
                + "      \"number\": \"16345723\"\n"
                + "    }\n"
                + "  ],\n"
                + "  \"cityInfo\": {\n"
                + "    \"city\": \"København K\",\n"
                + "    \"zip\": 1402\n"
                + "  }\n"
                + "}";
        given().body(person).when().put("api/Person/").then().statusCode(200).body("isSucced", equalTo("Updated"));
    }

}
