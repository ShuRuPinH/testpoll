package testpoll.classes;

import java.util.List;

public class Answer {
    Poll poll;
    List<String> listAnwswers;

    public Answer(Poll poll, List<String> listAnwswers) {
        this.poll = poll;
        this.listAnwswers = listAnwswers;
    }
}
