package com.example.adventofcode.daysix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class daysix {
//between 1944 and 1958
    // not 1956, 1957, 1944
    //1946
    public static List<List<String>> visited;

    public static void main(String[] args) {
        visited = readFile("AdventDay1/src/com/example/adventofcode/daysix/day6data.txt");
        List<List<String>> data = readFile("AdventDay1/src/com/example/adventofcode/daysix/day6data.txt");
        List<Integer> start = findStart(data);
        int startX = start.get(0);
        int startY = start.get(1);
       System.out.println(addBarrier(data));
//        searchUp(startX, startY, data);
//        System.out.println(visited);
    }
    public static List<Integer> findStart(List<List<String>> map) {
        List<Integer> start = new ArrayList<>();
        for (int i=0; i<map.size(); i++) {
            for (int j=0; j<map.size(); j++) {
                if (map.get(i).get(j).equals("^")) {
                    start.add(i);
                    start.add(j);
                }
            }
        }
        return start;
    }
    public static int addBarrier(List<List<String>> map) {
//        placeholder.addAll(map);
        List<Integer> start = findStart(map);
        int startX = start.get(0);
        int startY = start.get(1);
        int ans = 0;
        for (int i=0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size()-1; j++ ) {
                if (i == startX && j == startY) {
                    continue;
                }
                String orig = map.get(i).get(j);
                map.get(i).set(j, "#");
                try {
                    searchUp(startX, startY, map);
                } catch (StackOverflowError e) {

//                    System.out.print("Row: ");

//                    System.out.print(i);
//                    System.out.print(" Col: ");
//                    System.out.println(j);
                    ans += 1;
                } finally {
                    map.get(i).set(j, orig);
                }
            }
        }
        return ans;
    }

    public static void searchUp( Integer x, Integer y, List<List<String>> map) {
        if (x == 0 && map.get(x).get(y).equals(".")) {
            visited.get(x).set(y, "1");
//            System.out.println("exited from the top");
        } else {
            if (map.get(x - 1).get(y).equals("#")) {
                visited.get(x).set(y, "1");
                searchRight(x, y, map);
            }
            if (!map.get(x - 1).get(y).equals("#")) {
                visited.get(x).set(y, "1");
                x -= 1;
                searchUp(x, y, map);
            }
        }
    }
    public static void searchRight(Integer x, Integer y, List<List<String>> map) {
        if (y.equals(map.get(0).size() - 1) && map.get(x).get(y).equals(".")) {
            visited.get(x).set(y, "1");
//            System.out.println("exited from the right");
        } else {
            if (map.get(x).get(y + 1).equals("#")) {
                visited.get(x).set(y, "1");
                searchDown(x, y, map);
            }
            if (!map.get(x).get(y + 1).equals("#")) {
                visited.get(x).set(y, "1");
                y += 1;
                searchRight(x, y, map);
            }
        }
    }
    public static void searchDown(Integer x, Integer y, List<List<String>> map) {
        if (x == map.size() - 1 && map.get(x).get(y).equals(".")) {
            visited.get(x).set(y, "1");
//            System.out.println("exited from the bottom");
        } else {
            if (map.get(x + 1).get(y).equals("#")) {
                visited.get(x).set(y, "1");
                searchLeft(x, y, map);
            }
            if (!map.get(x + 1).get(y).equals("#")) {
                visited.get(x).set(y, "1");
                x += 1;
                searchDown(x, y, map);
            }
        }
    }
    public static void searchLeft( Integer x, Integer y, List<List<String>> map) {
        if (y == 0 && map.get(x).get(0).equals(".")) {
            visited.get(x).set(y, "1");
//            System.out.println("exited from the left");
        } else {
            if (map.get(x).get(y - 1).equals("#")) {
                visited.get(x).set(y, "1");
                searchUp(x, y, map);
            }
            if (!map.get(x).get(y - 1).equals("#")) {
                visited.get(x).set(y, "1");
                y -= 1;
                searchLeft(x, y, map);
            }
        }
    }

    public static int countSpaces(List<List<String>> map) {
        int freq = 0;
        for (List<String> row : map) {
            freq += Collections.frequency(row, "1");
        }
        return freq;
    }
    public static List<List<String>> readFile(String file) {
        List<List<String>> values = new ArrayList<>();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                try {
                    while (myReader.hasNext()) {
                        List<String> col1 = new ArrayList<>();
                        String col = myReader.nextLine();
                        String[] x = col.split("");
                        for (int i=0; i< col.length(); i++) {
                            col1.add(x[i]);
                        }
                        values.add(col1);
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
