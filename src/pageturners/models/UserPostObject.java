package pageturners.models;

import java.util.Date;

public class UserPostObject {
    public int id;
    public int categoryId;
    public String title;
    public String contents;
    public int authorId;
    public Date postDate;
    public boolean isDeleted;

    public UserObject author;
}
