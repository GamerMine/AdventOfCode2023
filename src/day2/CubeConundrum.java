package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;

public class CubeConundrum {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        Scanner file = new Scanner(new File(Objects.requireNonNull(CubeConundrum.class.getResource("input.txt")).toURI()));
        file.useDelimiter("\n");
        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;
        int sumID = 0;

        while (file.hasNext()) {
            String line = file.next();
            String[] records = line.split(":")[1].split(";");
            boolean isCorrect = true;

            for (String record: records) {
                String[] cubes = record.split(",");
                for (String cube: cubes) {
                    int number = Integer.parseInt(cube.strip().split(" ")[0]);
                    if (cube.contains("blue") && number > maxBlue) {
                        isCorrect = false;
                        break;
                    } else if (cube.contains("red") && number > maxRed) {
                        isCorrect = false;
                        break;
                    } else if (cube.contains("green") && number > maxGreen) {
                        isCorrect = false;
                        break;
                    }
                }

                if (!isCorrect) break;
            }

            if (isCorrect) sumID += Integer.parseInt(line.split(":")[0].split(" ")[1]);
        }

        System.out.println(sumID);
    }
}
