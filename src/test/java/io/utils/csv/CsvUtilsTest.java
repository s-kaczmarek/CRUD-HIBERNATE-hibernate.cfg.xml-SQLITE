package io.utils.csv;

public class CsvUtilsTest {

    public static void main(String[] args) {

        String path = "D:\\CLOUD\\MEGA\\Documents\\#PROGRAMOWANIE\\JAVA\\#Brudnopisy\\CRUD-HIBERNATE-hibernate.cfg.xml-SQLITE\\test.csv";
        CsvUtils csvUtils = new CsvUtils();

        csvUtils.printCSVfile(path);

    }
}
