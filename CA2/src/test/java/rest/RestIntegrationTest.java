package rest;

import com.google.gson.Gson;
import entity.Person;
import entity.Phone;
import facade.PersonFacade;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import jsonMappers.PersonContact;
import jsonMappers.PersonJson;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
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
        Gson gson = new Gson();
        String content = "{\n"
                + "  \"id\": 5,\n"
                + "  \"firstName\": \"Jesper\",\n"
                + "  \"lastName\": \"Callahan\",\n"
                + "  \"email\": \"dleconte@verizon.net\",\n"
                + "  \"address\": {\n"
                + "    \"street\": \" 9621 Briarwood St. 120\",\n"
                + "    \"additionalInfo\": \"desc\",\n"
                + "    \"cityInfo\": {\n"
                + "    \"city\": \"København K\",\n"
                + "    \"zip\": 1440\n"
                + "  },\n"
                + "  \"hobbies\": [\n"
                + "    {\n"
                + "      \"description\": \"sow things\",\n"
                + "      \"name\": \"sowing\"\n"
                + "    }\n"
                + "  ],\n"
                + "  \"phones\": [\n"
                + "    {\n"
                + "      \"description\": \"Mobile\",\n"
                + "      \"number\": \"13036074\"\n"
                + "    }\n"
                + "  ]\n"
                + "  \n"
                + "  }\n"
                + "}";
            
        given().body(content).when().put("api/Person/").then().statusCode(200).body("isSucced", equalTo("Updated"));
    }

}
