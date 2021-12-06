package pageturners.models;

import java.util.Date;

public class UserCommentObject {
    
    public int commentId; //Comments ID
    public String comment; //The users comment
    public int userId; //The user that posted the comment
    public Date commentCreated; //When the user created the comment
}
