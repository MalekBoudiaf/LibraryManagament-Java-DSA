package utils;

public class ClientIdGenerator {
    static private int uniqueId=0;
    static public int generateClientID(){
        int id=uniqueId;
        uniqueId++;
        return id;
    }
}
