package book.fields;

/**
 * User Variables
 */


public class UserFields {

    private String username;
    private String password;
    private String token;

    public  UserFields(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public void setUserName(String name){
        this.username = name;
    }
    public String getUserName(){
        return this.username;
    }

    public String getToken() {
        return this.token;
    }
    public void setPassword(String userPassword){
        this.password = userPassword;
    }
    public String getPassword(){
        return this.password;
    }
}
