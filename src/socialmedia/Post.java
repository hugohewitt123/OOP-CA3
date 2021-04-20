package socialmedia;

public class Post{
    //attributes
    private String message;

    private int postId;

    private String accountHandle;

    private int commentID;

    private int noEndorsements;

    private int noComments;

    //methods
    public void setMessage(String m) {
        if (m.length() < 100) {
            this.message = m;
        }
    }

    public void setPostId(int id) {
        this.postId = id;
    }

    public void setHandle(String h) {
        this.accountHandle = h;
    }

    public void setCommentID(int id) {
        this.commentID = id;
    }

    public void addEndorsement() {
        this.noEndorsements += 1;
    }

    public void addComment() { this.noComments += 1; }

    public String getHandle() {
        return this.accountHandle;
    }

    public String getMessage() {
        return this.message;
    }

    public int getId() {
        return this.postId;
    }
    //Constructor
    public Post(int id, String m, String h) {
        setPostId(id);
        setMessage(m);
        setHandle(h);
    }

    public Post(String m, String h, int id) {
        setCommentID(id);
        setMessage(m);
        setHandle(h);
    }

}
