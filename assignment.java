// Before starting the program please read the README.md
//package Assignment;

import java.util.*;

public class assignment {
    static Scanner input = new Scanner(System.in);
    static int choice;
    // static int choice2;

    static String[] category = { "Music", "Art Exhibitions", "literature & Poetry" };
    static int[] categorySize = new int[3];// instead of 3 category.length could've been used as well
    static int Cchoice;
    static int Echoice;
    static String PNameId;
    static int Pcounter = 0;
    static int Psearch;
    static char sort;
    static boolean hasparticipants = true;
    static int max = 0;
    static int maxIndex = -1;
    static int maxE;
    static int maxER = 0;
    static int maxEC = 0;

    // this Pfind is used to get the recent registration
    static String Pfind;

    static boolean Cchosen = false;
    static ArrayList<ArrayList<String>> eventNames = new ArrayList<>();
    static ArrayList<ArrayList<ArrayList<String>>> registrations = new ArrayList<>();
    // we created an array to keep track of everything's counter
    // we used get(0) for the events name because every category have 4 events

    public static void main(String[] args) {
        Eventdecleration();
        RegistrationDecleration();
        // this 2D array is used to keep track of number of participants in each
        // category and events .
        // even though it is isn't perfectly dynamic, it does the job very well since
        // every category has 4 events.
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
                    if (Pcounter == 0) {
                        System.out.println("there are no registrations to search for");
                    } else {
                        SearchValidation();
                        String result = SearchByValue();
                        if (result == null) {
                            System.out.println("there is no paritcipants with that id");
                        } else {
                            System.out.println(result);
                        }
                    }

                    break;
                case 7:
                    if (Pcounter == 0) {
                        System.out.println("there are no registrations");
                    } else {
                        ShowRecent();
                    }

                    break;

                case 8:

                    if (Pcounter == 0) {
                        System.out.println("there no participants registered");
                    } else {
                        Summary(counter);
                    }
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

            // if the name doesn't start with letter and end with number force retry
             if (!PNameId.matches("[A-Za-z]+-\\d+")) {
            System.out.println(" Invalid format. Use letters-id (e.g. name-123).");
            PNameId = ""; // force retry
        }
        } while (PNameId.isEmpty());
    }

    // create a method to add the name+id
    public static void Register(int[][] counter) {

        EnterNameID();
        Pcounter++;
        Pfind = Integer.toString(Pcounter);
        // we used the Pfind in order to tie the counter with the names and then find
        // the highest counter which equals the recent registration
        PNameId = Pfind + "-" + PNameId;
        registrations.get(Cchoice).get(Echoice).add(PNameId);

        counter[Cchoice][Echoice] = counter[Cchoice][Echoice] + 1;
        categorySize[Cchoice] = categorySize[Cchoice] + 1;
    }

    // create a method to remove a name+id
    public static void Remove(int[][] counter) {
        boolean removed = false;
        if (registrations.get(Cchoice).get(Echoice).isEmpty()) {
            System.out.println("No participants in this event to remove.");
        } else {
            EnterNameID();
            for (int i = 0; i < registrations.get(Cchoice).get(Echoice).size(); i++) {
                String Participant = registrations.get(Cchoice).get(Echoice).get(i);
                int index = Participant.indexOf("-");
                String cleaned = Participant.substring(index + 1);
                if (cleaned.equals(PNameId)) {
                    registrations.get(Cchoice).get(Echoice).remove(i);

                    removed = true;
                    break;
                }

            }
            if (removed) {
                System.out.println("Participant removed successfully!");
                Pcounter--;
                Pfind = Integer.toString(Pcounter);
                counter[Cchoice][Echoice] = counter[Cchoice][Echoice] - 1;
                categorySize[Cchoice] = categorySize[Cchoice] - 1;

                removed = false;
            } else {
                System.out.println("Participant not found!");
            }
        }
    }

    public static void DisplayParticipants() {
        // this if condition ensures there are participants and the one in the ELSE
        // statement just resets it back to true if there are any
        if (Pcounter == 0) {
            hasparticipants = false;
        } else {
            hasparticipants = true;
            for (int i = 0; i < category.length; i++) {
                for (int j = 0; j < eventNames.get(i).size(); j++) {

                    for (int k = 0; k < registrations.get(i).get(j).size(); k++) {
                        System.out.print(category[i] + "/" + eventNames.get(i).get(j) + " :");
                        System.out.println(
                                registrations.get(i).get(j).get(k).replaceAll("^[0-9]+", "").replaceFirst("-", ""));
                        // we user .replaceall to not show the Pcounter at the begining sice its only
                        // use is for case 7. and the replacefirst is to make sure that the counter
                        // doesn't get mixed with the name.

                    }
                }

            }
        }
    }

    public static void SortParticipants() {
        for (int i = 0; i < category.length; i++) {
            for (int j = 0; j < eventNames.get(i).size(); j++) {
                // we use this built in object to handle the sorting regardless of the counter
                // at the start of the name
                Collections.sort(registrations.get(i).get(j), new Comparator<String>() {

                    // and this is a built in method for the comparator
                    public int compare(String s1, String s2) {
                        // Remove everything before the first "-"
                        String name1 = s1.replaceFirst("^[0-9]+-", "");
                        String name2 = s2.replaceFirst("^[0-9]+-", "");

                        if (sort == 'A') {
                            return name1.compareToIgnoreCase(name2); // ascending regardless of capitalization
                        } else {
                            return name2.compareToIgnoreCase(name1); // descending regardless of capitalization
                        }
                    }
                });
            }
        }
    }

    public static void prompt() {
        do {
            System.out.println("enter A/a for ascending or D/d for descending: ");
            sort = Character.toUpperCase(input.next().charAt(0));
        } while (sort != 'A' && sort != 'D');

    }

    public static void CounterMethod(int[][] counter) {

        for (int i = 0; i < category.length; i++) {
            System.out.println("--------------------");
            for (int j = 0; j < eventNames.get(i).size(); j++) {
                System.out.println(
                        category[i] + "/" + eventNames.get(i).get(j) + " has " + counter[i][j] + " participants");
            }

        }
    }

    public static String SearchByValue() {
        for (int i = 0; i < category.length; i++) {
            for (int j = 0; j < eventNames.get(i).size(); j++) {
                for (int k = 0; k < registrations.get(i).get(j).size(); k++) {
                    // we created a variable str to search for the string version of the id
                    String str = Integer.toString(Psearch);
                    if (registrations.get(i).get(j).get(k).endsWith(str)) {
                        return category[i] + "/" + eventNames.get(i).get(j) + ":"
                                + registrations.get(i).get(j).get(k).replaceAll("^[0-9]+", "").replaceFirst("-", "");

                    }
                }
            }

        }
        return null;
    }

    public static void SearchValidation() {
        do {
            try {
                System.out.print("Please enter an id to search for ");
                Psearch = input.nextInt();
                System.out.println("-------------------------");

            } catch (InputMismatchException e) {
                System.out.println("Error: That's not a valid input. Try again.");
                input.next();
                Psearch = -1;
            }
        } while (Psearch == -1);

    }

    public static void ShowRecent() {

        for (int i = 0; i < category.length; i++) {
            for (int j = 0; j < eventNames.get(i).size(); j++) {
                for (int k = 0; k < registrations.get(i).get(j).size(); k++) {
                    if (registrations.get(i).get(j).get(k).startsWith(Pfind)) {
                        System.out.print(category[i] + "/" + eventNames.get(i).get(j) + " :");
                        System.out.println(
                                registrations.get(i).get(j).get(k).replaceAll("^[0-9]+", "").replaceFirst("-", ""));
                    }

                }

            }

        }

    }

    public static void Summary(int[][] counter) {
        max = categorySize[0];
        maxIndex = 0;

        for (int i = 1; i < categorySize.length; i++) {
            if (categorySize[i] > maxE) {
                max = categorySize[i]; // the number of paritcipants in the highest category
                maxIndex = i; // the index to get the category
            }
        }
        maxE = counter[0][0];
        maxER = 0;
        maxEC = 0;
        for (int i = 0; i < category.length; i++) {
            for (int j = 0; j < counter[i].length; j++) {
                if (counter[i][j] > maxE) {
                    maxE = counter[i][j];
                    maxER = i;
                    maxEC = j;
                }

            }
        }

        System.out.println("Category(s) with the highest number of participants (" + max + "):");
        for (int i = 0; i < categorySize.length; i++) {
            if (categorySize[i] == max) {
                System.out.println("-" + category[i]);
            }
        }
        System.out.println(
                "Event(s) with the highest number of participants (" + maxE + "):");
        for (int i = 0; i < counter.length; i++) {
            for (int j = 0; j < counter[i].length; j++) {
                if (counter[i][j] == maxE) {
                    System.out.println("-" + eventNames.get(i).get(j) );
                }
            }
        }
    }
}
