package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GearRatios {
    static ArrayList<Gear> alreadyTaken = new ArrayList<>();

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        Scanner file = new Scanner(new File(Objects.requireNonNull(GearRatios.class.getResource("input.txt")).toURI()));
        ArrayList<String> fileEntries = new ArrayList<>();
        int sum = 0;

        while (file.hasNext()) {
            fileEntries.add(file.next());
        }

        for (int y = 0; y < fileEntries.size(); y++) {
            String line = fileEntries.get(y);
            for (int x = 0; x < line.length(); x++) {
                char currentChar = line.charAt(x);
                if (!Character.isDigit(currentChar) && currentChar != '.') {
                    if (Character.isDigit(line.charAt(x - 1))) { // Gauche
                        sum += retrieveNumber(x - 1, line);
                    }
                    if (Character.isDigit(fileEntries.get(y - 1).charAt(x - 1))) { // Gauche - Haut
                        sum += retrieveNumber(x - 1, fileEntries.get(y - 1));
                    }
                    if (Character.isDigit(fileEntries.get(y - 1).charAt(x))) { // Haut
                        sum += retrieveNumber(x, fileEntries.get(y - 1));
                    }
                    if (Character.isDigit(fileEntries.get(y - 1).charAt(x + 1))) { // Droite - Haut
                        sum += retrieveNumber(x + 1, fileEntries.get(y - 1));
                    }
                    if (Character.isDigit(line.charAt(x + 1))) { // Droite
                        sum += retrieveNumber(x + 1, line);
                    }
                    if (Character.isDigit(fileEntries.get(y + 1).charAt(x + 1))) { // Droite - Bas
                        sum += retrieveNumber(x + 1, fileEntries.get(y + 1));
                    }
                    if (Character.isDigit(fileEntries.get(y + 1).charAt(x))) { // Bas
                        sum += retrieveNumber(x, fileEntries.get(y + 1));
                    }
                    if (Character.isDigit(fileEntries.get(y + 1).charAt(x - 1))) { // Gauche - Bas
                        sum += retrieveNumber(x - 1, fileEntries.get(y + 1));
                    }
                }
            }
        }

        System.out.println(sum);
    }

    public static int retrieveNumber(int x, String line) {
        int startX;
        int endX;
        int currentX = x;

        while (Character.isDigit(line.charAt(currentX))) {
            currentX--;
            if (currentX == -1) break;
        }

        startX = currentX;
        currentX = x;

        while (Character.isDigit(line.charAt(currentX))) {
            currentX++;
            if (currentX >= line.length()) break;
        }

        endX = currentX;

        for (Gear gear : alreadyTaken) {
            if (line.equals(gear.line) && gear.startIndex == startX + 1 && gear.endIndex == endX) {
                return 0;
            }
        }

        alreadyTaken.add(new Gear(startX + 1, endX, line));
        return Integer.parseInt(line.substring(startX + 1, endX));
    }

    static class Gear {
        public Gear(int startIndex, int endIndex, String line) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.line = line;
        }

        public int startIndex;
        public int endIndex;
        public String line;
    }
}
