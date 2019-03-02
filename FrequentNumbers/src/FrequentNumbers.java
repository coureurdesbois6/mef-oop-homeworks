/**
 * @author Yigit Sezer
 * @since February 15, 2019
 * Program explanation: Finds the most repeating number in the input text file.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrequentNumbers {
    public static void main(String[] args) {
        boolean isSingular = true; //tells whether if there is only one number in the array with the most frequency
        int mostFrequency = 0; //indicates the frequency (number of occurrences) of the most frequent number or numbers
        String mostFrequents = ""; //used to store the most frequent number or numbers

        //loads the numbers in the specified text file to the array
        int[] numbers = loadNumbers("data1.txt");

        //loads the frequency of each number to their corresponding index value
        int[] frequency = computeFrequency(numbers);

        /* Finds the maximum value or values in the frequency array, in this case this value corresponds
         to the frequency of given index.
         Eg: frequency[11] == 4 means that number 11 in the numbers array is present 4 times. */
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > mostFrequency) {
                mostFrequency = frequency[i];
                mostFrequents = "" + i + ", ";
                isSingular = true;
            } else if (frequency[i] == mostFrequency) {
                mostFrequents += i + ", ";
                isSingular = false;
            }
        }

        //Simply print the most frequent number or numbers in the numbers array with the frequency
        if (isSingular)
            System.out.println("Most frequent number in the array is " + mostFrequents.substring(0, mostFrequents.length() - 2) +
                    " with the frequency of " + mostFrequency + ".");
        else
            System.out.println("Most frequent numbers in the array are " + mostFrequents.substring(0, mostFrequents.length() - 2) +
                    " with the frequency of " + mostFrequency + ".");
    }

    /**
     * Loads the numbers inside a text file to an integer array line by line.
     *
     * @param fileName Specified text file storing the numbers
     * @return Integer array loaded with the numbers inside the specified fileName
     */
    public static int[] loadNumbers(String fileName) {
        //Using a try catch block here in case the specified fileName is wrong or the file cannot be found
        try {
            //Declaring a File object for Scanner objects
            File file = new File(fileName);

            /* This scanner object is used to both count the number of lines in the file
             and to write every line of number into the array, this is mandatory due to
             nature of array sizes being fixed once they are created and scanners having to be
             created again to start reading the same file from the beginning again */
            Scanner reader = new Scanner(file);

            int lineCount = 0; //holds the number of lines in the specified file

            //Loop for counting the number of lines in the specified file
            while (reader.hasNext()) {
                lineCount++;
                reader.nextLine();
            }

            //closes the reader and creates it again
            reader.close();
            reader = new Scanner(file);

            /* Declaring the array to write the numbers inside the text file to,
             size is as same as the number of lines in the text file to
             avoid IndexOutOfBoundsException and to use as less memory as possible */
            int[] numbersArray = new int[lineCount];

            int currentLine = 0; //indicates the current number of line being read for writing into the array.

            //This loop reads each line and writes the number inside the line to array line by line.
            while (reader.hasNext()) {
                //Since the output array is an integer array, String line is gotten rid of white spaces and converted into an integer.
                numbersArray[currentLine] = Integer.parseInt(reader.nextLine().trim());
                currentLine += 1; //indicate that the next line will be written to the next index in the array
            }
            return numbersArray;
        } catch (FileNotFoundException e) {
            //If for some case the file is not found simply create an error text and exit the program.
            System.out.println("Specified file can not be found");
            System.exit(0);
            return null;
        }
    }

    /**
     * This method gives the frequency of numbers in the inputNumbersArray as another integer
     * with the each number's corresponding index indicating it's frequency.
     *
     * @param inputNumbersArray Array of numbers those frequency is wanted.
     * @return an integer array which holds the frequency of each number in the inputNumbersArray in their corresponding index value
     */
    public static int[] computeFrequency(int[] inputNumbersArray) {
        /* Maximum value in the inputNumbersArray is required to hold the frequency of
         the maximum number so that value is also set as the size of the frequency array */
        int maxValue = 0;
        for (int i = 0; i < inputNumbersArray.length; i++) {
            if (inputNumbersArray[i] > maxValue)
                maxValue = inputNumbersArray[i];
        }

        /* Count the number of occurrences of each number in the inputNumbersArray by increasing that number's
         index value in the frequencyArray */
        int[] frequencyArray = new int[maxValue + 1];
        for (int i = 0; i < inputNumbersArray.length; i++) {
            frequencyArray[inputNumbersArray[i]]++;
        }
        return frequencyArray;
    }
}

