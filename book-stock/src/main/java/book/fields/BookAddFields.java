package book.fields;

/**
 * Created by sam on 3/5/18.
 */

public class BookAddFields {

    private String title;
    private String subtitle;
    private String categories;
    private String description;
    private String publisheddate;
    private String isbn;

    public BookAddFields(String title,String subtitle,String categories,String description,
                         String publisheddate,String isbn){
        this.title = title;
        this.subtitle = subtitle;
        this.categories = categories;
        this.description = description;
        this.publisheddate = publisheddate;
        this.isbn = isbn;

    }
}
