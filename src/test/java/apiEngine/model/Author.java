package apiEngine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    public int authorId;
    public AuthorName authorName;
    public String nationality;
    public Birth birth;
    public String authorDescription;
}
