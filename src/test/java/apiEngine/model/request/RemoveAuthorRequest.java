package apiEngine.model.request;

public class RemoveAuthorRequest {
    public int authorId;
    public boolean forcibly;

    public RemoveAuthorRequest(int authorId, boolean forcibly) {
        this.authorId = authorId;
        this.forcibly = forcibly;
    }
}
