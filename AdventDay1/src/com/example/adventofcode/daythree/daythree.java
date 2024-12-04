package com.example.adventofcode.daythree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class daythree {
    public static void main(String[] args) {
       List<String> data = readFile("AdventDay1/src/com/example/adventofcode/daythree/day3data.txt");
//       System.out.println(data);
//       partOne(data);
       partTwo(data);
    }

    public static int partOne(List<String> data){
        int total = 0;
        List<String> parts = new ArrayList<>();
        for (String line : data) {
            Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
            Matcher match = pattern.matcher(line);
            while (match.find()) {
                parts.add(match.group());
            }
//            System.out.println(parts);
        }

        for (String part : parts) {
//            System.out.println(part);
            total += multiply(part);
        }
        System.out.print("Part 1 total: ");
        System.out.println(total);

        return total;
    }

    private static int multiply(String part) {
        int comma = part.indexOf(",");
        int closing = part.indexOf(")");
        int first = Integer.parseInt(part.substring(4, comma));
        int second = Integer.parseInt(part.substring(comma+1, closing));
        return (first * second);
    }

    public static int partTwo(List<String> data) {
        int total = 0;
        List<String> beg = new ArrayList<>();

        for (String line : data) {
            // find the mul instructions before any do/do not
            Pattern patternBeg = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");
            Matcher match1 = patternBeg.matcher(line);
            while (match1.find()) {
                beg.add(match1.group());
            }
//            System.out.println(beg);
        }

        boolean enabled = true;
        for (String item : beg) {
            if (enabled && item.contains("mul")) {
                total += multiply(item);
            }
            if (item.matches("don't\\(\\)")) {
                enabled = false;
            }
            if (item.matches("do\\(\\)")) {
                enabled = true;
            }
        }
        System.out.print("Part 2 total: ");
        System.out.println(total);
        return total;
    }
    public static List<String> readFile(String file) {
        List<String> data = new ArrayList<>();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                try {
                    while (myReader.hasNext()) {
                        String col = myReader.nextLine();
                        data.add(col);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("An error occured");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return data;
    }

}
