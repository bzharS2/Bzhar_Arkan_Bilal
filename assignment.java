//package Assignment;

import java.util.*;

public class assignment {
    static Scanner input = new Scanner(System.in);
    static int choice;

    public static void main(String[] args) {
        do {
            menu();
            switch (choice) {

                case 1:

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;
                case 7:

                    break;

                case 8:

                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }

        } while (choice != 0);
    }

    public static void menu() {
        System.out.println("-------------------------");
        System.out.println("1. Choose an event category");
        System.out.println("2. Choose an event");
        System.out.println("3. Remove a participant");
        System.out.println("4. Display al registered participant");
        System.out.println("5. Display total number of registrations");
        System.out.println("6. Search for a participant");
        System.out.println("7. Display recent registrations");
        System.out.println("8. Generate a summary");
        System.out.println("Enter (0) to exit ");
        System.out.println("-------------------------");
        try {
            System.out.print("Enter a choice ");
            choice = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: That's not a valid Choice. Try again.");
            input.next();
            choice = -1;
        }
    }
}
