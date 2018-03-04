package me.dm7.barcodescanner.zxing.bookstock.books.fields;

/**
 * Book fields, class with the books model
 */

public class BookFields {

    private String title, subTitle, authors, categories, description, publishedDate, industryIdentifiers;


    private String id, userId;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    private String isbn;
    public BookFields(String title,String subTitle, String categories,
                      String description,String publishedDate,String industryIdentifiers) {

        this.title = title;
        this.subTitle = subTitle;
        this.categories = categories;
        this.description = description;
        this.publishedDate = publishedDate;
        this.industryIdentifiers = industryIdentifiers;

    }

    public BookFields() {

    }

    public String getId() {
        return id;
    }

    public void setId(String idEntry) {
        this.id = idEntry;
    }

    public void setUserId(String userIdEntry) {
        this.userId = userIdEntry;
    }


    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subTitle;
    }

    public void setSubtitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
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

    public String getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(String industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }
}
