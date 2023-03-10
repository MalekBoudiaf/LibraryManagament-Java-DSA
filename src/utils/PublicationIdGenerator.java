package utils;

public class PublicationIdGenerator {

    static public int generateID(String sequence){
      int uniqueId=0;
      for(char ch: sequence.toCharArray()){
          uniqueId+= ch;
      }
      return uniqueId;
    }
}
