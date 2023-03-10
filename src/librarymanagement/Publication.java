package librarymanagement;
import datastructures.PriorityQueue;

public class Publication implements Comparable {
    //this is the super class for creating publication objects PS:a blueray publication can be created directly from this class

    // a priority queue of Clients waiting to borrow the already borrowed specific Publication
    private PriorityQueue waitingList;

    private int Id;
    private String title;
    private int year_of_publication;
    private Boolean borrowed;
    private String section;



    public Publication(String title, int year,int id, String section){
        this.waitingList=new PriorityQueue();
        this.Id=id;
        this.borrowed=false;
        this.title=title;
        this.year_of_publication=year;
        this.section=section;
    }

    public String getSection() {
        return section;
    }

    public void borrow(Client client){
        // change the state of the publication to borrowed
        this.borrowed=true;
    }
    public void addToWaitingList(Client client){
        // add the client to the waiting list
        waitingList.push(client,client.getPriority());
    }

    public Client returnItem(){
        // change the state of the publication to not borrowed
        borrowed=false;
        // if the waiting list is not empty return the first client on the waiting list
        if(!waitingList.isEmpty()){
            return ((Client) waitingList.top());
        // if no one is waiting return null
        }else{
            return null;
        }
    }
    public int getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear_of_publication() {
        return year_of_publication;
    }

    public Boolean isBorrowed() {
        return borrowed;
    }


    @Override
    public int compareTo(Object o) {
        Publication pub=(Publication)o;
        return Integer.compare(this.Id, pub.getId());
    }
}
