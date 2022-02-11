package testpoll.cont;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testpoll.classes.Admin;
import testpoll.classes.Poll;
import testpoll.classes.Question;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/rest/admin/")
public class AdminController {

    static DateTimeFormatter dtf =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    // предполагаем, что даты будут из <input id="datetime" type="datetime-local">, и все данные отправляются POST забросом из веб формы


    @PostMapping("/poll/add/")
    public ResponseEntity addPoll(@ModelAttribute("title") String title, @ModelAttribute("start") String start, @ModelAttribute("end") String end,
                                  @ModelAttribute("desc") String desc) {
        try {
            LocalDateTime startPoll = LocalDateTime.parse(start, dtf);
            LocalDateTime endPoll = LocalDateTime.parse(end, dtf);

            Poll poll = new Poll(title, startPoll, endPoll, desc);
            poll.setID(Admin.getInstance().add(poll));  // присваиваем опросу ID, его индекс в списке
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/poll/del/")
    public ResponseEntity delPoll(@ModelAttribute("id") String id) {

        try {
            Admin.getInstance().del(Integer.getInteger(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/poll/edit/")
    public ResponseEntity editPoll(@ModelAttribute("id") String id, @ModelAttribute("title") String title, @ModelAttribute("end") String end,
                                   @ModelAttribute("desc") String desc) {


        try {
            LocalDateTime endPoll = LocalDateTime.parse(end, dtf);

            Admin.getInstance().change(Integer.getInteger(id), title, endPoll, desc);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    //// Работа с вопросами

    @PostMapping("/ques/add/")
    public ResponseEntity addQ(@ModelAttribute("pollid") String pollid,@ModelAttribute("text") String text, @ModelAttribute("type") String type){  // выбрал число для передачи типа , изменить будет не сложно
        try {
            Question question = new Question(text,Integer.getInteger(type));
            question.setID( Admin.getInstance().getPoll(Integer.getInteger(pollid)).addQ(question));  // присваиваем вопросу ID, его индекс в списке
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/ques/del/")
    public ResponseEntity delQ(@ModelAttribute("pollid") String pollid, @ModelAttribute("qid") String qid ){

        try {
            Admin.getInstance().getPoll(Integer.getInteger(pollid)).remQ(Integer.getInteger(qid));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/ques/edit/")
    public ResponseEntity editQ(@ModelAttribute("pollid") String pollid, @ModelAttribute("qid") String qid , @ModelAttribute("text") String text, @ModelAttribute("type") String type){

        try {
            Admin.getInstance().getPoll(Integer.getInteger(pollid)).editQ(Integer.getInteger(qid), text, Integer.getInteger(type));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

}
