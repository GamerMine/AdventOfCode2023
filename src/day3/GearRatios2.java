package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class GearRatios2 {
    static LinkedList<Gear> alreadyTaken = new LinkedList<>();
    static int nbGears = 0;

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        Scanner file = new Scanner(new File(Objects.requireNonNull(GearRatios2.class.getResource("input.txt")).toURI()));
        ArrayList<String> fileEntries = new ArrayList<>();
        int sum = 0;

        while (file.hasNext()) {
            fileEntries.add(file.next());
        }

        for (int y = 0; y < fileEntries.size(); y++) {
            String line = fileEntries.get(y);
            for (int x = 0; x < line.length(); x++) {
                char currentChar = line.charAt(x);
                if (currentChar == '*') {
                    nbGears = 0;
                    if (Character.isDigit(line.charAt(x - 1))) { // Gauche
                        retrieveGear(x - 1, line);
                    }
                    if (Character.isDigit(fileEntries.get(y - 1).charAt(x - 1))) { // Gauche - Haut
                        retrieveGear(x - 1, fileEntries.get(y - 1));
                    }
                    if (Character.isDigit(fileEntries.get(y - 1).charAt(x))) { // Haut
                        retrieveGear(x, fileEntries.get(y - 1));
                    }
                    if (Character.isDigit(fileEntries.get(y - 1).charAt(x + 1))) { // Droite - Haut
                        retrieveGear(x + 1, fileEntries.get(y - 1));
                    }
                    if (Character.isDigit(line.charAt(x + 1))) { // Droite
                        retrieveGear(x + 1, line);
                    }
                    if (Character.isDigit(fileEntries.get(y + 1).charAt(x + 1))) { // Droite - Bas
                        retrieveGear(x + 1, fileEntries.get(y + 1));
                    }
                    if (Character.isDigit(fileEntries.get(y + 1).charAt(x))) { // Bas
                        retrieveGear(x, fileEntries.get(y + 1));
                    }
                    if (Character.isDigit(fileEntries.get(y + 1).charAt(x - 1))) { // Gauche - Bas
                        retrieveGear(x - 1, fileEntries.get(y + 1));
                    }
                    System.out.println(oldNbGear);
                }
            }
        }

        for (Gear gear : alreadyTaken) {
            System.out.printf("Gear: %d\n", gear.value);
        }

        for (int i = 0; i < alreadyTaken.size() - 1; i+=2) {
            System.out.printf("Gear 1: %d\t Gear 2: %d\n", alreadyTaken.get(i).value, alreadyTaken.get(i + 1).value);
            sum += alreadyTaken.get(i).value * alreadyTaken.get(i + 1).value;
        }

        System.out.println(sum);
    }

    static int oldNbGear = 0;
    public static void retrieveGear(int x, String line) {
        int startX;
        int endX;
        int currentX = x;

        if (oldNbGear > nbGears && oldNbGear == 1) {
            alreadyTaken.removeLast();
        }

        if (nbGears > 2) {
            alreadyTaken.removeLast();
            alreadyTaken.removeLast();
            return;
        }

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
                return;
            }
        }

        nbGears++;
        oldNbGear = nbGears;
        alreadyTaken.add(new Gear(startX + 1, endX, line, Integer.parseInt(line.substring(startX + 1, endX))));
    }

    static class Gear {
        public Gear(int startIndex, int endIndex, String line, int value) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.line = line;
            this.value = value;
        }

        public int startIndex;
        public int endIndex;
        public String line;
        public int value;
    }
}
