package me.dm7.barcodescanner.zxing.bookstock.books.fields;

/**
 * User Variables
 */


public class UserFields {

    private String userName;
    private String password;

    public void setUserName(String name){
        this.userName = name;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setPassword(String userPassword){
        this.password = userPassword;
    }
    public String getPassword(){
        return this.password;
    }
}
