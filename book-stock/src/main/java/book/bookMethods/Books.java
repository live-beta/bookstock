package book.bookMethods;

/**
 * Created by sam on 12/20/17.
 */


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import book.api.APICalls;
import book.fields.BookFields;
import book.fields.UserFields;


public class Books {

    public static String token;
    Collection<List<BookFields>> allBooks = new ArrayList<>();
    List<BookFields> booklists = new ArrayList<>();
    //    ItemFields itemFields = new ItemFields();
    //UserFields userFields = new UserFields();
    APICalls apiCalls = new APICalls();
    private String title, subTitle, authors;
//
//    public Books(String userName, String password) {
//
//        this.userFields.setUserName(userName);
//        this.userFields.setPassword(password);
//    }

//    public boolean login() {
//
//        boolean status = apiCalls.login(userFields.getUserName(), userFields.getPassword());
//        return status;
//    }
}


