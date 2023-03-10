package librarymanagement;

public class Blueray extends Publication{


    public Blueray(String title, int year, int id, String section) {
        super(title, year, id, section);
    }


    @Override
    public String toString() {
        return "type: Blueray ,Title: "+this.getTitle()+" ,Year: "+this.getYear_of_publication()+" ,ID: "+this.getId();
    }

}
