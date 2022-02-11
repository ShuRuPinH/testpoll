package testpoll.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {

     int ID ;
     List <Answer> answers;

     public User(int ID) {
          this.ID = ID;
          answers = new ArrayList<Answer>();
     }

     public boolean add(int pollid, List answers){
          try {
               Admin.getInstance().getPoll(pollid);

               answers.add(new Answer( Admin.getInstance().getPoll(pollid),answers));
               return true;
          }
          catch (Exception e){
               return false;
          }
     }


     public List<Answer> getAnswers() {
          return answers;
     }
}
