package book.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import book.networking.NetworkCalls;
import books.R;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sam on 3/5/18.
 */

public class NetworkInstance {


    private String url;



    public Retrofit.Builder createRetrofitClass(Context context){
        /*Creating a retrofit instance with an interceptor*/

        this.url = context.getResources().getString(R.string.url);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String token = sharedPreferences.getString("token", "");



        Log.d("Token in net ", token);

        /* Gson definition for faulty JSON*/

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        /* Inerceptor for token headers*/

        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request modifiedRequest = chain.request().newBuilder().
                        addHeader("Authorization", "Bearer " + token).build();
                return chain.proceed(modifiedRequest);
            }
        };

        /*Adding interceptor to OkHTTPClient*/

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        Retrofit.Builder retroBuild = new Retrofit.Builder()
                .baseUrl(this.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client);


        return retroBuild;
    }


    public NetworkCalls networkCallsInstance(Context context){

        Retrofit retrofit = createRetrofitClass(context).build();

        return retrofit.create(NetworkCalls.class);
    }
}
