import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main03 {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer for part 01: " + part01());
        System.out.println("Answer for part 02: " + part02());
    }

    private static int part01() throws IOException {
        int sum = 0;
        try (FileReader in = new FileReader(getInputFile()); BufferedReader br = new BufferedReader(in)) {
            List<String> lines = br.lines().toList();
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    String value = matcher.group();
                    if (IntStream.range(Math.max(0, i - 1), Math.min(i + 1, lines.size() - 1) + 1).anyMatch(m -> IntStream.range(Math.max(0, matcher.start() - 1), Math.min(matcher.end(), line.length() - 1) + 1).anyMatch(n -> {
                        char c = lines.get(m).charAt(n);
                        return !Character.isDigit(c) && c != '.';
                    }))) sum += Integer.parseInt(value);
                }
            }
        }
        return sum;
    }

    private static int part02() throws IOException {
        int sum = 0;
        try (FileReader in = new FileReader(getInputFile()); BufferedReader br = new BufferedReader(in)) {
            List<String> lines = br.lines().toList();
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                Pattern pattern = Pattern.compile("(?![\\d.]).");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    List<Integer> nums = IntStream.range(
                                    Math.max(0, i - 1),
                                    Math.min(i + 1, lines.size() - 1) + 1)
                            .mapToObj(m -> IntStream.range(
                                            Math.max(0, matcher.start() - 1),
                                            Math.min(matcher.end(), line.length() - 1) + 1)
                                    .mapToObj(n -> {
                                        char c = lines.get(m).charAt(n);
                                        if (Character.isDigit(c)) return completeNumber(lines.get(m), n);
                                        return String.valueOf(c);
                                    })
                                    .reduce(".", (a, b) -> {
                                        char x = a.charAt(a.length() - 1);
                                        char y = b.charAt(b.length() - 1);
                                        if (Character.isDigit(x) && Character.isDigit(y))
                                            return a;
                                        return a + b;
                                    }))
                            .map(s -> s.replaceAll("(?![\\d]).", ""))
                            .filter(Predicate.not(String::isEmpty))
                            .map(Integer::parseInt)
                            .toList();
                    sum += nums.size() > 1 ? nums.stream().reduce(1, (a, b) -> a * b) : 0;
                }
            }
        }

        return sum;
    }

    private static String completeNumber(String line, int index) {
        int l = index - 1, r = index + 1;
        StringBuilder num = new StringBuilder(String.valueOf(line.charAt(index)));
        do {
            char x = line.charAt(l);
            char y = line.charAt(r);
            if (Character.isDigit(x)) {
                num.insert(0, x);
                l--;
            } else if (Character.isDigit(y)) {
                num.append(y);
                r++;
            } else break;
        } while (l >= 0 && r < line.length());
        return num.toString();
    }

    private static boolean isNumber(String s) {
        try {
            Integer.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static File getInputFile() {
        String dir = Main03.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return new File(dir, "input.txt");
    }
}
