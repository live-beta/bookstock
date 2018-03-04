package book.api;

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

/**
 * Class Implements BucketList API calls
 */

public class APICalls {

    public static String token;

    public ArrayList<BookFields> bookData = new ArrayList<>();

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean login(String userName, String password) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        if (userName.equals("") || password.equals("")) {
            Log.d("Value", " User name or password cannot be empty");
            return false;
        } else {

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://10.0.2.2:5000/api/v1/auth/login");
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("username", userName));
            nameValuePair.add(new BasicNameValuePair("password", password));

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
            }
            try {
                HttpResponse response = httpClient.execute(httpPost);
                String responseString = EntityUtils.toString(response.getEntity());

                if (responseString.contains("Could not log you in, Check credentials")) {
                    return false;
                }

                JSONObject jsonResponse = new JSONObject(responseString);
                String token = jsonResponse.getString("token");

                setToken(token);

                return true;


            } catch (ClientProtocolException e) {
                Log.d("Error", e.getMessage());
            } catch (IOException e) {

                e.printStackTrace();

            } catch (JSONException e) {
                e.printStackTrace();
            }

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


        token = getToken();

        Log.d("BucketList Token", token.toString());

        if (token.equals("")) {
            Log.d("Auth ", "Not Authorized, Login in again");
        } else {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://10.0.2.2:5000/api/v1/book?limit=1000");
            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpGet.addHeader("Authorization", "Bearer " + token);


            try {
                // passing data from API
                HttpResponse response = httpClient.execute(httpGet);
                String responseStr = EntityUtils.toString(response.getEntity());

                BookFields bookFields = new BookFields();

                String newResponse = responseStr.replace("[", "").
                        replace("{", "");


                Log.d("New resp", newResponse);

                ArrayList dataObj = new ArrayList(Arrays.asList(newResponse.split(" \\}")));

                for (int index = 0; index < dataObj.size(); index++) {
                    ArrayList data = new ArrayList(Arrays.asList(dataObj.get(index).toString().split(",")));
                    bookData.add(bookFields);


                }


            } catch (ClientProtocolException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


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
