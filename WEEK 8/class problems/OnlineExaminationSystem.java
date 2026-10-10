import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OnlineExaminationSystem {

    static abstract class Question {

        private final int number;
        private final String text;
        private final int points;

        Question(int number, String text, int points) {
            this.number = number;
            this.text = text;
            this.points = points;
        }

        int getNumber() {
            return number;
        }

        String getText() {
            return text;
        }

        int getPoints() {
            return points;
        }

        // each question type decides for itself whether an answer is correct
        abstract boolean isCorrect(String answer);

        int evaluate(String answer) {
            return isCorrect(answer) ? points : 0;
        }
    }

    static class MultipleChoiceQuestion extends Question {

        private final String correctOption;

        MultipleChoiceQuestion(int number, String text, int points, String correctOption) {
            super(number, text, points);
            this.correctOption = correctOption;
        }

        @Override
        boolean isCorrect(String answer) {
            return correctOption.equalsIgnoreCase(answer);
        }
    }

    static class TrueFalseQuestion extends Question {

        private final boolean correctAnswer;

        TrueFalseQuestion(int number, String text, int points, boolean correctAnswer) {
            super(number, text, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        boolean isCorrect(String answer) {
            return String.valueOf(correctAnswer).equalsIgnoreCase(answer);
        }
    }

    static class ShortAnswerQuestion extends Question {

        private final String expected;

        ShortAnswerQuestion(int number, String text, int points, String expected) {
            super(number, text, points);
            this.expected = expected;
        }

        @Override
        boolean isCorrect(String answer) {
            return answer != null && expected.trim().equalsIgnoreCase(answer.trim());
        }
    }

    static class Student {

        private final String name;

        Student(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class Examination {

        private final String title;
        private final List<Question> questions = new ArrayList<>();
        private final Set<Student> submittedBy = new HashSet<>();

        Examination(String title) {
            this.title = title;
        }

        String getTitle() {
            return title;
        }

        void addQuestion(Question question) {
            questions.add(question);
        }

        List<Question> getQuestions() {
            return questions;
        }

        int getTotalPoints() {
            int total = 0;
            for (Question q : questions) {
                total += q.getPoints();
            }
            return total;
        }

        Question findQuestion(int number) {
            for (Question q : questions) {
                if (q.getNumber() == number) {
                    return q;
                }
            }
            return null;
        }

        boolean hasSubmitted(Student student) {
            return submittedBy.contains(student);
        }

        void markSubmitted(Student student) {
            submittedBy.add(student);
        }
    }

    enum AttemptState {
        IN_PROGRESS, SUBMITTED
    }

    static class Attempt {

        private final Student student;
        private final Examination exam;
        private final Map<Integer, String> answers = new HashMap<>();
        private AttemptState state = AttemptState.IN_PROGRESS;

        private Attempt(Student student, Examination exam) {
            this.student = student;
            this.exam = exam;
        }

        static Attempt start(Student student, Examination exam) {
            if (exam.hasSubmitted(student)) {
                System.out.println(student.getName() + " has already submitted " + exam.getTitle() + ".");
                return null;
            }
            System.out.println(exam.getTitle() + " started by " + student.getName() + ".");
            return new Attempt(student, exam);
        }

        void answer(int questionNumber, String answer) {
            if (state == AttemptState.SUBMITTED) {
                System.out.println("Cannot change answers for a submitted examination.");
                return;
            }
            if (exam.findQuestion(questionNumber) == null) {
                System.out.println("Question " + questionNumber + " does not exist.");
                return;
            }
            answers.put(questionNumber, answer);
            System.out.println("Answer recorded for Question " + questionNumber + ".");
        }

        void submit() {
            if (state == AttemptState.SUBMITTED) {
                System.out.println("Examination already submitted.");
                return;
            }
            state = AttemptState.SUBMITTED;
            exam.markSubmitted(student);
            System.out.println(exam.getTitle() + " submitted by " + student.getName() + ".");
            printResult();
        }

        private void printResult() {
            StringBuilder sb = new StringBuilder("Result: ");
            int score = 0;
            for (Question q : exam.getQuestions()) {
                int earned = q.evaluate(answers.get(q.getNumber()));
                score += earned;
                if (sb.length() > "Result: ".length()) {
                    sb.append(", ");
                }
                sb.append("Question ").append(q.getNumber()).append(": ")
                        .append(earned > 0 ? "Correct" : "Incorrect")
                        .append(" (").append(earned).append(" points)");
            }
            sb.append(". Total score: ").append(score).append("/").append(exam.getTotalPoints()).append(".");
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        Examination examA = new Examination("Exam A");
        examA.addQuestion(new MultipleChoiceQuestion(1, "Which option is the capital of France?", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion(2, "Java is a purely procedural language.", 5, false));

        Student student1 = new Student("Student 1");

        Attempt attempt = Attempt.start(student1, examA);
        attempt.answer(1, "C");
        attempt.answer(2, "True");
        attempt.submit();
        attempt.answer(1, "A");
    }
}
