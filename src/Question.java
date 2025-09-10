public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    // Getters para que a biblioteca Gson possa acessar os campos
    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
