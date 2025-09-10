public class Main {
    public static void main(String[] args) {
        // Cria uma nova instância do Quiz
        Quiz quiz = new Quiz();

        // Carrega as perguntas do arquivo JSON.
        // O arquivo deve estar na raiz do projeto ou o caminho deve ser ajustado.
        quiz.loadQuestions("questions.json");

        // Inicia o quiz
        quiz.start();
    }
}