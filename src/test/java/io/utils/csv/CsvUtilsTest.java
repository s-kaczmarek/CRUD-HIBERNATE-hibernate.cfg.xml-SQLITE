package io.utils.csv;

public class CsvUtilsTest {

    public static void main(String[] args) {

        // TODO make it a rel test

        String path = "D:\\CLOUD\\MEGA\\Documents\\#PROGRAMOWANIE\\JAVA\\#Brudnopisy\\CRUD-HIBERNATE-hibernate.cfg.xml-SQLITE\\src\\test\\resources\\testSampleDataToLoad.csv";
        CsvUtils csvUtils = new CsvUtils();

        //csvUtils.printCSVfile(path);


        csvUtils.mapCSVfileToObjectList(path);

    }
}
