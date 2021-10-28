package com.oracle.interview;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Step 1 : Traverse the number from rightside. Keep checking till you get a digit smaller than the previous
 * Example -- "1243". Here while traversing from right, stop at 2, because 2 is smaller than the next digit on the right, i.e. 4
 * If there is no such occurence, means the number is already in descending order and there is no bigger number possible
 *
 * Step 2 : Now look at all the numbers to right of 2, and find the smallest among them. Here, the smallest to the right of 2 is 3
 *
 * Step 3 : Swap the two digits found in the above step. New number == 1342
 *
 * Step 4 : Sort all the digits to the right side of 3.
 * Result == 1324
 *
 * Therefore, 1324 is the next biggest number with the same set of digits for the given input, 1234
 *
 * Special Cases --
 * 1. If the digits are already in descending order, there will be no other bigger number with the same set of digits.
 * Example : 8451. It is the greatest and no other greater number can be formed from the same set of number
 *
 * 2. If the digits are sorted in ascending order, you need to just swap the last two digits of the input
 * Example : 1548. Result will be a swap of 4 & 8 --> 1584
 *
 * **/

public class NextBiggestNumber {

    private final static String STOP = "stop";

    public static void main(String []args) {

        NextBiggestNumber nextBiggestNumber = new NextBiggestNumber();
        Scanner scanner = new Scanner(System.in);

        String inputNumber = "";

        while(!inputNumber.equalsIgnoreCase(STOP)) {
            System.out.println("Please enter input number : ");
            inputNumber = scanner.nextLine();
            if (isNumeric(inputNumber)) {
                String result = nextBiggestNumber.evaluate(inputNumber);

                if (inputNumber.equals(result))
                    System.out.println(String.format(" '%s' This is the biggest number. There is no next biggest.", inputNumber));
                else
                    System.out.println(String.format("The next biggest number is : '%s'", result));
            }
            else {
                if (!inputNumber.equalsIgnoreCase(STOP))
                    System.out.println("This is not a number, please try again with a number.");
            }
        }
    }

    public String evaluate(String input) {
        int [] nextBigNumber = findNextBiggestNumber(input);
        if (nextBigNumber == null)
            return input;
        else {
            Stream<String> numberToString = Arrays.stream(Arrays.stream(nextBigNumber)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new));
            return numberToString.collect(Collectors.joining(""));
        }
    }

    private int[] findNextBiggestNumber(String input) {
        int [] digits = Arrays.stream(input.split("")).mapToInt(Integer::parseInt).toArray();

        // Step 1 : If already in descending order, there is no other greater number with the same set of digits
        if (isSorted (digits, digits.length))
            return null;

        // Step 2 : Traverse from rightmost digit. Keep traversing till you get a digit smaller than the previous digit
        int firstSmallestDigit = findFirstSmallestFromRight(digits);

        // Step 3 : Swap two digits found in Step 2
        int [] swapped = swapArrayElements(digits, firstSmallestDigit, digits.length - 1);

        // Step 4 : Sort all the digits on the right side of the number found in Step 2
        Arrays.sort(swapped, firstSmallestDigit + 1, swapped.length);

        return swapped;
    }

    public static boolean isNumeric(String input) {
        return input.chars().allMatch(Character::isDigit);
    }

    private boolean isSorted (int [] digits, int index) {
        if (digits == null || digits.length <= 1 || index == 1)
            return true;
        if (digits[index - 1] > digits[index - 2])
            return false;
        return isSorted(digits, index - 1);
    }

    private int findFirstSmallestFromRight(int [] digits) {
        for(int i = digits.length-1; i>=0; i--) {
            if (i>1 && (digits[i] > digits[i-1]))
                return i - 1;
        }
        return 0;
    }

    public int[] swapArrayElements(int [] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
        return array;
    }

}
