package domain.utils;

public class IOutilsTest {

    public static void main(String[] args) {

        try {
            IOutils.saveAllContactsToCSV();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
