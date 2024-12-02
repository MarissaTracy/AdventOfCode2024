package com.example.adventofcode.dayone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class dayone {


    public static void main(String[] args) {
        List<List<Integer>> data = readFile("Advent/src/com/example/adventofcode/day2data.txt");
        List<Integer> col1 = data.get(0);
        List<Integer> col2 = data.get(1);
        partTwo(col1, col2);
        partOne(col1, col2);
    }

    // Should return 11 in testing
     public static void partOne(List<Integer> col1, List<Integer> col2) {
        // Grab the size of the array so it doesn't change
        int size = col1.size();

        Integer diff = 0;
        for(int i=0; i<size; i++) {
            Integer col1min = getSmallestInt(col1);
            Integer col2min = getSmallestInt(col2);

            diff += abs(col1min-col2min);

            col1.remove(col1min);
            col2.remove(col2min);
        }

        System.out.print("PART 1 ANSWER: ");
        System.out.println(diff);
    }

    public static void partTwo(List<Integer> col1, List<Integer> col2){
        int total = 0;
        for (Integer num : col1) {
            int occurences = 0;
            for (Integer num2 : col2) {
                if( num.equals(num2)) {
                    occurences += 1;
                }
            }
            int similarity = occurences*num;
            total += similarity;
        }
        System.out.print("PART 2 ANSWER: ");
        System.out.println(total);
    }

    public static Integer getSmallestInt(List<Integer> col) {
        Integer smallest = col.get(0);
        for(Integer num : col) {
            if (num < smallest) {
                smallest = num;
            }
        }
        return smallest;
    }

    public static List<List<Integer>> readFile(String file) {
        List<List<Integer>> values = new ArrayList<List<Integer>>();
        List<Integer> col1 = new ArrayList<Integer>();
        List<Integer> col2 = new ArrayList<Integer>();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                try {
                    while (myReader.hasNext()) {
                        String col = myReader.nextLine();
                        String[] nums = col.split("\\s+");
                        col1.add(Integer.valueOf(nums[0]));
                        col2.add(Integer.valueOf(nums[1]));
                        }
                } catch (NumberFormatException ex) {
                    System.out.println("An error occured");
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        values.add(col1);
        values.add(col2);
        return values;
    }

}
