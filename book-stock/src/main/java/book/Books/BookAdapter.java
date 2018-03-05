package book.Books;

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


import org.json.JSONException;

import java.util.ArrayList;

import book.fields.BookFields;
import books.R;


/**
 *
 * the class extends a recycleview --> has an Adapter method of type custom (local, overriden method)--> to
 * enable recycling..
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookListViewAdapter> {

    private LayoutInflater layoutInflator;
    private  ArrayList<BookFields> bookData;


    /**
     *
     * Constructor with required parameters  Context and Data
     */


    public BookAdapter(Context context,ArrayList<BookFields>  bookInfo){

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


           BookFields currentBookObject = bookData.get(position);

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

        Log.d("data", String.valueOf(bookData));
        return bookData.size();
    }


    class BookListViewAdapter extends RecyclerView.ViewHolder{
        TextView id,bookName,subTitles,description,publisheDates,industryIdentifiers;


        ImageView itemsList, deleteBucketlist;
        int position;


        public BookListViewAdapter(View bookListView){
            super(bookListView);

            id = bookListView.findViewById(R.id.idView);
            bookName = bookListView.findViewById(R.id.bookName);
            subTitles= bookListView.findViewById(R.id.subTitles);
            description = bookListView.findViewById(R.id.description);
            publisheDates = bookListView.findViewById(R.id.publishedDates);

            industryIdentifiers = bookListView.findViewById(R.id.industryIdentifiers);



        }

        public void setData(final BookFields current, final int position) throws JSONException, NoSuchFieldException {

            this.position = position;
            bookName.setText(current.getBookName());


        }

    }

}
