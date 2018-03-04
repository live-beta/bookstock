package book.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import book.api.APICalls;
import book.fields.BookFields;
import books.R;

/**
 * Created by sam on 1/7/18.
 */

public class BookInfoActivity extends AppCompatActivity {

    private Button back,postBook;
    private TextView bookTitle,subTitleView,categoriesView,descriptionView,publishedDateView,
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
       // String industryIdentifiers = intent.getStringExtra("industryIdentifiers");

        bookTitle.setText(title);
        subTitleView.setText(subTitle);
        categoriesView.setText(categories);
        descriptionView.setText(description);
        publishedDateView.setText(publishedDate);


        postBook = findViewById(R.id.addBook);
        postBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                APICalls apiCalls = new APICalls();
                apiCalls.addBook(title,subTitle,categories,description,publishedDate,isbn);

                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
