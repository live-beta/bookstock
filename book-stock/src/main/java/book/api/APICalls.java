package book.api;

import android.content.res.Resources;
import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import book.fields.BookFields;
import book.fields.UserFields;
import book.networking.NetworkCalls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class Implements BucketList API calls
 */

public class APICalls {

    public static String token;

    public ArrayList<BookFields> bookData = new ArrayList<>();

    public String getToken() {
        return this.token;
    }

    /*Retrofit builder*/

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void setToken(String token) {
        this.token = token;
    }

    public boolean login(String  userName, String password) {

        final boolean state;



        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        if (userName.equals("") || password.equals("")) {
            Log.d("Value", " User name or password cannot be empty");
            return false;
        } else {

            // Create an instance of retrofit


        }
        return false;
    }

    public JSONObject getBookInfo(String isbn) {
        JSONObject bookInfo = new JSONObject();


        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("https://www.googleapis.com/book/v1/volumes?q=isbn:" + isbn);


        try {
            HttpResponse response = httpClient.execute(httpGet);
            String responseStr = EntityUtils.toString(response.getEntity());

            bookInfo = new JSONObject(responseStr);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bookInfo;
    }

    public ArrayList<BookFields> getBooks() throws NoSuchFieldException {




        return bookData;
    }


    public boolean addBook(String title, String subTitle, String categories, String description,
                           String publishedDate, String isbn) {

        token = getToken();
        if (token.equals("")) {
            Log.d("Auth ", "Not Authorized, Login in again");
            return false;
        } else {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5000/api/v1/book");
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.addHeader("Authorization", "Bearer " + token);

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("title", title));
                nameValuePairs.add(new BasicNameValuePair("subtitle", subTitle));
                nameValuePairs.add(new BasicNameValuePair("categories", categories));
                nameValuePairs.add(new BasicNameValuePair("description", description));
                nameValuePairs.add(new BasicNameValuePair("publisheddate", publishedDate));
                nameValuePairs.add(new BasicNameValuePair("isbn", isbn));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httpPost);
                int responseCode = response.getStatusLine().getStatusCode();

                if (responseCode == 200) {
                    return true;
                } else {
                    return false;
                }


            } catch (ClientProtocolException e) {
                return false;

            } catch (IOException e) {
                Log.d("Exception", e.getLocalizedMessage());

            }

        }
        return false;
    }


}
