package io.utils.csv;


import com.opencsv.CSVReader;
import domain.Contact;
import domain.utils.ContactBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CsvUtils {

    public void printCSVfile(String pathToFile){

        try{

            CSVReader csvReader = new CSVReader(new FileReader(pathToFile));
            String[]nextLine;
            while((nextLine = csvReader.readNext()) != null){
                if(nextLine != null){
                    System.out.println(Arrays.toString(nextLine));
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    // TODO change to return object and make proper test
    public void mapCSVfileToObjectList(String pathToFile){

        ContactBuilder contactBuilder = new ContactBuilder();

        try{

            CSVReader csvReader = new CSVReader(new FileReader(pathToFile));
            String[]nextLine;
            while((nextLine = csvReader.readNext()) != null){
                if(nextLine != null){

                    String firstName = nextLine[1];
                    String lastName = nextLine[2];
                    String email = nextLine[3];
                    String groups = nextLine[4];

                    // split group of strings by coma and add each of them into array
                    String[] gr = groups.split(",");

                    // get rid of brackets and space in array and add each group to list in order to pass it into
                    // contact builder.
                    String pattern = "\\[|\\]|\\s";
                    List<String> formattedGroups = new ArrayList<String>();
                    for(int i=0; i < gr.length; i++){
                        String temp = gr[i];
                        String tempReplaced = temp.replaceAll(pattern,"");
                        formattedGroups.add(tempReplaced);
                        //
                        System.out.println(formattedGroups);
                    }

                    Contact testContact = contactBuilder
                            .buildFirstName(firstName)
                            .buildLastName(lastName)
                            .buildEmail(email)
                            .buildGroups((ArrayList<String>) formattedGroups)
                            .build();

                    System.out.println(testContact);

                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
