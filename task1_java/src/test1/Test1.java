package test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Exercise 1: Create a program that reads a list of words from a text file
 * (res/words.txt) and:
 *
 * Remove duplicate words and prints resulting word list 
 * Sorts words alphabetically and prints resulting word list 
 * Sorts words by size (number of characters) and prints resulting word list 
 * Separates words into 26 files (A.txt, B.txt, ..., Z.txt)where each file <Letter>.txt has the words starting with <Letter> sorted alphabetically.
 */
public class Test1 {

    public static void main(String[] args) {
        ArrayList<String> inputData = new ArrayList<>();

        //read the data from file - for this implementation the file is required to be in the same folder as test1.java file
        String fileName = "words.txt";
        try {
            inputData = read(fileName);
        } //catch exceptions regarding file reading
        catch (FileNotFoundException e) {
            System.err.println("Error - the file " + fileName + " is missing ");
            Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            System.err.println("Error reading data from file ");
            Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, e);
        }

        //run tasks :
        removeDuplicates(inputData);
        alphabeticalSort(inputData);
        lengthSort(inputData);
        try {
            alphabeticalSort2File(inputData);
        } catch (IOException e) {
            System.err.println("Error saving data from file ");
            Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     *
     * @param file - filename to read
     * @return - returns ArrayList<String> of all read lines - one line per
     * ArrayList<String> entry
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<String> read(String file) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> input = new ArrayList<>();
        try {
            String line = br.readLine();

            while (line != null) {
                input.add(line);
                line = br.readLine();

            }
        } finally {
            br.close();
        }
        return input;

    }

    /**
     * function removes duplicate data
     *
     * @param inputData - data read from file
     */
    public static void removeDuplicates(ArrayList<String> inputData) {

        //hash set can contain only unique values, thus removing duplicates from iuput data
        Set<String> list = new HashSet<>(inputData);

        // print data
        for (String string : list) {
            System.out.println(string);
        }

    }

    /**
     *
     * @param inputData - data read from file
     */
    public static void alphabeticalSort(ArrayList<String> inputData) {
        //JAVA COLLECTION CAN SORT STRINGS - USING CASE INSENSITIVE, JUST IN CASE - THE EXAMPLE DATASET IS ALL LOWERCASE.
        Collections.sort(inputData, String.CASE_INSENSITIVE_ORDER);

        //PRINT RESULTS
        for (String string : inputData) {
            System.out.println(string);
        }
    }

    /**
     *
     * @param inputData - data read from file
     */
    public static void lengthSort(ArrayList<String> inputData) {
        //convert ArrayList<String> to Array to use the integrated sort function
        String[] data = inputData.toArray(new String[inputData.size()]);

        //use Array.sort with a custom comparator - compares length of strings
        Arrays.sort(data, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return Integer.compare(a.length(), b.length());
            }
        });

        // print data
        for (String string : data) {
            System.out.println(string);
        }

    }

    /**
     *
     * @param inputData - data read from file
     * @throws IOException
     */
    public static void alphabeticalSort2File(ArrayList<String> inputData) throws IOException {

        //sort the whole input alphabetically
        Collections.sort(inputData, String.CASE_INSENSITIVE_ORDER);

        //for all lowercase characters [a-z] in ascii representation:
        for (int ascii = 97; ascii < 123; ascii++) {
            //temporary variable declaration
            String out = "";
            Writer writer = null;

            //iterate through the whole input data set
            for (int i = 0; i < inputData.size(); i++) {
                //if the first character of string /converted to int/ equals the ascii value of current iteration, append the string to output 
                if ((int) (inputData.get(i).charAt(0)) == ascii) {
                    out = out + inputData.get(i) + "\n";
                }
            }

            //write the output string for current ascii character to file
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(Character.toUpperCase(out.charAt(0)) + ".txt"), "utf-8"));
                writer.write(out);
            } catch (IOException ex) {
                System.err.println("Error writing to file " + Character.toUpperCase(out.charAt(0)) + ".txt");
            } finally {
                writer.close();
            }
        }

    }

}
