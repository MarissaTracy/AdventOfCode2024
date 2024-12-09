package com.example.adventofcode.dayfive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class dayfive {
    public static void main(String[] args) {
        List<String> data = readFile("AdventDay1/src/com/example/adventofcode/dayfive/day5data.txt");
        List<String> rules = getRules(data);
        List<List<String>> order = getOrder(data);
        List<List<String>> validOrders = findValidOrders(rules, order);
        int partOne = getTotal(validOrders);
        System.out.print("Part One Answer: ");
        System.out.println(partOne);
        List<List<String>> invalidOrders = findInvalidOrders(validOrders, order);
        List<List<String>> reordered = reorderOrders(invalidOrders, rules);
        System.out.print("Part Two Answer: ");
        System.out.println(getTotal(reordered));

    }

    public static List<List<String>> findValidOrders(List<String> rules, List<List<String>> orders) {
        List<List<String>> validOnes = new ArrayList<>();
        for (List<String> order : orders) {
            boolean valid = true;
//            System.out.println(order);
            for (int i = 0; i < order.size()-1; i++ ) {
                String start = order.get(i);
                String next = order.get(i+1);
                String checkRule = start + "|" + next;
                if (!rules.contains(checkRule)) {
                    valid = false;
                    break;
                }
//                System.out.println(checkRule);
            }
            if (valid) {
                validOnes.add(order);
            }
        }
//        System.out.println(validOnes);
        return validOnes;
    }

    public static List<List<String>> findInvalidOrders(List<List<String>> valid, List<List<String>> all) {
        List<List<String>> invalid  = all;
        invalid.removeAll(valid);
//        System.out.println(invalid);
        return invalid;
    }

    public static List<List<String>> reorderOrders(List<List<String>> invalid, List<String> rules) {
        for (List<String> invalidOrder : invalid) {
//            System.out.println(order);
            for (int i = 0; i < invalidOrder.size()-1; i++ ) {
                String start = invalidOrder.get(i);
                String next = invalidOrder.get(i+1);
                String checkRule = next + "|" + start;
                if (rules.contains(checkRule)) {
                    swap(invalidOrder, i, i+1);
                }
//                System.out.println(checkRule);
            }
        }

        List<List<String>> valids = findValidOrders(rules, invalid);
        if (!valids.equals(invalid)) {
            reorderOrders(invalid, rules);
        }

        return invalid;
    }

    public static final <T> void swap (List<T> l, int i, int j) {
        Collections.<T>swap(l, i, j);
    }

    public static int getTotal(List<List<String>> validOrders) {
        int total = 0;
        //only do this for ones that are valid, so do this one an array of only valid ones
        for (List<String> valid : validOrders) {
            int middleIndex = (int) Math.ceil((double) (valid.size() /2));
            String middleChar = valid.get(middleIndex);
            total += Integer.parseInt(middleChar);
        }
        return total;
    }
    public static List<String> readFile(String file) {
        List<String> values = new ArrayList<>();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                try {
                    while (myReader.hasNext()) {
                        String col = myReader.nextLine();
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

    public static List<String> getRules(List<String> rules) {
        List<String> finalRules = new ArrayList<>();
        for (String rule : rules) {
            if (rule.contains("|")) {
                finalRules.add(rule);
            }
        }
        return finalRules;
    }

    public static List<List<String>> getOrder(List<String> orders) {
        List<List<String>> Orders = new ArrayList<>();
        List<List<String>> finalOrders = new ArrayList<>();
        for (String order : orders) {
            List<String> x = new ArrayList<>();
            if (order.contains(",")) {
                x.add(order);
            }
            if (!x.isEmpty()) {
                Orders.add(x);
            }
        }

        for (int i=0; i< Orders.size(); i++) {
            List<String> x = Orders.get(i);
//            System.out.println(x);
            for (String order : x) {
                List<String> column = new ArrayList<>();
                String[] nums = order.split(",");
                for (int j=0; j<nums.length; j++) {
                    column.add(nums[j]);
                }
                finalOrders.add(column);
            }
        }
//        System.out.println(finalOrders);
        return finalOrders;
    }

}
