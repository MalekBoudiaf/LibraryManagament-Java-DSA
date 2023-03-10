package librarymanagement;

public class Magazine extends Publication{
    private int issue;

    public Magazine(String title, int year, int issue, int id, String section) {
        super(title, year, id, section);
        this.issue = issue;
    }

    public int getIssue() {
        return issue;
    }

    @Override
    public String toString() {
        return "type: Magazine ,Title: "+this.getTitle()+" ,Year: "+this.getYear_of_publication()+" ,Issue: "+this.getIssue()+" ,ID: "+this.getId();
    }
}
