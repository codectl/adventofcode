import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Main02 {
    private static final int RED = 12;
    private static final int GREEN = 13;
    private static final int BLUE = 14;

    public static void main(String[] args) throws IOException {
        System.out.println("Answer for part 01: " + part01());
        // System.out.println("Answer for part 02: " + part02());
    }

    private static int part01() throws IOException {
        int sum = 0;
        try (
                FileReader in = new FileReader(getInputFile("input01.txt"));
                BufferedReader br = new BufferedReader(in)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                int gameId = Integer.parseInt(Pattern.compile("Game (\\d+):")
                        .matcher(line)
                        .results()
                        .map(m -> m.group(1))
                        .findFirst()
                        .orElse("0"));

                if (isValidGame(line.split(":")[1]))
                    sum += gameId;
            }
        }

        return sum;
    }

//    private static int part02() {
//        return 0;
//    }

    private static File getInputFile(String filename) {
        String dir = Main02.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return new File(dir, filename);
    }

    private static boolean isValidGame(String gameSet) {
        for (String set : gameSet.split(";")) {
            int red = processColorValue(set, "red");
            int green = processColorValue(set, "green");
            int blue = processColorValue(set, "blue");
            if (red > RED || green > GREEN || blue > BLUE)
                return false;
        }

        return true;
    }

    private static int processColorValue(String set, String color) {
        return Integer.parseInt(Pattern.compile("(\\d+) " + color)
                .matcher(set)
                .results()
                .map(m -> m.group(1))
                .findFirst()
                .orElse("0"));
    }
}
