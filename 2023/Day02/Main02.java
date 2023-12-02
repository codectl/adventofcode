import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main02 {
    private static final int RED = 12;
    private static final int GREEN = 13;
    private static final int BLUE = 14;

    public static void main(String[] args) throws IOException {
        System.out.println("Answer for part 01: " + part01());
        System.out.println("Answer for part 02: " + part02());
    }

    private static int part01() throws IOException {
        int sum = 0;
        try (
                FileReader in = new FileReader(getInputFile());
                BufferedReader br = new BufferedReader(in)
        ) {
            for (String line : br.lines().toList()) {
                int gameId = Integer.parseInt(Pattern.compile("Game (\\d+):")
                        .matcher(line)
                        .results()
                        .map(m -> m.group(1))
                        .findFirst()
                        .orElse("0"));

                String gameSet = line.split(":")[1];
                if (Stream.of(gameSet.split(";")).allMatch(set -> {
                    int red = processColorValue(set, "red");
                    int green = processColorValue(set, "green");
                    int blue = processColorValue(set, "blue");
                    return (red <= RED && green <= GREEN && blue <= BLUE);
                }))
                    sum += gameId;
            }
        }

        return sum;
    }

    private static int part02() throws IOException {
        int sum = 0;
        try (
                FileReader in = new FileReader(getInputFile());
                BufferedReader br = new BufferedReader(in)
        ) {
            for (String line : br.lines().toList()) {
                String gameSet = line.split(":")[1];
                int red = 0, green = 0, blue = 0;
                for (String set : gameSet.split(";")) {
                    red = Math.max(red, processColorValue(set, "red"));
                    green = Math.max(green, processColorValue(set, "green"));
                    blue = Math.max(blue, processColorValue(set, "blue"));
                }
                sum += red * green * blue;
            }
        }

        return sum;
    }

    private static File getInputFile() {
        String dir = Main02.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return new File(dir, "input.txt");
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
