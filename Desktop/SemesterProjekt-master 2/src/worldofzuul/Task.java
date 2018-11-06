package worldofzuul;

import java.util.ArrayList;

/**
 *
 * @author Jonas
 */
public class Task {

    private String question;
    private String answer;
    private ArrayList<String> PossibleAnwsers;

    public Task(String question, String answer, String PossibleAnwsers) {
        this.question = question;
        this.answer = answer;

        this.PossibleAnwsers = new ArrayList<String>();
        String[] temppossible = PossibleAnwsers.split(",");
        for (String temp : temppossible) {
            this.PossibleAnwsers.add(temp);
        }

    }

    public boolean Checkanswer(String userAnswer) {
        if (answer.toLowerCase().equals(userAnswer.toLowerCase())) {

            return true;

        } else {
            return false;
        }
    }

}