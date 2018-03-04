package me.dm7.barcodescanner.zxing.bookstock.books.Books;

/**
 * Created by sam on 12/20/17.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.dm7.barcodescanner.zxing.bookstock.R;
import me.dm7.barcodescanner.zxing.bookstock.books.api.APICalls;
import me.dm7.barcodescanner.zxing.bookstock.books.fields.BookFields;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 *
 * the class extends a recycleview --> has an Adapter method of type custom (local, overriden method)--> to
 * enable recycling..
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookListViewAdapter> {

    private LayoutInflater layoutInflator;
    private ArrayList bookData;

    // Changing the values of book data



    /**
     *
     * Constructor with required parameters  Context and Data
     */


    public BookAdapter(Context context,ArrayList bookInfo){

        this.bookData = bookInfo;
        this.layoutInflator  = LayoutInflater.from(context);
    }

    /**
     *
     * Creating a view holder of custom type.
     * The view holder contains an adapter(with references to the view group)
     * and a data setter(setting data behaviour with widgets)
     */
    @Override
    public BookListViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflator.inflate(R.layout.book_adapter_view, parent, false);

        /**
         * The adapter recalls itself as a new instance --> Recycling
         */

        BookListViewAdapter viewHolder = new BookListViewAdapter(view);

        return viewHolder;

    }

    /**
     *
     * Data Binder that calls adapter data setter, depending on the data type
     */

    @Override
    public void onBindViewHolder(BookListViewAdapter holder, int position) {


           Object  currentBookObject = bookData.get(position);

        try {
            holder.setData(currentBookObject,position);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return bookData.size();
    }


    class BookListViewAdapter extends RecyclerView.ViewHolder{
        TextView id,bookName,subTitles,description,publisheDates,industryIdentifiers;


        ImageView itemsList, deleteBucketlist;
        int position;


        public BookListViewAdapter(View bookListView){
            super(bookListView);

            id =(TextView)bookListView.findViewById(R.id.idView);
            bookName =(TextView)bookListView.findViewById(R.id.bookName);
            subTitles= (TextView)bookListView.findViewById(R.id.subTitles);
            description = (TextView)bookListView.findViewById(R.id.description);
            publisheDates = (TextView)bookListView.findViewById(R.id.publishedDates);

            industryIdentifiers = (TextView)bookListView.findViewById(R.id.industryIdentifiers);



        }

        public void setData(final Object current, final int position) throws JSONException, NoSuchFieldException {

            this.position = position;
        }

    }

}
