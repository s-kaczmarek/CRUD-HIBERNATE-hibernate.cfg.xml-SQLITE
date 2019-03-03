import controller.Controller;
import domain.*;
import hibernate.utils.HibernateUtils;

import java.util.Scanner;

public class App {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        boolean run = true;

        do {

            // MENU
            System.out.println("");
            System.out.println("MENU");
            System.out.println("=====================");
            System.out.println("1. List all contacts");
            System.out.println("2. Get contact by id");
            System.out.println("3. Add new contact");
            System.out.println("4. Update contact");
            System.out.println("5. Delete contact");
            System.out.println("6. Drop all tables");
            System.out.println("7. Exit");
            System.out.println("=====================");
            System.out.println("");

            int choice = scanner.nextInt();

            switch (choice){

                case 1:
                    // SUB MENU
                    System.out.println("");
                    System.out.println("MENU");
                    System.out.println("=====================");
                    System.out.println("1. Sorted by id");
                    System.out.println("2. Sort by name asc");
                    System.out.println("3. Sort by name desc");
                    System.out.println("=====================");
                    System.out.println("");
                    int choiceSort = scanner.nextInt();
                    switch (choiceSort){
                        case 1:
                            controller.getAllContacts();
                            break;
                        case 2:
                            controller.getAllContactsSortedByName("asc");
                            break;
                        case 3:
                            controller.getAllContactsSortedByName("desc");
                            break;
                    }break;
                case 2:
                    controller.getContactById();
                    break;
                case 3:
                    controller.addNewContact();
                    break;
                case 4:
                    controller.updateContact();
                    break;
                case 5:
                    controller.deleteContact();
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("bye!");
                    run = false;
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }while(run);

        HibernateUtils.closeSession();
    }
}
