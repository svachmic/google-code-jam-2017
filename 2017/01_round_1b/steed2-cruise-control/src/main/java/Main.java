import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by michalsvacha on 22.04.17.
 */

public class Main {
    static List<TestCase> testCases = new ArrayList<TestCase>();

    public static void main(String[] args) {
        String path = "src/main/resources/" + "sample.in";
        readInput(path);
        saveOutput(path);
    }

    private static void readInput(String file) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));

            int testCasesCount = Integer.parseInt(bf.readLine());
            for (int i = 0; i < testCasesCount; i++) {
                String line = bf.readLine();
                String[] testCaseRaw = line.split(" ");

                TestCase testCase = new TestCase(Integer.parseInt(testCaseRaw[0]));
                int horsesCount = Integer.parseInt(testCaseRaw[1]);

                for (int j = 0; j < horsesCount; j++) {
                    line = bf.readLine();
                    testCaseRaw = line.split(" ");

                    Horse horse = new Horse(Integer.parseInt(testCaseRaw[0]), Integer.parseInt(testCaseRaw[1]));
                    testCase.addHorse(horse);
                }

                testCases.add(testCase);
            }
        } catch (Exception e) {
            System.out.println("Cannot read file");
            e.printStackTrace();
        }
    }

    private static void saveOutput(String file) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < testCases.size(); i++) {
            sb.append("Case #").append(i + 1).append(": ");

            TestCase testCase = testCases.get(i);
            String averageSpeed = String.format(Locale.US, "%.6f", testCase.getAverageSpeed());

            sb.append(averageSpeed);
            sb.append("\n");
        }

        try {
            PrintWriter out = new PrintWriter(file + ".out");

            out.write(sb.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
