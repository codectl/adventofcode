import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main04 {

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
            for (String line : br.lines().toList()) {
                String gameSet = line.split(":")[1];
                String[] parts = gameSet.trim().split("\\|");
                List<Integer> winners = Stream.of(parts[0].split(" "))
                        .filter(Predicate.not(String::isEmpty))
                        .map(Integer::parseInt)
                        .toList();
                List<Integer> bets = Stream.of(parts[1].split(" "))
                        .filter(Predicate.not(String::isEmpty))
                        .map(Integer::parseInt)
                        .toList();
                List<Integer> intersect = winners.stream()
                        .distinct()
                        .filter(bets::contains)
                        .toList();

                sum += (int) Math.pow(2, intersect.size() - 1);
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
                String[] parts = gameSet.trim().split("\\|");
                List<Integer> winners = Stream.of(parts[0].split(" "))
                        .filter(Predicate.not(String::isEmpty))
                        .map(Integer::parseInt)
                        .toList();
                List<Integer> bets = Stream.of(parts[1].split(" "))
                        .filter(Predicate.not(String::isEmpty))
                        .map(Integer::parseInt)
                        .toList();
                List<Integer> intersect = winners.stream()
                        .distinct()
                        .filter(bets::contains)
                        .toList();

                sum += (int) Math.pow(2, intersect.size() - 1);
            }
        }

        return sum;
    }

    private static File getInputFile() {
        String dir = Main04.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return new File(dir, "input.txt");
    }
}
