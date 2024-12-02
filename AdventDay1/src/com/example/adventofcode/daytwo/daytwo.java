package com.example.adventofcode.daytwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class daytwo {
    static int totalSafe = 0;
    public static void main(String[] args) {
        List<List<Integer>> data = readFile("AdventDay1/src/com/example/adventofcode/daytwo/day2data.txt");
        List<List<Integer>> unsafe = partOne(data);
        partTwo(unsafe);
    }

    // Should return 2 in testing
    public static List<List<Integer>> partOne(List<List<Integer>> data) {
        List<List<Integer>> unsafe = new ArrayList<List<Integer>>();
        int total = 0;
        for (List<Integer> level : data) {
            boolean safe = true;
            if (!isAscending(level)) {
                if (!isDescending(level)) {
                    unsafe.add(level);
                    safe = false;
                }
            }
            if (safe && !isSafe(level)) {
                unsafe.add(level);
                safe = false;
            }
            if (safe) {
                total += 1;
            }
        }
        System.out.print("PART ONE ANSWER: ");
        System.out.println(total);
        totalSafe = total;

        return unsafe;
    }

    // Should return 4 in testing
    public static void partTwo(List<List<Integer>> data) {
        for (List<Integer> list : data) {
            for (int i=0; i<list.size(); i++){
                boolean safe = true;
                List<Integer> temp = new ArrayList<>(list);
                temp.remove(i);
                if (!isAscending(temp)) {
                    if (!isDescending(temp)) {
                        safe = false;
                    }
                }
                if (safe && !isSafe(temp)) {
                    safe = false;
                }
                if (safe) {
                    totalSafe += 1;
                    break;
                }
            }
        }
        System.out.print("PART TWO ANSWER: ");
        System.out.println(totalSafe);
    }

    public static boolean isAscending(List<Integer> list) {
        boolean ascending = true;
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                ascending = false;
                break;
            }
        }
        return ascending;
    }

    public static boolean isDescending(List<Integer>  list) {
        boolean descending = true;
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                descending = false;
                break;
            }
        }
        return descending;
    }

    public static boolean isSafe(List<Integer>  list){
        boolean safe = true;
        for (int i = 0; i < list.size() - 1; i++) {
            if ((abs(list.get(i) - list.get(i+1)) < 1) || (abs(list.get(i) - list.get(i+1)) > 3)) {
                safe = false;
                break;
            }
        }
        return safe;
    }

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

    static int[] stringToInt(String s) {
        String[] sa = s.split(" ");
        int[] arr = new int[sa.length];

        // parsing the String argument as a signed decimal
        // integer object and storing that integer into the array
        for (int i = 0; i < sa.length; i++) {
            arr[i] = Integer.parseInt(sa[i]);
        }
        return arr;
    }
}
