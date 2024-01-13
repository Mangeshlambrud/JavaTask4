import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOption;

    public QuizQuestion(String question, List<String> options, int correctOption){
        this.question  = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestions(){
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

public class QuizAppl {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer tr;
    private Scanner sc;

    public QuizAppl() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        score = 0;
        tr = new Timer();
        sc = new Scanner(System.in);
    }

    public void addQuestion(QuizQuestion question) {
        questions.add(question);
    }

    public void starQuiz() {
        System.out.println("Welcome to the Quiz! ");
        tr.schedule(new QuizTimerTask(), 3, 700000);

        while (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
            displayQuestion(currentQuestion);

            int userChoice = getUserChoice();
            if (userChoice == currentQuestion.getCorrectOption()) {
                System.out.println("Correct! ");
                score++;
            }
            else {
                System.out.println("Incorrect. ");
            }

            currentQuestionIndex++;
        }
        displayResult();
    }
    private void displayQuestion(QuizQuestion question) {
        System.out.println(question.getQuestions());
        List<String> options = question.getOptions();
        for(int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private int getUserChoice() {
        System.out.println("Enter your choice (1- " + questions.get(currentQuestionIndex).getOptions().size() + "): ");
        return sc.nextInt();
    }

    private void displayResult() {
        System.out.println("Quiz Completed! ");
        System.out.println("Your score: " + score + "/" + questions.size());
    }

    private class QuizTimerTask extends TimerTask {
        private int secondsLeft = 10;

        @Override
        public void run() {
            if (secondsLeft > 0) {
                System.out.println("Time left: " + secondsLeft + " seconds");
                secondsLeft--;
            }
            else{
                System.out.println("Times's up! ");
                currentQuestionIndex++;
            }
        }
    }

    public static void main(String[] args) {
        QuizAppl quizApp = new QuizAppl();
        
        quizApp.addQuestion(new QuizQuestion("1. What is 2 + 2?", List.of("3", "4", "5"), 2));
        quizApp.addQuestion(new QuizQuestion("2. What is the capital of france? ",List.of("berlin", "madrid", "paris"), 3));
        quizApp.addQuestion(new QuizQuestion("3. What is the largest planet in our solar system? ",List.of("earth", "mersh", "sun"), 1));
        quizApp.addQuestion(new QuizQuestion("4. Which gas do plants absorb from the atmosphere? ", List.of("oxygen", "carbon", "nitrogen"), 3));
        quizApp.addQuestion(new QuizQuestion("5. Who wrote the play 'romeo and juliet'? ", List.of("charles", "dickens", "william"), 3));
        quizApp.addQuestion(new QuizQuestion("6. which country is known as the land of the rising sun? ", List.of("china", "japan", "india"), 3));
        quizApp.addQuestion(new QuizQuestion("7. What is the symbol for gold? ", List.of("au", "ag", "fe"), 1));
        quizApp.addQuestion(new QuizQuestion("8. Which planet is known as the red planet? ", List.of("venus", "mars", "jupiter"), 2));
        quizApp.addQuestion(new QuizQuestion("9. What is the national flower of japan? ", List.of("tulip", "rose", "cherry"), 3));
        quizApp.addQuestion(new QuizQuestion("10. Who painted the mona lisa?", List.of("livonardo da vinchi", "vinecent van gogh", "mashe th dude"), 1));

        quizApp.starQuiz();

    }
}
