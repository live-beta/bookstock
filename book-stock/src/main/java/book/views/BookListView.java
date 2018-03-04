package book.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import book.fields.BookFields;
import books.R;

/**
 * Class implements Recycler view for book Views
 */

public class BookListView extends RecyclerView.Adapter<BookListView.BookViewAdapter> {


    /**
     * For views declare inflaotors, Inflators can either be by default system
     * defined or defined in a unique behaviour( You can override the API definition)
     */
    private LayoutInflater layoutInflater;

    /**
     * Defining the data type returned by the system's aPI
     */
    /*TODO
    * Streamline  the book data to fit into the fields schema*/
    private ArrayList<BookFields> bookData;


    public  BookListView(Context context, ArrayList<BookFields> bookDataResponse){

    }
    @Override
    public BookViewAdapter onCreateViewHolder(ViewGroup parent, int viewType){

        // define the view from a card view activity layout

        View view  = layoutInflater.inflate(R.layout.book_item_views, parent,false);

        BookViewAdapter viewHolder = new BookViewAdapter(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookViewAdapter holder,int position){
        BookFields currentBookFieldsData = bookData.get(position);
        holder.setData(currentBookFieldsData,position);
    }

    @Override
    public  int getItemCount(){
        return bookData.size();
    }

    class BookViewAdapter extends RecyclerView.ViewHolder{

        public BookViewAdapter(View bookView){
            super(bookView);


        }

        public void setData(final BookFields current, final int position){



        }
    }

}
