package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;

public class Scratchcards {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        Scanner file = new Scanner(new File(Objects.requireNonNull(Scratchcards.class.getResource("input.txt")).toURI()));
        int sum = 0;

        while (file.hasNext()) {
            String line = file.nextLine().split(":")[1];
            String[] winnerNumbers = line.split("\\|")[0].split(" ");
            String[] otherNumbers = line.split("\\|")[1].split(" ");
            int points = 0;

            for (String number : otherNumbers) {
                for (String winner : winnerNumbers) {
                    if (!winner.isBlank() && !number.isBlank() && winner.strip().equals(number.strip())) {
                        System.out.printf("%s equals to %s\n", number, winner);
                        if (points == 0) points = 1;
                        else points *= 2;
                        break;
                    }
                }
            }

            sum += points;
        }

        System.out.println(sum);
    }
}
