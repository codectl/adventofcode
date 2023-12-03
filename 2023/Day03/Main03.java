import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main03 {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer for part 01: " + part01());
//        System.out.println("Answer for part 02: " + part02());
    }

    private static int part01() throws IOException {
        int sum = 0;
        try (
                FileReader in = new FileReader(getInputFile());
                BufferedReader br = new BufferedReader(in)
        ) {
            List<String> lines = br.lines().toList();
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    String value = matcher.group();
                    if (IntStream.range(
                                    Math.max(0, i - 1),
                                    Math.min(i + 1, lines.size() - 1) + 1)
                            .anyMatch(m -> IntStream.range(
                                            Math.max(0, matcher.start() - 1),
                                            Math.min(matcher.end(), line.length() - 1) + 1)
                                    .anyMatch(n -> {
                                        char c = lines.get(m).charAt(n);
                                        return !Character.isDigit(c) && c != '.';
                                    })
                            )
                    )
                        sum += Integer.parseInt(value);
                }
            }
        }

        return sum;
    }

    private static File getInputFile() {
        String dir = Main03.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return new File(dir, "input.txt");
    }

//    private static boolean hasAdjacentSymbol(int index, ) {
//
//    }
}
