
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class restIntegrationTest {

    public restIntegrationTest() {
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
        given().pathParam("id", 9).when().get("api/Person/{id}").then().statusCode(200).body("id", equalTo(9));
    }
    
    @Test
    public void getPersonsContactInfo() {
        given().when().get("api/Person/contactinfo").then().statusCode(200);
    }
    
    @Test
    public void getPersonContactInfoById() {
        given().pathParam("id", 9).when().get("api/Person/contactinfo/{id}").then().statusCode(200).body("id", equalTo(9));
    }
    
    @Test
    public void deletePersonById() {
        given().pathParam("id", 67).when().delete("api/Person/{id}").then().statusCode(200).body(equalTo("Succes"));
    }

}
