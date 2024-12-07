package com.example.adventofcode.dayfour;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dayfour {

    public static int xmas = 0;
    public static int mas = 0;

    public static void main(String[] args) {
        List<String> data = readFile("AdventDay1/src/com/example/adventofcode/dayfour/day4data.txt");
//        partOne(data);
//        System.out.print("Part One Answer: ");
//        System.out.println(xmas);
        partTwo(data);
        System.out.print("Part Two Answer: ");
        System.out.println(mas);
    }

    public static void partOne(List<String> data){
        for (int i=0; i< data.size(); i++) {
            String row = data.get(i);
            for (int j=0; j<row.length(); j++) {
                String currChar = String.valueOf(row.charAt(j));
                if (currChar.equals("X")) {
                    searchBackwards(row, currChar, j);
                    searchForward(row, currChar, j);
                    searchUp(data, currChar, i, j);
                    searchDown(data, currChar, i, j);
                    searchNorthEast(data, currChar, i, j);
                    searchSouthEast(data, currChar,i, j);
                    searchSouthWest(data, currChar,i, j);
                    searchNorthWest(data, currChar,i, j);
                }
            }
        }
    }

    public static void partTwo(List<String> data){
        for (int i=0; i< data.size(); i++) {
            String row = data.get(i);
            for (int j=0; j<row.length(); j++) {
                String currChar = String.valueOf(row.charAt(j));
                if (currChar.equals("A")) {
                    searchAdjacent(data, i, j);
                }
            }
        }
    }

    public static void searchAdjacent(List<String> data, int i, int j) {
        // i is row, j is col
        // [i-1, j-1] [i-1, j] [i-1, j+1]
        // [i,j-1] [i, j] [i, j+1]
        // [i+1, j-1] [i+1, j] [i+1, j+1]
        String currRow = data.get(i);
        if (String.valueOf(currRow.charAt(j)).equals("A")) {
            String topLeft = ".";
            String topRight = ".";
            String bottomLeft = ".";
            String bottomRight = ".";

            if ((i > 0) && (i < data.size()-1)) {

                String topRow = data.get(i - 1);
                String bottomRow = data.get(i + 1);

                if ((j > 0) && (j < topRow.length()-1)) {

                    topLeft = String.valueOf(topRow.charAt(j - 1));
                    topRight = String.valueOf(topRow.charAt(j + 1));

                    bottomLeft = String.valueOf(bottomRow.charAt(j - 1));
                    bottomRight = String.valueOf(bottomRow.charAt(j + 1));
                }

                checkMas(topLeft, bottomLeft, topRight, bottomRight);
            }
        }
    }

    public static void checkMas(String topleft, String bottomLeft, String topRight, String bottomRight) {
        switch (topleft) {
            case "M":
                checkM(bottomLeft, topRight, bottomRight);
                break;
            case "S":
                checkS(bottomLeft, topRight, bottomRight);
                break;
            default:
                break;
        }
    }

    public static void checkM(String bottomLeft, String topRight, String bottomRight) {
        switch (topRight) {
            case "M":
                if (bottomLeft.equals("S") && bottomRight.equals("S")) {
                    mas += 1;
                }
                break;
            case "S":
                if (bottomLeft.equals("M") && bottomRight.equals("S")) {
                    mas += 1;
                }
                break;
        }
    }

    public static void checkS(String bottomLeft, String topRight, String bottomRight) {
        switch (topRight) {
            case "M":
                if (bottomLeft.equals("S") && bottomRight.equals("M")) {
                    mas += 1;
                }
                break;
            case "S":
                if (bottomLeft.equals("M") && bottomRight.equals("M")) {
                    mas += 1;
                }
                break;
        }
    }

    public static void searchBackwards(String line, String curr, int j) {
        switch (curr) {
            case "X":
                if (j > 2) {
                    if (String.valueOf(line.charAt(j - 1)).equals("M")) {
                        searchBackwards(line, "M", j - 1);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case "M":
                if (j > 1) {
                    if (String.valueOf(line.charAt(j-1)).equals("A")) {
                        searchBackwards(line, "A", j-1);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case "A":
                if (j > 0) {
                    if (String.valueOf(line.charAt(j-1)).equals("S")) {
//                        System.out.print("Found XMAS backwards in row: ");
//                        System.out.println(line);
                        xmas += 1;
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
        }
    }

    public static void searchForward(String line, String curr, int j) {
        switch (curr) {
            case "X":
                if (j < line.length()-2) {
                    if (String.valueOf(line.charAt(j + 1)).equals("M")) {
                        searchForward(line, "M", j + 1);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case "M":
                if (j < line.length()-1) {
                    if (String.valueOf(line.charAt(j + 1)).equals("A")) {
                        searchForward(line, "A", j + 1);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case "A":
                if (j < line.length()-1) {
                    if (String.valueOf(line.charAt(j+1)).equals("S")) {
//                        System.out.print("Found XMAS forwards in row: ");
//                        System.out.println(line);
                        xmas += 1;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
        }
    }

    public static void searchUp(List<String> data, String curr, int i, int j) {
        switch (curr) {
            case "X":
                if (i > 2) {
                    String row = data.get(i-1);
                    if (String.valueOf(row.charAt(j)).equals("M")) {
                        searchUp(data, "M", i-1, j);
                    } else {
                        break;
                    }
                }
                else {
                    break;
                }
            case "M":
                if (i > 1) {
                    String row = data.get(i - 1);
                    if (String.valueOf(row.charAt(j)).equals("A")) {
                        searchUp(data, "A", i-1, j);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case "A":
                if (i > 0) {
                    String row = data.get(i - 1);
                    if (String.valueOf(row.charAt(j)).equals("S")) {
//                        System.out.print("Found XMAS up at row: ");
//                        System.out.print(i);
//                        System.out.print(" col: ");
//                        System.out.println(j);
                        xmas += 1;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
        }
    }

    public static void searchDown(List<String> data, String curr, int i, int j) {
        switch (curr) {
            case "X":
                if (i < data.size()-2) {
                    String row = data.get(i+1);
                    if (String.valueOf(row.charAt(j)).equals("M")) {
                        searchDown(data, "M", i+1, j);
                    } else {
                        break;
                    }
                }
                else {
                    break;
                }
            case "M":
                if (i < data.size()-1) {
                    String row = data.get(i + 1);
                    if (String.valueOf(row.charAt(j)).equals("A")) {
                        searchDown(data, "A", i+1, j);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case "A":
                if (i < data.size()-1) {
                    String row = data.get(i + 1);
                    if (String.valueOf(row.charAt(j)).equals("S")) {
//                        System.out.print("Found XMAS down at row: ");
//                        System.out.print(i);
//                        System.out.print(" col: ");
//                        System.out.println(j);
                        xmas += 1;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
        }
    }

    public static void searchNorthEast(List<String> data, String curr, int i, int j) {
        switch (curr) {
            case "X":
                if (i > 2) {
                    String row = data.get(i-1);
                    if (j < row.length() - 3) {
                        if (String.valueOf(row.charAt(j+1)).equals("M")) {
                            searchNorthEast(data, "M", i - 1, j + 1);
                    } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                else {
                    break;
                }
            case "M":
                if (i > 1) {
                    String row = data.get(i - 1);
                    if (j < row.length() - 2) {
                        if (String.valueOf(row.charAt(j+1)).equals("A")) {
                            searchNorthEast(data, "A", i - 1, j + 1);
                    } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case "A":
                if (i > 0) {
                    String row = data.get(i - 1);
                    if (j < row.length() - 1) {
                        if (String.valueOf(row.charAt(j+1)).equals("S")) {
//                            System.out.print("Found XMAS northeast: ");
//                            System.out.print(i);
//                            System.out.print(" col: ");
//                            System.out.println(j);
                            xmas += 1;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
        }
    }

    public static void searchSouthEast(List<String> data, String curr, int i, int j) {
        switch (curr) {
            case "X":
                if (i < data.size()-3) {
                    String row = data.get(i+1);
                    if (j < row.length() - 3) {
                        if (String.valueOf(row.charAt(j+1)).equals("M")) {
                            searchSouthEast(data, "M", i + 1, j + 1);
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                else {
                    break;
                }
            case "M":
                if (i < data.size() - 2) {
                    String row = data.get(i + 1);
                    if (j < row.length() - 2) {
                        if (String.valueOf(row.charAt(j+1)).equals("A")) {
                            searchSouthEast(data, "A", i + 1, j + 1);
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case "A":
                if (i < data.size()-1) {
                    String row = data.get(i + 1);
                    if (j < row.length() - 1) {
                        if (String.valueOf(row.charAt(j+1)).equals("S")) {
//                            System.out.print("Found XMAS southeast: ");
//                            System.out.print(i);
//                            System.out.print(" col: ");
//                            System.out.println(j);
                            xmas += 1;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
        }
    }

    public static void searchSouthWest(List<String> data, String curr, int i, int j) {
        switch (curr) {
            case "X":
                if (i < data.size()-1) {
                    String row = data.get(i+1);
                    if (j > 1) {
                        if (String.valueOf(row.charAt(j-1)).equals("M")) {
                            searchSouthWest(data, "M", i + 1, j - 1);
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                else {
                    break;
                }
            case "M":
                if (i < data.size()-1) {
                    String row = data.get(i + 1);
                    if (j > 1) {
                        if (String.valueOf(row.charAt(j-1)).equals("A")) {
                            searchSouthWest(data, "A", i + 1, j - 1);
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case "A":
                if (i < data.size()-1) {
                    String row = data.get(i + 1);
                    if (j >0) {
                        if (String.valueOf(row.charAt(j-1)).equals("S")) {
//                            System.out.print("Found XMAS southwest: ");
//                            System.out.print(i);
//                            System.out.print(" col: ");
//                            System.out.println(j);
                            xmas += 1;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
        }
    }

    public static void searchNorthWest(List<String> data, String curr, int i, int j) {
        switch (curr) {
            case "X":
                if (i > 2) {
                    String row = data.get(i-1);
                    if (j > 1) {
                        if (String.valueOf(row.charAt(j-1)).equals("M")) {
                            searchNorthWest(data, "M", i - 1, j - 1);
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                else {
                    break;
                }
            case "M":
                if (i > 1) {
                    String row = data.get(i - 1);
                    if (j >1) {
                        if (String.valueOf(row.charAt(j-1)).equals("A")) {
                            searchNorthWest(data, "A", i - 1, j - 1);
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case "A":
                if (i > 0) {
                    String row = data.get(i - 1);
                    if (j >0) {
                        if (String.valueOf(row.charAt(j-1)).equals("S")) {
//                            System.out.print("Found XMAS northwest: ");
//                            System.out.print(i);
//                            System.out.print(" col: ");
//                            System.out.println(j);
                            xmas += 1;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
        }
    }

    public static List<String> readFile(String file) {
        List<String> values = new ArrayList<String>();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                try {
                    while (myReader.hasNext()) {
                        String column = myReader.nextLine();
                        values.add(column);
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
