package librarymanagement;

public class VIPclient extends Client{


    public VIPclient(String name, String email, int id) {
        super(name, email, id);
        // setting the super class's protected field "priority" to 0 to give the VIP client higher priority
        this.priority=0;
    }

    @Override
    public String toString() {
        return "Client type: VIP ,Name: "+this.getName()+" ,Email: "+this.getEmail()+" ,ID: "+this.getID();
    }

    /*
               .
               .
               .
               .
    VIP client specific methods
               .
               .
               .
               .

     */
}
