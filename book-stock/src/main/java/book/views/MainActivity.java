package book.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import book.Books.BookAdapter;
import book.api.APICalls;
import book.fields.BookFields;
import book.networking.NetworkCalls;
import books.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final int ZXING_CAMERA_PERMISSION = 1;
    final Context context = this;
    Button launchScanner;
    private Class<?> mClss;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        setupToolbar();
        try {
            loadBooks();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        FloatingActionButton addBook = findViewById(R.id.addBookByScanner);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SimpleScannerActivity.class);

                startActivity(intent);
            }
        });
    }

    public void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZXING_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (mClss != null) {
                        Intent intent = new Intent(this, mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    public void loadBooks() throws JSONException, NoSuchFieldException {

        final RecyclerView recyclerView = ((Activity) context)
                .findViewById(R.id.bookViewerRecycler);

        APICalls apiCalls = new APICalls();
        final ArrayList bookValues = apiCalls.getBooks();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SharedPreferences sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(context);
        final String token = "Bearer " + sharedPreferences.getString("token", "");

        NetworkCalls networkCalls = retrofit.create(NetworkCalls.class);

        Call<ArrayList<BookFields>> getBooks = networkCalls.getBooks(token);

        getBooks.enqueue(new Callback<ArrayList<BookFields>>() {

            @Override

            public void onResponse(Call<ArrayList<BookFields>> call,
                                   Response<ArrayList<BookFields>> response) {

                BookAdapter adapter = new BookAdapter(context, response.body());
                recyclerView.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);

                recyclerView.setItemAnimator(new DefaultItemAnimator());

            }

            @Override
            public void onFailure(Call<ArrayList<BookFields>> call, Throwable t) {

            }
        });


    }
}