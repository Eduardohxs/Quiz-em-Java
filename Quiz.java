
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;
    private int score = 0;

    public void loadQuestions(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Question[] questionArray = new Gson().fromJson(reader, Question[].class);
            this.questions = Arrays.asList(questionArray);
            Collections.shuffle(this.questions); // Embaralha as perguntas
        } catch (IOException e) {
            System.err.println("Erro ao carregar as perguntas do arquivo: " + e.getMessage());
            // Encerra o programa se não conseguir carregar as perguntas
            System.exit(1); 
        }
    }

    public void start() {
        if (questions == null || questions.isEmpty()) {
            System.out.println("Nenhuma pergunta carregada. O quiz não pode começar.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            System.out.println("\n----------------------------------------");
            System.out.println("Pergunta " + (i + 1) + ": " + currentQuestion.getQuestionText());
            System.out.println("----------------------------------------");

            String[] options = currentQuestion.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            System.out.print("\nSua resposta (1-" + options.length + "): ");
            int userAnswer = -1;
            try {
                userAnswer = Integer.parseInt(scanner.nextLine());
                if (userAnswer < 1 || userAnswer > options.length) {
                    System.out.println("Opção inválida. A pergunta será considerada como incorreta.");
                    userAnswer = -1; // Resposta inválida
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número. A pergunta será considerada como incorreta.");
                userAnswer = -1; // Resposta inválida
            }

            // O índice da resposta correta no array é `correctAnswerIndex`, 
            // mas a opção para o usuário é `correctAnswerIndex + 1`
            if (userAnswer == currentQuestion.getCorrectAnswerIndex() + 1) {
                System.out.println("Correto!");
                score++;
            } else {
                System.out.println("Incorreto. A resposta correta era: " + (currentQuestion.getCorrectAnswerIndex() + 1) + ". " + options[currentQuestion.getCorrectAnswerIndex()]);
            }
        }

        showResult(scanner);
        scanner.close();
    }

    private void showResult(Scanner scanner) {
        System.out.println("\n========================================");
        System.out.println("Quiz Finalizado!");
        System.out.println("Sua pontuação final: " + score + " de " + questions.size());
        
        double percentage = ((double) score / questions.size()) * 100;
        System.out.printf("Aproveitamento: %.2f%%\n", percentage);
        System.out.println("========================================");

    }
}
