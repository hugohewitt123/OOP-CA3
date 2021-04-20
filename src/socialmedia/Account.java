package socialmedia;

public class Account {
    //Attributes
    private int id;
    
    private String handle;

    private String description;

    private int noEndorsements;

    private int noPosts;

    //setters
    public void setHandle(String h) {
        this.handle = h;
    }

    public void setDescription(String d) {
        this.description = d;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void addEndorsement() {
        this.noEndorsements += 1;
    }

    public void addPost() { this.noPosts += 1; }

    //getters
    public String getHandle(){
        return this.handle;
    }
    
    public int getid(){
        return this.id;
    }

    public String getDesc() {
        return this.description;
    }

    public int getNoEndorsements() {
        return this.noEndorsements;
    }

    public int getNoPosts() {
        return this.noPosts;
    }

    //Constructors
    public Account(int id, String h) {
        this.id = id;
        setHandle(h);
    }

    public Account(int id, String h, String d) {
        this.id = id;
        setHandle(h);
        setDescription(d);
    }
}
