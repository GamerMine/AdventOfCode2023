package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class CubeConundrum2 {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        Scanner file = new Scanner(new File(Objects.requireNonNull(CubeConundrum2.class.getResource("input.txt")).toURI()));
        file.useDelimiter("\n");
        int sum = 0;

        while (file.hasNext()) {
            String line = file.next();
            String[] records = line.split(":")[1].split(";");
            ArrayList<Integer> red = new ArrayList<>();
            ArrayList<Integer> green = new ArrayList<>();
            ArrayList<Integer> blue = new ArrayList<>();

            for (String record: records) {
                String[] cubes = record.split(",");
                for (String cube: cubes) {
                    int number = Integer.parseInt(cube.strip().split(" ")[0]);
                    if (cube.contains("blue")) {
                        blue.add(number);
                    } else if (cube.contains("red")) {
                        red.add(number);
                    } else if (cube.contains("green")) {
                        green.add(number);
                    }
                }
            }

            Collections.sort(red);
            Collections.sort(green);
            Collections.sort(blue);

            sum += red.get(red.size() - 1) * green.get(green.size() - 1) * blue.get(blue.size() - 1);
        }

        System.out.println(sum);
    }
}
