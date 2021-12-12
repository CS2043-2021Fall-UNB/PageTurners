package pageturners.models;

import java.util.Date;

public class UserCommentObject {
    
    public int id; //Comments ID
    public int userId; //The user that posted the comment
    public String content; //The users comment contents
    public Date commentDate; //When the user created the comment
}
