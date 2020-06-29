/**
 * @author [Jefferson Faustin]
 * @email [faustinj@hotmail.fr]
 * @create date 2020-06-29 11:58:30
 * @modify date 2020-06-29 11:58:30
 * @desc [My code for the Code_4_Canada challenge]
 */
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class SkillChallenge {
    public static void main(final String[] args) {
        String file = "./C4C-dev-challenge-2018.csv";
        
        List<String[]> content = readData(file);
        
        //Removes the headers from the list
        content.remove(0);
        
        //We get all the categories in the and remove all duplicates
        List<String> categories = content.stream().map(c -> c[2]).distinct().collect(Collectors.toList());
        
        //The list that will contain the information that we need
        List<Violation> statistics = new ArrayList<>();
        
        //Converts a string date to a Date type variable
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd h:mm");

        //Converts a Date variable to a string
        DateFormat sf = new SimpleDateFormat("yyyy-MM-dd h:mm");
        
        for (int i = 0; i < categories.size(); i++) {
            final int index = i;

            //We get the number of violations for a category
            int numberOfViolation = (int) content.stream().filter(c -> c[2].equals(categories.get(index)))
            .count();
            
            //We get the vioaltion dates associated with a certain category
            List<String> datesString = content.stream().filter(c -> c[2].equals(categories.get(index)))
            .map(c -> c[3]).collect(Collectors.toList());
            
            //Convert the strings into Date variables
            List<Date> dates = datesString.stream().map(c -> {
                try {
                    return df.parse(c);
                } catch (ParseException e) {
            
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            
            //Sort the dates to the earliest to the most recent
            Collections.sort(dates);
            
            //Create an instance of Violation that contains the info on a category
            Violation cat = new Violation(categories.get(i), numberOfViolation, sf.format(dates.get(0)), sf.format(dates.get(dates.size()-1)));
            
            //Add our Violation variable to our list
            statistics.add(cat);      
        }

        //Print the content of our list the show the information each category
        for(int i = 0; i < statistics.size(); i++) {
            System.out.println(statistics.get(i) + "\n");
        }
        
    }
    

        /*
        * Returns a list that contains the content of a CSV file.
        *
        * @param the path of the CSV file
        * @return the list of the that contains the content of the file
        */
    public static List<String[]> readData(String file) {
        final List<String[]> content = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String row = "";
            
            while ((row = br.readLine()) != null) {
                content.add(row.split(","));
            }
            
        } catch (final Exception e) {
            System.out.println(e);
        }
        
        return content;
    }
}