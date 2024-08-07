
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    // Array to store questions, options, and correct answers
    private static String[][] Quizquestions = {
            {"What is the capital of india?", "A. Dholakpur", "B. Uttrakhand", "C. Jaipur", "D. Delhi", "D"},
            {"Which god is known as kesrinandan ?", "A. Shiv ", "B. Kartike ", "C. Hanuman ", "D. Sanidev", "C"},
            {"which forest is known as lungs of earth?", "A.sundarban ", "B.Valdivian ", "C.Amazon ", "D.congo ", "C"}
    };

    private static int Your_score = 0;
    private static Scanner sc = new Scanner(System.in);
    private static Timer timer = new Timer();
    private static boolean answered = false;
    private static String AnswerofUser = "";

    public static void main(String[] args) {
        for (int i = 0; i < Quizquestions.length; i++) {
            displayQuizQuestion(i);
            startTheTimer();
            getAnswerByUser();
            evaluateAnswer(i);
        }
        displayQuizResultS();
    }

    private static void displayQuizQuestion(int questionIndex) {
        System.out.println("Question " + (questionIndex + 1) + ": " + Quizquestions[questionIndex][0]);
        for (int i = 1; i <= 4; i++) {
            System.out.println(Quizquestions[questionIndex][i]);
        }
        answered = false;
    }

    private static void startTheTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up!");
                    AnswerofUser = "";
                    answered = true;
                }
            }
        }, 10000) ;// 10 seconds timer
    }

    private static void getAnswerByUser() {
        while (!answered) {
            System.out.print("Enter your answer (A/B/C/D): ");
            AnswerofUser = sc.nextLine().toUpperCase();
            if ("A".equals(AnswerofUser) ||"B" .equals(AnswerofUser) || "C".equals(AnswerofUser) || "D".equals(AnswerofUser)) {
                answered = true;
            }else {
                System.out.println("Invalid input. Please enter A, B, C, or D.");
            }
        }
    }

    private static void evaluateAnswer(int questionIndex) {
        timer.cancel();
        timer = new Timer(); // Reset timer for the next question
        if (AnswerofUser.equals(Quizquestions[questionIndex][5])) {
            Your_score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect! The correct answer was " + Quizquestions[questionIndex][5]);
        }
    }

    private static void  displayQuizResultS() {
        System.out.println("\nQuiz Finished!");
        System.out.println("Your score: " + Your_score + "/" + Quizquestions.length);
        System.out.println("Summary:");
        for (int i = 0; i < Quizquestions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + Quizquestions[i][0]);
            System.out.println("Your answer: " + (AnswerofUser.equals("") ? "No answer" : AnswerofUser));
            System.out.println("Correct answer: " + Quizquestions[i][5]);
            System.out.println();
        }
    }
}