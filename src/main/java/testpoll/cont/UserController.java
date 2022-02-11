package testpoll.cont;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testpoll.classes.Admin;
import testpoll.classes.Poll;
import testpoll.classes.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/user/")
public class UserController {


    @GetMapping("/list")
    public ResponseEntity<List<Poll>> getList() {
        List<Poll> rez;
        try {
            rez = Admin.getInstance().getPoolList();
            return new ResponseEntity(rez, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/answer/fill")
    public ResponseEntity fillPoll(@ModelAttribute("pollid") String pollid, @ModelAttribute("answers") ArrayList<String> answers, @ModelAttribute("ID") String ID) {
        try {
            User user = Admin.getInstance().getU(Integer.getInteger(ID));                      // примем, что получаем ответы списком строк, кол-во строк строго равно кол-ву и  порядку вопросов в опросе
            if (user == null) {
                user = new User(Integer.getInteger(ID));
            }
            user.add(Integer.getInteger(pollid), answers);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/answer/list")
    public ResponseEntity listAns(@ModelAttribute("ID") String ID) {
        try {
            User user = Admin.getInstance().getU(Integer.getInteger(ID));
            if (user != null) {
                return new ResponseEntity(user.getAnswers(), HttpStatus.OK);

            }
            return new ResponseEntity("Wrong user ID", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }


    }}
