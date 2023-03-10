package librarymanagement;

public class Book extends Publication {

    private String author;

    public Book( String title, int year,String author, int id, String section) {
        super(title, year, id, section);
        this.author=author;

    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "type: Book ,Title: "+this.getTitle()+" ,Author: "+this.getAuthor()+" ,Year: "+this.getYear_of_publication()+" ,ID: "+this.getId();
    }
}
