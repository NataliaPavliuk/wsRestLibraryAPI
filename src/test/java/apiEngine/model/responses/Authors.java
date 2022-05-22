package apiEngine.model.responses;

import apiEngine.model.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Authors {
    public static List<Author> authors;
}
