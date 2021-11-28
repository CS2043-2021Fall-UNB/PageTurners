package pageturners.models;
import java.util.Date;

public class UserObject {
    private String username;
    private String password;
    private int idNum;
    private Date accountCreated;
    private boolean isMod;
    private boolean isMuted;

    UserObject(String username, String password, int idNum){
      this.username = username;
      this.password = password;
      this.idNum = idNum;
      accountCreated = new Date();
      isMod = false;
      isMuted = false;
    }

    public String getUsername(){
      return username;
    }

    public String getPassword(){
      return password;
    }

    public int getIdNum(){
      return idNum;
    }

    public Date getAccountCreated(){
      return accountCreated;
    }

    public boolean getMuteStatus(){
      return isMuted;
    }

    public boolean changeMuteStatus(){
      if(isMuted){
        isMuted = false;
      }
      else{
        isMuted = true;
      }

      return isMuted;
    }

    public boolean getModStatus(){
      return isMod;
    }

    public boolean changeModStatus(){
      if(isMod){
        isMod = false;
      }
      else{
        isMod = true;
      }

      return isMod;
    }
}
