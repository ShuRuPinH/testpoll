package testpoll.cont;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testpoll.classes.Admin;
import testpoll.classes.Poll;
import testpoll.classes.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/user/")
public class UserController {


    @GetMapping ("/list")
    public ResponseEntity <List<Poll>> getList(){
        List <Poll> rez;
        try{  rez =Admin.getInstance().getPoolList();
            return new ResponseEntity(rez, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/answer/fill")
    public ResponseEntity fillPoll(@ModelAttribute("pollid") String pollid, @ModelAttribute("answers") ArrayList <String> answers , int ID){
        try {
            User user = Admin.getInstance().getU(ID);                      // примем, что получаем ответы списком строк, кол-во строк строго равно кол-ву и  порядку вопросов в опросе
            if (user == null) {
                user = new User(ID);
            }
            user.add(Integer.getInteger(pollid), answers);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/answer/list")
    public ResponseEntity listAns( int ID){
        try {
            User user = Admin.getInstance().getU(ID);
            if (user != null) {
                HashMap <Integer , List> answersHashMap = user.getAnswers();    // передаем  всю необходимую для информацию для фронта
                int countOfDonePolls = answersHashMap.size();

                if (countOfDonePolls >0){                                      // выбрал  массив так мне кажется будет удобнее, его отображать
                    Object [][] rez = new Object[countOfDonePolls][2];
                    int cnt=0;
                    for(Map.Entry <Integer , List> entry : answersHashMap.entrySet()){
                        rez[cnt][0]= Admin.getInstance().getPoll(entry.getKey());   // положим в первую ячейку объект опрос, он хранит список вопросов
                        rez[cnt][2]= entry.getValue();                              // во вторую список ответов, так  информации для отображения достаточно,
                    }


                }



            }

            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

}
