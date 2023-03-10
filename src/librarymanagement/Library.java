package librarymanagement;

import datastructures.Graph;
import datastructures.Tree;
import datastructures.Vector;
import utils.ClientIdGenerator;
import utils.PublicationIdGenerator;


public class Library implements ILibraryManagement{
    private Tree publicationList;
    private Vector clients;
    private Tree borrowedPublications;
    private Graph sections;

    public Library() {
        this.publicationList = new Tree();
        this.clients = new Vector();
        this.borrowedPublications = new Tree();
        this.sections=new Graph();
    }

    @Override
    public int borrowBook(int client, String author, String title) {
        int id= PublicationIdGenerator.generateID(author+title);
        Publication dummyPub=new Publication("",0,id,"");
        //search if the book is in the library
        Publication book=(Publication) publicationList.find(dummyPub);
        // if book in the library borrow it
        if(book!=null){
            // clients are given a unique Id which is the sequence of their creation
            // meaning that the ID of the client is its index in the clients vector (assuming client can't be removed for now)
            Client c=(Client) clients.binarySearch(new Client("","",client)) ;
            // if book is not already borrowed then borrow it to the client and add it to the borrowed publications list
            if(!book.isBorrowed()){
                book.borrow(c);
                borrowedPublications.insert(book);
            // if the book is already borrowed then add the client to the waiting list
            }else{
                book.addToWaitingList(c);
            }
            return book.getId();
        }
        // if book is not in the library return a non valid ID -1
        else {
            return -1;
        }
    }

    @Override
    public int lookAtMagazine(int client, String title, int yearOfPublication, int issue) {
        int id= PublicationIdGenerator.generateID(title+yearOfPublication+issue);
        Publication dummyPub=new Publication("",0,id,"");
        Publication mag=(Publication) publicationList.find(dummyPub);
        if(mag!=null){
            Client c=(Client) clients.binarySearch(new Client("","",client)) ;
            if(!mag.isBorrowed()){
                mag.borrow(c);
                borrowedPublications.insert(mag);
            }else{
                mag.addToWaitingList(c);
            }
            return mag.getId();
        }else {
            return -1;
        }
    }

    @Override
    public int borrowBlueRay(int client, String title, int yearOfPublication) {
        int id= PublicationIdGenerator.generateID(title+yearOfPublication);
        Publication dummyPub=new Publication("",0,id,"");
        Publication blueray=(Publication) publicationList.find(dummyPub);
        if(blueray!=null){
            Client c=(Client) clients.binarySearch(new Client("","",client)) ;
            if (!blueray.isBorrowed()) {
                blueray.borrow(c);
                borrowedPublications.insert(blueray);
            }else{
                blueray.addToWaitingList(c);
            }
            return blueray.getId();
        }else {
            return -1;
        }
    }

    @Override
    public int borrowCD(int client, String author, String title) {
        int id= PublicationIdGenerator.generateID(author+title);
        Publication dummyPub=new Publication("",0,id,"");
        Publication cd=(Publication) publicationList.find(dummyPub);
        if(cd!=null){
            Client c=(Client) clients.binarySearch(new Client("","",client)) ;
            if (!cd.isBorrowed()){
                cd.borrow(c);
                borrowedPublications.insert(cd);
            }else{
                cd.addToWaitingList(c);
            }
            return cd.getId();
        }else {
            return -1;
        }
    }

    @Override
    public int returnItem(int publicationID) {
        //getting the borrowed publication object corresponding to the ID
        //assuming that the passed ID is valid and is of an already borrowed publication
        Publication pub=(Publication) borrowedPublications.find(new Publication("",0,publicationID,""));
        //calling return publication on the publication object returning the first client on the waiting list
        Client waitingClient=pub.returnItem();
        // Removing the publication from the borrowed list using the pubIndex as it has been returned
        borrowedPublications.remove(pub);
        //if waitingClient list is not empty return the id of client
        if (waitingClient!=null){
            return waitingClient.getID();
        }
        // waitingClient list is empty return -1
        else{
            return -1;
        }
    }



    @Override
    public int addBook(String author, String title, int yearOfPublication, String section) {
        String idSequence=author+ title;
        // generating a unique id for the publication
        int id= PublicationIdGenerator.generateID(idSequence);
        // creating the publication
        Book book=new Book(title, yearOfPublication, author, id, section);
        this.publicationList.insert(book);
        return book.getId();
    }

    @Override
    public int addMagazine(String title, int yearOfPublication, int issue, String section) {
        String idSequence=title+ yearOfPublication + issue;
        int id= PublicationIdGenerator.generateID(idSequence);
        Magazine mag=new Magazine(title, yearOfPublication, issue, id, section);
        this.publicationList.insert(mag);
        return mag.getId();
    }

    @Override
    public int addBlueRay(String title, int yearOfPublication, String section) {
        String idSequence=title+ yearOfPublication;
        int id= PublicationIdGenerator.generateID(idSequence);
        Blueray blu=new Blueray(title, yearOfPublication, id, section);
        this.publicationList.insert(blu);
        return blu.getId();
    }

    @Override
    public int addCD(String author, String title, int yearOfPublication, String section) {
        String idSequence=author+ title;
        int id= PublicationIdGenerator.generateID(idSequence);
        CD cd=new CD(title, yearOfPublication, author, id, section);
        this.publicationList.insert(cd);
        return cd.getId();
    }

    @Override
    public int addClient(String name, String email) {
        int id= ClientIdGenerator.generateClientID();
        Client client=new Client(name,email,id);
        this.clients.addLast(client);
        return client.getID();
    }

    @Override
    public int addVIPClient(String name, String email) {
        int id= ClientIdGenerator.generateClientID();
        VIPclient client=new VIPclient(name,email,id);
        this.clients.addLast(client);
        return client.getID();
    }

    @Override
    public void addSection(String name) {
        // adding a vertex to the sections graph
        sections.addNode(name);
    }

    @Override
    public void connectSections(String section1, String section2) {
        // adding an undirected edge section1-->section2 / section2-->section1 with wright 1
        // this will result in an undirected unweighted graph using the implementation of the directed graph
        sections.addEdge(section1,section2,1);
        sections.addEdge(section2,section1,1);
    }

    @Override
    public void findShortestPath(int publicationID, String startSection) {
        // find the publication in the library from the id
        // create dummy publication object as the Publication Tree find method expects a publication object as parameter
        Publication dummyPub=new Publication("",0,publicationID,"");
        // find the publication in the library
        Publication pub=(Publication) publicationList.find(dummyPub);
        // getting the section of the wanted publication
        String endSection=pub.getSection();
        // calling the getShortestPath() method on the sections graph which will return a list containing the shortest
        // path section using the bellman-ford algorithm
        System.out.println(sections.getShortestPath(startSection,endSection));
    }

    @Override
    public void printAllPublications() {
            System.out.println("all publications in the library:");
            System.out.println(publicationList.toString());
    }

    public void printBorrowedItems(){
        System.out.println("borrowed publications in the library:");
        System.out.println(borrowedPublications.toString());
    }


    @Override
    public void printAllClients() {
        System.out.println("all clients registered in the library:");
        System.out.println(clients.toString());
    }
}
