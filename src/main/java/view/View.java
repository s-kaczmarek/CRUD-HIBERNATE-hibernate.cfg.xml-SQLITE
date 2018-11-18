package view;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    public List<List<String>> newContactCreator(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add New Contact");
        System.out.println("===============");
        System.out.println("");

        List<String> basicData = new ArrayList<String>();

        System.out.println("First Name: ");
        String firstName = scanner.next();
        System.out.println("Last Name: ");
        String lastName = scanner.next();
        System.out.println("Email: ");
        String email_ = scanner.next();

        basicData.add(firstName);
        basicData.add(lastName);
        basicData.add(email_);

        List<String> groups = new ArrayList<String>();
        boolean groupRun = true;
        do{
            System.out.println("Group name: ");
            String groupName = scanner.next();

            groups.add(groupName);

            System.out.println("Would you like to add another group? y/n");
            String choice = scanner.next();
            switch (choice.charAt(0)){
                case 'y':
                    break;
                case 'n':
                    groupRun = false;
                    break;
            }

        }while(groupRun);

        List<List<String>> allDataRequiredToCreateContactList = new ArrayList<List<String>>();
        allDataRequiredToCreateContactList.add(basicData);
        allDataRequiredToCreateContactList.add(groups);

        return allDataRequiredToCreateContactList;
    }
}
