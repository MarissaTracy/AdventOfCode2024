package com.example.adventofcode.daythree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class daythree {
    public static void main(String[] args) {
        List<List<Integer>> data = readFile("AdventDay1/src/com/example/adventofcode/daythree/day3example.txt");
    }

    // Should return 2 in testing
//    public static List<List<Integer>> partOne(List<List<Integer>> data) {
//
//    }

    // Should return 4 in testing
//    public static void partTwo(List<List<Integer>> data) {
//
//    }

    public static List<List<Integer>> readFile(String file) {
        List<List<Integer>> values = new ArrayList<List<Integer>>();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                try {
                    while (myReader.hasNext()) {
                        List<Integer> col = new ArrayList<Integer>();
                        String column = myReader.nextLine();
                        String[] nums = column.split("\\s+");
                        for (int i=0; i< nums.length; i++) {
                            col.add(Integer.valueOf(nums[i]));
                        }
                        values.add(col);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("An error occured");
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return values;
    }

}
