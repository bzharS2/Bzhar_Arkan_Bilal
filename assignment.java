// Before starting the program please read the README.md
package Assignment;

import java.util.*;

public class assignment {
    static Scanner input = new Scanner(System.in);
    static int choice;
    static int choice2;

    static String[] category = { "Music", "Art Exhibitions", "literature & Poetry" };
    static int Cchoice;
    static int Echoice;
    static String PNameId;
    static int Pcounter;
    static char sort;
    static boolean hasparticipants = true;

    static boolean Cchosen = false;
    static ArrayList<ArrayList<String>> eventNames = new ArrayList<>();
    static ArrayList<ArrayList<ArrayList<String>>> registrations = new ArrayList<>();
    // we created an array to keep track of everything's counter
    // we used get(0) for the events name because every category have 4 events

    public static void main(String[] args) {
        Eventdecleration();
        RegistrationDecleration();
        int counter[][] = new int[category.length][eventNames.get(0).size()];
        do {
            menu();
            switch (choice) {

                case 1:
                    chooseC();
                    Cchosen = true;
                    break;

                case 2:
                    // make sure a category is chosen
                    if (Cchosen) {
                        chooseE();
                        Register(counter);

                    } else {
                        System.out.println("A category must be chosen in order to move on");
                    }
                    Cchosen = false;
                    break;

                case 3:
                    if (Pcounter > 0) {

                        // make sure a category is chosen
                        if (Cchosen) {
                            chooseE();
                            Remove(counter);

                        } else {
                            System.out.println("A category must be chosen in order to move on");

                        }
                        Cchosen = false;
                    } else {
                        System.out.println("there is no participants to remove");
                    }
                    break;

                case 4:
                    prompt();
                    SortParticipants();
                    DisplayParticipants();
                    if (!hasparticipants) {
                        System.out.println("there are no participants");
                    }

                    break;

                case 5:
                    CounterMethod(counter);

                    break;

                case 6:

                    break;
                case 7:

                    break;

                case 8:

                    break;
                case 0:
                    System.out.println("Exiting....");
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }

        } while (choice != 0);
    }

    // create a method to display the menu
    public static void menu() {
        System.out.println("-------------------------");
        System.out.println("1. Choose an event category");
        System.out.println("2. Choose an event and add a participant");
        System.out.println("3. Remove a participant");
        System.out.println("4. Display al registered participant");
        System.out.println("5. Display total number of registrations");
        System.out.println("6. Search for a participant");
        System.out.println("7. Display recent registrations");
        System.out.println("8. Generate a summary");
        System.out.println("Enter (0) to exit ");
        System.out.println("-------------------------");
        // used try and catch to avoid InputMismatchException
        try {
            System.out.print("Enter a choice ");
            choice = input.nextInt();
            System.out.println("-------------------------");

        } catch (InputMismatchException e) {
            System.out.println("Error: That's not a valid Choice. Try again.");
            input.next();
            choice = -1;
        }

    }

    // create a method to display the category
    public static void DisplayCategory() {
        for (int i = 0; i < category.length; i++) {
            System.out.println((i + 1) + "- " + category[i]);
        }

    }

    // create a method to get the chosec category
    public static void chooseC() {
        DisplayCategory();
        do {
            try {
                System.out.print("choose a category: ");
                Cchoice = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input ");
                input.nextLine();
                Cchoice = -1;
            }

        } while (Cchoice < 1 || Cchoice > 3);
        Cchoice = Cchoice - 1; // to get the index of that category
    }

    public static void Eventdecleration() {
        for (int c = 0; c < category.length; c++) {
            eventNames.add(new ArrayList<>()); // create each empty list for each category
        }

        // Add 4 events per category
        eventNames.get(0).add("Traditional Music Night"); // Music
        eventNames.get(0).add("Youth Band Festival");
        eventNames.get(0).add("Solo Instrument Show");
        eventNames.get(0).add("Cultural Rhythm Jam");

        eventNames.get(1).add("Modern Art Gallery"); // Art
        eventNames.get(1).add("Photography Showcase");
        eventNames.get(1).add("Kurdish Calligraphy");
        eventNames.get(1).add("Digital Art Expo");

        eventNames.get(2).add("Poetry Reading Circle"); // Literature
        eventNames.get(2).add("Short Story Evening");
        eventNames.get(2).add("Book Discussion Forum");
        eventNames.get(2).add("Classical Literature Talk");
    }

    public static void DisplayEvents() {

        for (int j = 0; j < eventNames.get(Cchoice).size(); j++) {
            System.out.println((j + 1) + "-" + eventNames.get(Cchoice).get(j));
        }
        System.out.println("chosen category is " + (Cchoice + 1));

    }

    // create a method to get the chosec category
    public static void chooseE() {
        DisplayEvents();
        do {
            try {
                System.out.print("choose a event: ");
                Echoice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                input.nextLine();
                Echoice = -1;
            }

        } while (Echoice < 1 || Echoice > 4);
        Echoice = Echoice - 1; // to get the index of that Event
        System.out.println("chosen event " + (Echoice + 1));
    }

    public static void RegistrationDecleration() {

        // Create the empty registration lists
        for (int c = 0; c < category.length; c++) {
            registrations.add(new ArrayList<>()); // create empty list for each categories
            for (int e = 0; e < 4; e++) { // 4 events
                registrations.get(c).add(new ArrayList<>()); // create empty link inside each category for participants
                                                             // inside an event

            }
        }

    }

    // create a method to get the name + id from the user
    public static void EnterNameID() {
        input.nextLine(); // to get rid of the java input bug

        do {

            System.out.print("enter a name to add/remove like the following example (name-id) NO SPACES: ");
            PNameId = input.nextLine().trim(); // we user trim to get rid of spaces
        } while (PNameId.isEmpty());
    }

    // create a method to add the name+id
    public static void Register(int [][]counter) {

        EnterNameID();
        registrations.get(Cchoice).get(Echoice).add(PNameId);
        Pcounter++;
        counter[Cchoice][Echoice] = counter[Cchoice][Echoice] + 1;
    }

    // create a method to remove a name+id
    public static void Remove(int[][]counter) {

        if (registrations.get(Cchoice).get(Echoice).isEmpty()) {
            System.out.println("No participants in this event to remove.");
        } else {
            EnterNameID();
            boolean removed = registrations.get(Cchoice).get(Echoice).remove(PNameId);
            if (removed) {
                System.out.println("Participant removed successfully!");
                Pcounter--;
                counter[Cchoice][Echoice] = counter[Cchoice][Echoice] - 1;

            } else {
                System.out.println("Participant not found!");
            }
        }
    }

    public static void DisplayParticipants() {
        for (int i = 0; i < category.length; i++) {
            for (int j = 0; j < eventNames.get(i).size(); j++) {
                ArrayList<String> participants = registrations.get(i).get(j);
                if (participants.isEmpty()) {
                    hasparticipants = false;
                }
                for (int k = 0; k < registrations.get(i).get(j).size(); k++) {
                    System.out.print(category[i] + "/" + eventNames.get(i).get(j) + " :");
                    System.out.println(registrations.get(i).get(j).get(k));

                }
            }

        }
    }

    public static void SortParticipants() {
        for (int i = 0; i < category.length; i++) {
            for (int j = 0; j < eventNames.get(i).size(); j++) {
                if (sort == 'A') {
                    Collections.sort(registrations.get(i).get(j));

                } else {
                    Collections.sort(registrations.get(i).get(j), Collections.reverseOrder());
                }
            }
        }
    }

    public static void prompt() {
        do {
            System.out.println("enter A/a for ascending or D/d for descending: ");
            sort = Character.toUpperCase(input.next().charAt(0));
        } while (sort != 'A' && sort != 'D');

    }

    public static void CounterMethod(int [][]counter) {

        for (int i = 0; i < category.length; i++) {
            System.out.println("--------------------");
            for (int j = 0; j < eventNames.get(i).size(); j++) {
                System.out.println(
                        category[i] + "/" + eventNames.get(i).get(j) + " has " + counter[i][j] + " participants");
            }

        }
    }

}
