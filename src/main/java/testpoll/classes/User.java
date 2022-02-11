package testpoll.classes;

import java.util.HashMap;
import java.util.List;

public class User {
     HashMap <Integer, List > answers;
     int ID ;

     public User(int ID) {
          this.ID = ID;
          answers = new HashMap<>();
     }

     public boolean add(int pollid, List answers){
          try {
               answers.add(pollid, answers);
               return true;
          }
          catch (Exception e){
               return false;
          }
     }


     public HashMap<Integer, List> getAnswers() {
          return answers;
     }
}
