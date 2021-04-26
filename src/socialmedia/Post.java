package socialmedia;

import java.util.ArrayList;

/**
* A class that will hold all the post objects
**/
public class Post{
    //attributes
    private String message;

    private int postId;

    private String accountHandle;

    private int noEndorsements;

    private int noComments;
    
    private boolean isComment;
    
    private ArrayList<Integer> commentIds = new ArrayList<Integer>();

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
    
    public void setisComment(boolean c){
        this.isComment = c;
    }
    
    public void addCommentId(int id) {
        this.commentIds.add(id);
    }
    
    public void removeEndorsements() {
        this.noEndorsements = 0;
    }

    public void addEndorsement() {
        this.noEndorsements += 1;
    }

    public void addComment() { 
        this.noComments += 1; 
    }

    public String getHandle() {
        return this.accountHandle;
    }

    public String getMessage() {
        return this.message;
    }
    
    public int getNoEndorsements(){
        return this.noEndorsements;
    }
    
    public int getNoComments(){
        return this.noComments;
    }
    
    public boolean getIsComment(){
        return this.isComment;
    }
    
    public ArrayList<Integer> getCommentIds() {
        return this.commentIds;
    }

    public int getId() {
        return this.postId;
    }
    
    //Constructors
    public Post(int id, String m, String h) {
        setPostId(id);
        setMessage(m);
        setHandle(h);
        setisComment(false);
    }
    
    public Post(int id_, String m_, String h_, String c) {
        setPostId(id_);
        setMessage(m_);
        setHandle(h_);
        setisComment(true);
    }
    
    public Post(String m){
        setMessage(m);
    }
}
