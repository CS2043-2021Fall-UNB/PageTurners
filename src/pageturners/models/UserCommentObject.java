package pageturners.models;

import java.util.Date;

public class UserCommentObject {
    
    public int id; //Comments ID
    public int postId;
    public int userId; //The user that posted the comment
    public String contents; //The users comment contents
    public boolean isDeleted;
    public Date commentDate; //When the user created the comment

    public UserObject user;
}
