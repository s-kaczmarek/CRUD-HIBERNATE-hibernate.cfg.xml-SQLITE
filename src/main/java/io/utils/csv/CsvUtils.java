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

    public List<Contact> mapCSVfileToObjectList(String pathToFile){

        List<Contact> parsedContactsList = new ArrayList<Contact>();

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
                    }

                    Contact parsedContact = new ContactBuilder()
                            .buildFirstName(firstName)
                            .buildLastName(lastName)
                            .buildEmail(email)
                            .buildGroups((ArrayList<String>) formattedGroups)
                            .build();

                    parsedContactsList.add(parsedContact);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return parsedContactsList;
    }

    public void exportAllContactsToCSV(String path){
        //TODO
    }
}
