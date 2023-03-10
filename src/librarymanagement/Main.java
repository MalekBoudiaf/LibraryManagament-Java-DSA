package librarymanagement;

import datastructures.Graph;
import datastructures.LinkedList;

public class Main {
    public static void main(String[] args) {
        Library lib=new Library();


        lib.addBlueRay("free guy",2021,"action");
        lib.addBook("Irwin Shaw","rich guy poor guy",1969,"leadership");
        lib.addBlueRay("casino royale",2008,"action");
        lib.addCD("random autor","fantasy CD",2021,"fantasy");
        lib.addBook("science guy","science 101",1969,"science");
        lib.addMagazine("politics are boring",2050,2050,"politics");

        lib.addClient("malek boudiaf","malek.boudiaf@vub.be");
        lib.addClient("sabrine chafai","sabrine.chafai@vub.be");
        lib.addVIPClient("sir john the third","john.the3@vip.fancy");

        lib.addSection("action");
        lib.addSection("leadership");
        lib.addSection("fantasy");
        lib.addSection("science");
        lib.addSection("politics");

        lib.connectSections("action","leadership");
        lib.connectSections("action","science");
        lib.connectSections("action","politics");
        lib.connectSections("leadership","science");
        lib.connectSections("politics","science");
        // lib.connectSections("leadership","fantasy");
        lib.connectSections("science","fantasy");

        // finding the shortest path between publication with id 2153 ie:fantasy CD which is in section fantasy
        // starting from the section action
        System.out.println("the shortest path to get to the publication is:");
        lib.findShortestPath(2153,"action");

        lib.printAllPublications();
        lib.printBorrowedItems();
        lib.printAllClients();


        lib.borrowBook(0,"Irwin Shaw","rich guy poor guy");
        lib.borrowBlueRay(1,"free guy",2021);
        lib.borrowBook(2,"Irwin Shaw","rich guy poor guy");


        lib.printAllPublications();
        lib.printBorrowedItems();

        lib.returnItem(988);

        lib.printAllPublications();
        lib.printBorrowedItems();

        int waitingClientID=lib.returnItem(2604);

        if(waitingClientID!=-1){
            // borrowing the item to the first client on the waiting list
            lib.borrowBook(waitingClientID,"Irwin Shaw","rich guy poor guy");
        }else{
            System.out.println("no client waiting to borrow this item");
        }

        System.out.println("client on the waiting list of item with id 2604");
        // notice client 2 returned the item but he is still on the waiting list since we didn't pop it
        // do we pop??
        System.out.println(lib.returnItem(2604));

        lib.printAllPublications();
        lib.printBorrowedItems();
        
    }
}