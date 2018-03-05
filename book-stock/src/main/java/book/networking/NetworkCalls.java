package book.networking;

/**
 * Created by sam on 3/4/18.
 */

import java.util.ArrayList;

import book.fields.BookAddFields;
import book.fields.BookFields;
import book.fields.UserFields;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface NetworkCalls {

    @POST("auth/login")
    Call<UserFields> LoginCall(@Body UserFields userFields);

    @GET("books?limit=1000")
    Call<ArrayList<BookFields>> getBooks();

    @POST("books")
    Call<BookAddFields> addBook(@Body BookAddFields bookFields);


}
