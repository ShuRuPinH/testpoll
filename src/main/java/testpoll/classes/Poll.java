package testpoll.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Poll {

    private int ID; //id опроса его индекс в общем списке (админа)
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String description;

    List<Question> questionList;


    public Poll(String title, LocalDateTime start, LocalDateTime end, String description) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        questionList = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart() {
        return start;
    }


    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int addQ(Question q) {

        questionList.add(q);
        return questionList.indexOf(q);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void remQ(int ind) {
        questionList.remove(ind);
    }


    public void editQ(int qid, String text, int type) {
        questionList.get(qid).setText(text);
        questionList.get(qid).setType(type);
    }
}
