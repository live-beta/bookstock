package book.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import book.api.APICalls;
import book.fields.BookFields;
import books.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends BaseScannerActivity implements ZXingScannerView.ResultHandler {
    private final Context context = this;
    private ZXingScannerView mScannerView;
    private String title, subTitle, authors, categories, description, publishedDate, isbn, industryIdentifiers;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_simple_scanner);
        setupToolbar();

        ViewGroup contentFrame = findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {


        Toast.makeText(this, "Contents = " + rawResult.getText() +
                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

        isbn = rawResult.getText();
        APICalls apiCalls = new APICalls();

        /**
         * TODO
         * Complete all data fields
         *
         */
        try {

            JSONObject bookObject = apiCalls.getBookInfo(rawResult.getText());


            title = bookObject.getJSONArray("items").getJSONObject(0)
                    .getJSONObject("volumeInfo").getString("title");

            Log.d("Book Title: ", String.valueOf(bookObject.getJSONArray("items").
                    getJSONObject(0).getJSONObject("volumeInfo").getString("title")));


            try {
                subTitle = bookObject.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("subtitle");
            }catch (Exception e){
                Log.d("Setting subtitle ", e.getMessage());
                subTitle = "Not presented";

            }
            try {
                categories = bookObject.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("categories");
            }catch (Exception e){
                categories = "Not presented";
            }
            try {
                description = bookObject.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("description");
            }catch (Exception e){
                description = "Not presented";
            }
            try {
                publishedDate = bookObject.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("publishedDate");
            }catch (Exception e){
                publishedDate = "Not presented";
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        /*
        * TODO
        * Check buggy delays in camera invocation
        *
        * */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerActivity.this);
            }
        }, 2000);



        BookFields bookFields = new BookFields(title, subTitle,
                categories, description, publishedDate, industryIdentifiers);

        Intent intent = new Intent(context, BookInfoActivity.class);
        intent.putExtra("Title", bookFields.getBookName());
        intent.putExtra("subTitle", bookFields.getSubtitle());
        intent.putExtra("categories", bookFields.getBookCategory());
        intent.putExtra("description", bookFields.getDescription());
        intent.putExtra("publishedDate", bookFields.getPublishedDate());
        intent.putExtra("isbn", isbn);


        startActivity(intent);
    }
}
