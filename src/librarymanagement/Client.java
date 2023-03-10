package librarymanagement;

public class Client implements Comparable{


    // a class field setting the priority difference between the regular client and the VIP one
    // more specifically a regular client is given priority 1 and VIP is giver the higher priority 0
    protected int priority;
    private String name,email;
    private int ID;

    public Client(String name, String email, int id) {
        this.priority=1;
        this.name = name;
        this.email = email;
        this.ID=id;
    }

    // a method to get the client priority to distinguish whether it is a regular or VIP client
    public int getPriority(){return this.priority;}

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int compareTo(Object o) {
        Client client=(Client) o;
        // comparing Clients based on their ID's
        return ((Comparable)ID).compareTo(client.getID());
    }

    @Override
    public String toString() {
        return "Client type: Regular ,Name: "+this.getName()+" ,Email: "+this.getEmail()+" ,ID: "+this.getID();
    }
}
