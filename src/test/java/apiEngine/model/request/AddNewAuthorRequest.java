package apiEngine.model.request;

import apiEngine.model.AuthorName;
import apiEngine.model.Birth;

public class AddNewAuthorRequest {

    public int authorId;
    public AuthorName authorName;
    public String nationality;
    public Birth birth;
    public String authorDescription;

    public AddNewAuthorRequest(int authorId, String first, String second, String nationality, String date, String country, String city, String authorDescription) {
        this.authorId = authorId;
        this.authorName = new AuthorName(first, second);
        this.nationality = nationality;
        this.birth = new Birth(date, country, city);
        this.authorDescription = authorDescription;
    }
}
