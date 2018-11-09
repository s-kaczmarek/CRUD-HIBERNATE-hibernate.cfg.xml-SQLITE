package io.utils.csv;


import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;


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



}
