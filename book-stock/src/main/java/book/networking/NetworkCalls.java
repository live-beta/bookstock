package book.networking;

/**
 * Created by sam on 3/4/18.
 */

import book.fields.UserFields;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface NetworkCalls {

    @POST("auth/login")
    Call<UserFields> LoginCall(@Body UserFields userFields);

}
