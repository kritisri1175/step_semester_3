import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
 * Sample input (one student per line):
 *   Asha [78, 85, 90]
 *   Ravi [88, 92, 79]
 *   Neha [65, 70, 95]
 */
public class ClassMarksGridAnalysis {

    static void run(List<String> lines) {
        List<String> names = new ArrayList<>();
        List<int[]> rows = new ArrayList<>();

        for (String line : lines) {
            int open = line.indexOf('[');
            int close = line.lastIndexOf(']');
            if (open < 0 || close < open) {
                continue;
            }
            names.add(line.substring(0, open).trim());

            String[] parts = line.substring(open + 1, close).split(",");
            int[] marks = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                marks[i] = Integer.parseInt(parts[i].trim());
            }
            rows.add(marks);
        }

        if (rows.isEmpty()) {
            return;
        }

        int[][] grid = rows.toArray(new int[0][]);
        int students = grid.length;
        int subjects = grid[0].length;

        // total for each student, and the topper (first one wins a tie)
        int[] totals = new int[students];
        int topper = 0;
        for (int i = 0; i < students; i++) {
            for (int j = 0; j < subjects; j++) {
                totals[i] += grid[i][j];
            }
            if (totals[i] > totals[topper]) {
                topper = i;
            }
        }

        // average for each subject (column)
        double[] averages = new double[subjects];
        for (int j = 0; j < subjects; j++) {
            int sum = 0;
            for (int i = 0; i < students; i++) {
                sum += grid[i][j];
            }
            averages[j] = (double) sum / students;
        }

        StringBuilder totalsLine = new StringBuilder("Totals ");
        for (int i = 0; i < students; i++) {
            if (i > 0) {
                totalsLine.append(", ");
            }
            totalsLine.append(names.get(i)).append(' ').append(totals[i]);
        }

        StringBuilder averagesLine = new StringBuilder("averages ");
        for (int j = 0; j < subjects; j++) {
            if (j > 0) {
                averagesLine.append(", ");
            }
            averagesLine.append(String.format(Locale.US, "%.2f", averages[j]));
        }

        System.out.println(totalsLine);
        System.out.println(averagesLine);
        System.out.println("topper " + names.get(topper) + " (" + totals[topper] + ")");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                lines.add(line);
            }
        }
        run(lines);
    }
}
