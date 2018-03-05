package book.fields;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookFields {

    @SerializedName("book_category")
    @Expose
    private String bookCategory;
    @SerializedName("book_isbn")
    @Expose
    private String bookIsbn;
    @SerializedName("book_name")
    @Expose
    private String bookName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;

    public BookFields(String bookName,String subTitle, String bookCategory,
                      String description, String publishedDate, String
                              bookIsbn) {

        this.bookName = bookName;
        this.subtitle = subTitle;
        this.bookCategory = bookCategory;
        this.description = description;
        this.publishedDate = publishedDate;
        this.bookIsbn = bookIsbn;

    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

}