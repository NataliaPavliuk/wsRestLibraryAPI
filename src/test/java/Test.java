import apiEngine.model.Author;
import apiEngine.model.request.AddNewAuthorRequest;
import apiEngine.model.request.RemoveAuthorRequest;
import apiEngine.model.responses.Authors;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class Test {
    private static final String BASE_URL = "http://localhost:8080/";

    private static Response response;
    private static String jsonString;

    public Authors[] getListOfAuthors() throws JsonProcessingException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/api/library/authors");

        jsonString = response.asString();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonString, Authors[].class);
    }

    public void listIsAvaible() throws JsonProcessingException {
        Authors[] authors = getListOfAuthors();
        Assert.assertEquals(10, authors.length);
        Assert.assertEquals(200, response.getStatusCode());
    }

    public void addAuthorInList() {
        AddNewAuthorRequest addNewAuthorRequest = new AddNewAuthorRequest(1029, "Ayn", "Rand", "American", "1905-01-20", "Russian", "Saint-Peterburg", "American writer of Jewish descent");

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.body(addNewAuthorRequest).post("/api/library/author");

    }
    public void authorIsAdded() throws JsonProcessingException {
        Assert.assertEquals(201, response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String json = "{\"authorId\": 1029}";
        Author author = mapper.readValue(json, Author.class);
        Assert.assertEquals(1029, author.authorId);
    }

    public void removeAuthorFromList() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        RemoveAuthorRequest removeAuthorRequest = new RemoveAuthorRequest(1029, false);
        request.header("Content-Type", "application/json");

        response = request.body(removeAuthorRequest).delete("/api/library/author/"+1029);
    }

    public void authorIsRemoved() throws JsonProcessingException {
        Assert.assertEquals(204, response.getStatusCode());

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        response = request.get("/api/library/authors/");
        Assert.assertEquals(200, response.getStatusCode());

        Authors[] authors = getListOfAuthors();
        Assert.assertEquals(10, authors.length);
    }

    public void  authorIsAlreadyExists(){
        Assert.assertEquals(409, response.getStatusCode());
    }

    @org.junit.Test
    public void MainTest() throws JsonProcessingException {
        listIsAvaible();
        addAuthorInList();
        authorIsAdded();
        addAuthorInList();
        authorIsAlreadyExists();
        removeAuthorFromList();
        authorIsRemoved();
    }
}
