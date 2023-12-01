import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.printf("Answer for part 01: %s", part01());
    }

    private static int part01 () throws java.io.IOException {
        int sum = 0;
        try (
                FileReader in = new FileReader(getInputFile());
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

    private static File getInputFile() {
        String dir = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return new File(dir, "input.txt");
    }
}
