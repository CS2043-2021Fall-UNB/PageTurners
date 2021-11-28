package pageturners.models;

public class UserCommentObject {
    
    public int id; //Comments ID
    public String comment; //The users comment
    public UserObject user; //The user that posted the comment
    public Date commentCreated; //When the user created the comment
}
