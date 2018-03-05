package book.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import book.fields.BookAddFields;
import book.fields.BookFields;
import book.networking.NetworkCalls;
import books.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that displays information from the scanned book and presents it in views
 * user is able to add new book to database
 */

public class BookInfoActivity extends AppCompatActivity {

    private Button back,postBook;
    private TextView bookTitle,subTitleView,categoriesView,
            descriptionView,publishedDateView,
            industryIdentifiersView;
    BookFields bookFields;


    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        back =findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }
        });

        bookTitle =findViewById(R.id.bookTitle);
        subTitleView = findViewById(R.id.bookSubTitle);
        categoriesView = findViewById(R.id.categories);
        descriptionView = findViewById(R.id.description);
        publishedDateView = findViewById(R.id.publishedDates);
        industryIdentifiersView = findViewById(R.id.industryIdentifiers);


        Intent intent = getIntent();

       final String title = intent.getStringExtra("Title");
        final String subTitle = intent.getStringExtra("subTitle");
        final String categories = intent.getStringExtra("categories");
        final String description =intent.getStringExtra("description");
        final String publishedDate = intent.getStringExtra("publishedDate");
        final String isbn = intent.getStringExtra("isbn");

        bookTitle.setText(title);
        subTitleView.setText(subTitle);
        categoriesView.setText(categories);
        descriptionView.setText(description);
        publishedDateView.setText(publishedDate);


        postBook = findViewById(R.id.addBook);
        postBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:5000/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                BookAddFields bookAddFields  = new BookAddFields(title,subTitle,categories,
                        description,publishedDate,isbn);
                SharedPreferences sharedPreferences = PreferenceManager.
                        getDefaultSharedPreferences(context);
                final String token = "Bearer " + sharedPreferences.getString("token","");
                NetworkCalls networkCalls = retrofit.create(NetworkCalls.class);
                Call<BookAddFields> addBooks = networkCalls.addBook(token,bookAddFields);

                addBooks.enqueue(new Callback<BookAddFields>() {
                    @Override
                    public void onResponse(Call<BookAddFields> call,
                                           Response<BookAddFields> response) {

                        Intent intent = new Intent(context,MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<BookAddFields> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),
                                "Unable to communicate with server",
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}
