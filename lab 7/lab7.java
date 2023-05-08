import java.io.*;
import java.util.*;
public class lab7 {
    // A method that sorts an array of floats in ascending order and returns the sorted array.
    static float[] sort(float[] arr, int l) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l - i - 1; j++) {
                if (arr[j] >= arr[j + 1]) {
                    float temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return (arr);
    }
    // A method that calculates and returns the mean of an array of floats.
    static float mean(float[] arr, int l) {
        float sum = 0;
        for (int i = 0; i < l; i++) {
            sum += arr[i];
        }
        float mean = sum / l;
        return mean;
    }
    // A method that calculates and returns the median of an array of floats.
    static float median(float[] arr1, int l) {
        float[] arr = sort(arr1, l);
        float median = 0;
        for (int i = 0; i < l; i++) {
            if (l % 2 == 0) {
                float m = (arr[(l / 2) - 1] + arr[(l / 2)]);
                median = m / 2;
            } else {
                median = arr[(l - 1) / 2];
            }
        }
        return median;
    }
    // A method that calculates and returns the mode of an array of floats.
    static float mode(float[] arr, int l) {
        int max = 0;
        float mode = 0;

        for (int i = 0; i < l; i++) {
            int count = 0;
            for (int j = i + 1; j < l; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                mode = arr[i];
            }
        }
        if (max == 1) {
            mode = 0;
        }
        return mode;
    }
    // A method that returns the maximum value in an array of floats.
    static float max(float[] arr, int l) {
        float[] arr1 = sort(arr, l);
        float max = arr1[l - 1];
        return max;
    }
    // A method that returns the minimum value in an array of floats.
    static float min(float[] arr, int l) {
        float[] arr1 = sort(arr, l);
        float min = arr1[0];
        return min;
    }
    // A method that calculates and returns a summary of statistical information for an array of arrays of floats.
    static String[][] summary(float[][] para, int l) {
        String[][] arr = new String[5][5];
        String[] arr1 = { "MEAN", "MEDIAN", "MODE", "MAX", "MIN" };
        arr[0] = arr1;
        for (int i = 0; i < 4; i++) {
            String[] sum = { mean(para[i], l) + "", median(para[i], l) + "", mode(para[i], l) + "", max(para[i], l) + "",
                    min(para[i], l) + "" };
            arr[i + 1] = sum;
        }
        return arr;
    }
// Define a static method named "display" that takes in a 2D string array and a string argument, and returns a string value
static String display(String[][] arr, String s) {
    // Define a string array named "arr1" and initialize it with values
    String[] arr1 = {"PARAMETER", "SEPAL LENGTH", "SEPAL WIDTH", "PETAL LENGTH", "PETAL WIDTH"};
    // Define a string variable named "b" and initialize it with a vertical bar character
    String b = "|";
    // Define a string variable named "str" and initialize it with a formatted string
    String str = String.format("%s%s%s", "-".repeat(40), s, "-".repeat(40));
    // Add a new line character to "str"
    str += "\n";
    // Loop through the elements in "arr1" array
    for (int i = 0; i < 5; i++) {
        // Append a vertical bar character and a formatted string to "str"
        str += b + String.format("%-13s", arr1[i]) + b;
        // Loop through the elements in the current row of "arr" array
        for (int j = 0; j < 5; j++) {
            // Append a formatted string to "str"
            str += String.format("%-13s", arr[i][j]) + b;
        }
        // Add a new line character to "str"
        str += "\n";
    }
    // Print the final string value to the console
    System.out.println(str);
    // Return the final string value
    return str;
}

// Define the main method
public static void main(String[] args) {
    // Define a 2D string array named "arr" and initialize it with a size of 1024 x 6
    String[][] arr = new String[1024][6];
    // Define an integer variable named "n" and initialize it to zero
    int n = 0;
    try {
        // Create a new file object with the file name "Iris.csv"
        File ob = new File("Iris.csv");
        // Create a new scanner object to read the file
        Scanner scan = new Scanner(ob);
        // Loop through each line in the file
        while (scan.hasNextLine()) {
            // Read the current line as a string
            String s = scan.nextLine();
            // Split the string into an array of strings using comma as the delimiter
            arr[n] = s.split(",");
            // Increment "n" by one
            n++;
        }
        // Close the scanner object
        scan.close();
    } catch (Exception io) {
        // Print the error message to the console if an exception occurs
        System.out.println(io.getMessage());
    }
    // Decrement "n" by one
    n--;
    // Define four float arrays named "seplen", "sepwid", "petlen", and "petwid" with a size of "n"
    float[] seplen = new float[n];
    float[] sepwid = new float[n];
    float[] petlen = new float[n];
    float[] petwid = new float[n];
    // Define a string array named "species" with a size of "n"
    String[] species = new String[n];
    // Define a 2D float array named "total" and initialize it with the four float arrays
    float[][] total = {seplen, sepwid, petlen, petwid};
    // Define three 2D float arrays named "setosa", "versicolor", and "virginica"
    float[][] setosa =new float[4][n]; // 2D array to store data for setosa
    float[][] versicolor =new float[4][n]; // 2D array to store data for versicolor
    float[][] virginica =new float[4][n]; // 2D array to store data for virginica
    for(int i = 0; i < n; i++){
        // extract the four measurements for the ith flower
        seplen[i] = Float.parseFloat(arr[i + 1][1]);
        sepwid[i] = Float.parseFloat(arr[i + 1][2]);
        petlen[i] = Float.parseFloat(arr[i + 1][3]);
        petwid[i] = Float.parseFloat(arr[i + 1][4]);
        // extract the species for the ith flower
        species[i] = arr[i + 1][5].split("-")[1];
    }
    int setCount = 0; // initialize counter for setosa
    int verCount = 0; // initialize counter for versicolor
    int virCount = 0; // initialize counter for virginica
    for(int i = 0; i < n; i++){
        if(species[i].equals("setosa")){
            // store the four measurements for the ith setosa flower
            for(int j = 0; j < 4; j++){
                setosa[j][setCount] = total[j][i];
            }
            setCount++; // increment count for setosa
        }
        else if(species[i].equals("versicolor")){
            // store the four measurements for the ith versicolor flower
            for(int j = 0; j < 4; j++){
                versicolor[j][verCount] = total[j][i];
            }
            verCount++; // increment count for versicolor
        }
        else if(species[i].equals("virginica")){
            // store the four measurements for the ith virginica flower
            for(int j = 0; j < 4; j++){
                virginica[j][virCount] = total[j][i];
            }
            virCount++; // increment count for virginica
        }
    }
    // generate the summary table for each species and the total
    String str = display(summary(total,n),"TOTAL")+display(summary(setosa,setCount),"SETOSA")+
                    display(summary(versicolor,verCount),"VERSICOLOR")+
                    display(summary(virginica,virCount),"VIRGINICA");
    // write the summary table to a file
    try {
        FileWriter writer = new FileWriter("Output.txt");
        writer.write(str);
        writer.close();
        System.out.println("Output written to file 'output.txt'");
    } catch (IOException e) {
        System.out.println("An error occurred while writing to file.");
        e.printStackTrace();
    }
}
}    