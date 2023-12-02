import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main01 {
    public static void main(String[] args) throws IOException {
        System.out.println("Answer for part 01: " + part01());
        System.out.println("Answer for part 02: " + part02());
    }

    private static int part01() throws java.io.IOException {
        int sum = 0;
        try (
                FileReader in = new FileReader(getInputFile("input01.txt"));
                BufferedReader br = new BufferedReader(in)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                int l = 0, r = line.length() - 1;
                while (r >= l) {
                    char a = line.charAt(l), b = line.charAt(r);
                    if (!Character.isDigit(a)) l++;
                    else if (!Character.isDigit(b)) r--;
                    else {
                        sum += Integer.parseInt("" + a + b);
                        break;
                    }
                }
            }
        }

        return sum;
    }

    private static int part02() throws java.io.IOException {
        Map<String, Integer> numbers = Map.of(
                "one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9
        );

        int sum = 0;
        List<String> lines;
        try (
                FileReader in = new FileReader(getInputFile("input02.txt"));
                BufferedReader br = new BufferedReader(in)
        ) {
            lines = br.lines().toList();
        }

//        for (String line : lines) {
//            System.out.println(
//                    numbers.forEach((k, v) -> {
//
//                    });
//            );
//        }

        return sum;
    }

    private static File getInputFile(String filename) {
        String dir = Main01.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return new File(dir, filename);
    }
}
