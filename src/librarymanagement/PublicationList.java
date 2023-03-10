package librarymanagement;


import datastructures.Vector;

public class PublicationList {
    private Vector bluerays,CDs,books,magazines;
    public PublicationList() {
        this.bluerays=new Vector();
        this.CDs=new Vector();
        this.books=new Vector();
        this.magazines=new Vector();
    }
    public void addBlueray(Blueray blueray){
        this.bluerays.addLast(blueray);
    }
    public Blueray findBlueray(String title, int yearOfPublication){
        for(int i=0;i<bluerays.size();i++){
            Blueray current=(Blueray) bluerays.get(i);
            if(current.getTitle().compareTo(title)==0 && current.getYear_of_publication()==yearOfPublication){
                return current;
            }
        }
        return null;
    }

    public void addBook(Book book){
        this.books.addLast(book);
    }
    public Book findBook(String author, String title){
        for(int i=0;i<books.size();i++){
            Book current=(Book) books.get(i);
            if(current.getTitle().compareTo(title)==0 && current.getAuthor().compareTo(author)==0){
                return current;
            }
        }
        return null;
    }
    public void addCD(CD cd){
        this.CDs.addLast(cd);
    }
    public CD findCD(String author, String title){
        for(int i=0;i<CDs.size();i++){
            CD current=(CD) CDs.get(i);
            if(current.getTitle().compareTo(title)==0 && current.getAuthor().compareTo(author)==0){
                return current;
            }
        }
        return null;
    }
    public void addMagazine(Magazine magazine){
        this.magazines.addLast(magazine);
    }
    public Magazine findMagazine(String title, int yearOfPublication, int issue){
        for(int i=0;i<magazines.size();i++){
            Magazine current=(Magazine) magazines.get(i);
            if(current.getTitle().compareTo(title)==0 && current.getYear_of_publication()==yearOfPublication
                 && current.getIssue()==issue){
                return current;
            }
        }
        return null;
    }

    @Override
    public String toString() {
       String s="";
       s+=this.bluerays.toString();
        s+=this.books.toString();
        s+=this.magazines.toString();
        s+=this.CDs.toString();
        return s;
    }
}
