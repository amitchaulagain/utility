
package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Hero {

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "/Users/admin/backup/UtilityProject/src/com/company/hero.txt"));
            String line = reader.readLine();

            String allLines="";
            while (line != null) {

                String newLine = line.replaceAll("= [0-`9]", "");
                // System.out.println(newLine);

                String[] x = newLine.trim().split("\\s+");

                String ourLine = "";
                switch (x[0]) {
                    case "google.protobuf.StringValue": {
                        ourLine = "Optional<String>";
                        break;

                    }
                    case "google.protobuf.DoubleValue": {
                        ourLine = "Optional<Double>";
                        break;

                    }
                    case "google.protobuf.BoolValue": {
                        ourLine = "Optional<Boolean>";
                        break;

                    }
                    default: {
                        ourLine = x[0];
                    }
                }

                String eachLine = ourLine + " " + snakeToCamel(x[1]) + " ;\n";
                allLines+=eachLine;


               // System.out.println(eachLine);
                // read next line
                line = reader.readLine();
            }
            reader.close();
            System.out.println(allLines);

            writeToAFile(allLines);
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    public static String snakeToCamel(String str) {
        // Capitalize first letter of string
        try {
            str = str.substring(0, 1).toLowerCase()
                    + str.substring(1);
        } catch (Exception e) {

        }

        // Convert to StringBuilder
        StringBuilder builder
                = new StringBuilder(str);

        // Traverse the string character by
        // character and remove underscore
        // and capitalize next letter
        for (int i = 0; i < builder.length(); i++) {

            // Check char is underscore
            if (builder.charAt(i) == '_') {

                builder.deleteCharAt(i);
                builder.replace(
                        i, i + 1,
                        String.valueOf(
                                Character.toUpperCase(
                                        builder.charAt(i))));
            }
        }

        // Return in String type
        return builder.toString();
    }

    public static void writeToAFile(String allLines) throws IOException {
        FileWriter myWriter = new FileWriter("/Users/admin/backup/UtilityProject/src/com/company/output.txt");
        myWriter.write(allLines);
        myWriter.close();
        //System.out.println("Successfully wrote to the file.");
    }


}