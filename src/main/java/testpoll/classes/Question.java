package testpoll.classes;


public class Question {
    private String text;
    private int type;
    private String answer;
    private int ID;  //id вопроса его индекс в списке опроса

    /*
       1 Textarea,
       2 Radios,
       3 Checks
    */

    public Question(String text, int type) {
        this.text = text;
        this.type=type;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ind) {
        this.ID = ind;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }
}
