package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Trebuchet {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        Scanner file = new Scanner(new File(Objects.requireNonNull(Trebuchet.class.getResource("input.txt")).toURI()));
        int sum = 0;
        HashMap<String, String> strNumbers = new HashMap<>();
        strNumbers.put("one", "1");
        strNumbers.put("two", "2");
        strNumbers.put("three", "3");
        strNumbers.put("four", "4");
        strNumbers.put("five", "5");
        strNumbers.put("six", "6");
        strNumbers.put("seven", "7");
        strNumbers.put("eight", "8");
        strNumbers.put("nine", "9");

        while (file.hasNext()) {
            String line = file.next();
            String numbers = "";
            String finalNumber = "";

            // Gets all number in the line
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    numbers += line.charAt(i);
                } else {
                    for (String strNumber : strNumbers.keySet()) {
                        if (i + strNumber.length() - 1 < line.length() && line.substring(i, i + strNumber.length()).contains(strNumber)) {
                            numbers += strNumbers.get(strNumber);
                        }
                    }
                }
            }

            if (numbers.length() == 1) finalNumber = numbers.charAt(0) + Character.toString(numbers.charAt(0));
            if (numbers.length() == 2) finalNumber = numbers;
            if (numbers.length() > 2) finalNumber = numbers.charAt(0) + Character.toString(numbers.charAt(numbers.length() - 1));

            sum += Integer.parseInt(finalNumber);
        }

        System.out.println(sum);
    }
}
