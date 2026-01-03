// Before starting the program please read the README.md
//package Assignment;

import java.util.*;

public class assignment {

    static Scanner Input = new Scanner(System.in);
    static int SwitchCaseChoice;
    static String[] Category = { "Music", "Art Exhibitions", "literature & Poetry" };
    static int[] CategorySize = new int[3]; // instead of 3 Category.length could've been used as well
    static int CategoryChoice;
    static int CounterElegbility = 0;
    static int EventChoice;
    static String PersonNameId;
    static int PersonCounter = 0;
    static int PersonSearch;
    static char SortingChoice;
    static boolean HasParticipants = true;
    static int MaxCategory = 0;
    static int MaxEvent;

    static String PersonFind; // this PersonFind is used to get the recent registration
    static boolean CategoryChosen = false;
    static ArrayList<ArrayList<String>> EventNames = new ArrayList<>();
    static ArrayList<ArrayList<ArrayList<String>>> Registrations = new ArrayList<>();
    // we created an array to keep track of everything's Counter
    // we used get(0) for the events name because every Category have 4 events

    public static void main(String[] args) {
        Eventdecleration();
        RegistrationDecleration();
        // this 2D array is used to keep track of number of participants in each
        // Category and events .
        // even though it is isn't perfectly dynamic, it does the job very well since
        // every Category has 4 events.
        int Counter[][] = new int[Category.length][EventNames.get(0).size()];
        do {
            Menu();
            switch (SwitchCaseChoice) {

                case 1:
                    ChooseCategory();
                    CategoryChosen = true;
                    break;

                case 2:
                    // make sure a Category is chosen
                    if (CategoryChosen) {
                        ChooseEvent();
                        Register(Counter);

                    } else {
                        System.out.println("A Category must be chosen in order to move on");
                    }
                    CategoryChosen = false;
                    break;

                case 3:
                    if (PersonCounter > 0) {

                        // make sure a Category is chosen
                        if (CategoryChosen) {
                            ChooseEvent();
                            Remove(Counter);

                        } else {
                            System.out.println("A Category must be chosen in order to move on");

                        }
                        CategoryChosen = false;
                    } else {
                        System.out.println("there is no participants to remove");
                    }
                    break;

                case 4:
                    Prompt();
                    SortParticipants();
                    DisplayParticipants();
                    if (!HasParticipants) {
                        System.out.println("there are no participants");
                    }

                    break;

                case 5:
                    CounterMethod(Counter);

                    break;

                case 6:
                    if (PersonCounter == 0) {
                        System.out.println("there are no Registrations to search for");
                    } else {
                        SearchValidation();
                        SearchByValue();

                    }

                    break;
                case 7:
                    if (PersonCounter == 0) {
                        System.out.println("there are no Registrations");
                    } else {
                        ShowRecent();
                    }

                    break;

                case 8:

                    if (PersonCounter == 0) {
                        System.out.println("there no participants registered");
                    } else {
                        Summary(Counter);
                    }
                    break;
                case 0:
                    System.out.println("Exiting....");
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }

        } while (SwitchCaseChoice != 0);
    }

    // create a method to display the Menu
    public static void Menu() {
        System.out.println("-------------------------");
        System.out.println("1. Choose an event Category");
        System.out.println("2. Choose an event and add a participant");
        System.out.println("3. Remove a participant");
        System.out.println("4. Display al registered participant");
        System.out.println("5. Display total number of Registrations");
        System.out.println("6. Search for a participant");
        System.out.println("7. Display recent Registrations");
        System.out.println("8. Generate a summary");
        System.out.println("Enter (0) to exit ");
        System.out.println("-------------------------");
        // used try and catch to avoid InputMismatchException
        try {
            System.out.print("Enter a choice ");
            SwitchCaseChoice = Input.nextInt();
            System.out.println("-------------------------");

        } catch (InputMismatchException e) {
            System.out.println("Error: That's not a valid Choice. Try again.");
            Input.next();
            SwitchCaseChoice = -1;
        }

    }

    // create a method to display the Category
    public static void DisplayCategory() {
        for (int i = 0; i < Category.length; i++) {
            System.out.println((i + 1) + "- " + Category[i]);
        }

    }

    // create a method to get the chosec Category
    public static void ChooseCategory() {
        DisplayCategory();
        do {
            try {
                System.out.print("choose a Category: ");
                CategoryChoice = Input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input ");
                Input.nextLine();
                CategoryChoice = -1;
            }

        } while (CategoryChoice < 1 || CategoryChoice > 3);
        CategoryChoice = CategoryChoice - 1; // to get the index of that Category
    }

    public static void Eventdecleration() {
        for (int c = 0; c < Category.length; c++) {
            EventNames.add(new ArrayList<>()); // create each empty list for each Category
        }

        // Add 4 events per Category
        EventNames.get(0).add("Traditional Music Night"); // Music
        EventNames.get(0).add("Youth Band Festival");
        EventNames.get(0).add("Solo Instrument Show");
        EventNames.get(0).add("Cultural Rhythm Jam");

        EventNames.get(1).add("Modern Art Gallery"); // Art
        EventNames.get(1).add("Photography Showcase");
        EventNames.get(1).add("Kurdish Calligraphy");
        EventNames.get(1).add("Digital Art Expo");

        EventNames.get(2).add("Poetry Reading Circle"); // Literature
        EventNames.get(2).add("Short Story Evening");
        EventNames.get(2).add("Book Discussion Forum");
        EventNames.get(2).add("Classical Literature Talk");
    }

    public static void DisplayEvents() {

        for (int j = 0; j < EventNames.get(CategoryChoice).size(); j++) {
            System.out.println((j + 1) + "-" + EventNames.get(CategoryChoice).get(j));
        }
        System.out.println("chosen Category is " + (CategoryChoice + 1));

    }

    // create a method to get the chosec Category
    public static void ChooseEvent() {
        DisplayEvents();
        do {
            try {
                System.out.print("choose a event: ");
                EventChoice = Input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                Input.nextLine();
                EventChoice = -1;
            }

        } while (EventChoice < 1 || EventChoice > 4);
        EventChoice = EventChoice - 1; // to get the index of that Event
        System.out.println("chosen event " + (EventChoice + 1));
    }

    // Declaring the registrations
    public static void RegistrationDecleration() {

        // Create the empty registration lists
        for (int c = 0; c < Category.length; c++) {
            Registrations.add(new ArrayList<>()); // create empty list for each categories
            for (int e = 0; e < 4; e++) { // 4 events
                Registrations.get(c).add(new ArrayList<>()); // create empty link inside each Category for participants
                                                             // inside an event

            }
        }

    }

    // create a method to get the name + id from the user
    public static void EnterNameID() {
        Input.nextLine(); // to get rid of the java Input bug

        do {

            System.out.print("enter a name to add/remove like the following example (name-id) NO SPACES: ");
            PersonNameId = Input.nextLine().trim(); // we user trim to get rid of spaces

            // if the name doesn't start with letter and end with number force retry
            if (!PersonNameId.matches("[A-Za-z]+-\\d+")) {
                System.out.println(" Invalid format. Use letters-id (e.g. name-123).");
                PersonNameId = ""; // force retry
            }
        } while (PersonNameId.isEmpty());
    }

    // create a method to add the name+id
    public static void Register(int[][] Counter) {

        EnterNameID();

        PersonCounter++;
        PersonFind = Integer.toString(PersonCounter);
        // we used the PersonFind in order to tie the Counter with the names and then
        // find
        // the highest Counter which equals the recent registration
        PersonNameId = PersonFind + "-" + PersonNameId;

        Registrations.get(CategoryChoice).get(EventChoice).add(PersonNameId);
        CheckElegbility();

        Counter[CategoryChoice][EventChoice] = Counter[CategoryChoice][EventChoice] + 1;
        CategorySize[CategoryChoice] = CategorySize[CategoryChoice] + 1;
    }

    // create a method to remove a name+id
    public static void Remove(int[][] Counter) {
        boolean removed = false;
        if (Registrations.get(CategoryChoice).get(EventChoice).isEmpty()) {
            System.out.println("No participants in this event to remove.");
        } else {
            EnterNameID();
            for (int i = 0; i < Registrations.get(CategoryChoice).get(EventChoice).size(); i++) {
                String Participant = Registrations.get(CategoryChoice).get(EventChoice).get(i);
                int index = Participant.indexOf("-");
                String cleaned = Participant.substring(index + 1);
                if (cleaned.equals(PersonNameId)) {
                    Registrations.get(CategoryChoice).get(EventChoice).remove(i);

                    removed = true;
                    break;
                }

            }
            if (removed) {
                System.out.println("Participant removed successfully!");
                PersonCounter--;
                PersonFind = Integer.toString(PersonCounter);
                Counter[CategoryChoice][EventChoice] = Counter[CategoryChoice][EventChoice] - 1;
                CategorySize[CategoryChoice] = CategorySize[CategoryChoice] - 1;

            } else {
                System.out.println("Participant not found!");
            }
        }
    }

    public static void DisplayParticipants() {
        // this if condition ensures there are participants and the one in the ELSE
        // statement just resets it back to true if there are any
        if (PersonCounter == 0) {
            HasParticipants = false;
        } else {
            HasParticipants = true;
            for (int i = 0; i < Category.length; i++) {
                for (int j = 0; j < EventNames.get(i).size(); j++) {

                    for (int k = 0; k < Registrations.get(i).get(j).size(); k++) {
                        System.out.print(Category[i] + "/" + EventNames.get(i).get(j) + " :");
                        System.out.println(
                                Registrations.get(i).get(j).get(k).replaceAll("^[0-9]+", "").replaceFirst("-", ""));
                        // we user .replaceall to not show the PersonCounter at the begining sice its
                        // only
                        // use is for case 7. and the replacefirst is to make sure that the Counter
                        // doesn't get mixed with the name.

                    }
                }

            }
        }
    }

    public static void SortParticipants() {
        for (int i = 0; i < Category.length; i++) {
            for (int j = 0; j < EventNames.get(i).size(); j++) {
                // we use this built in object to handle the sorting regardless of the Counter
                // at the start of the name
                Collections.sort(Registrations.get(i).get(j), new Comparator<String>() {

                    // and this is a built in method for the comparator
                    public int compare(String s1, String s2) {
                        // Remove everything before the first "-"
                        String name1 = s1.replaceFirst("^[0-9]+-", "");
                        String name2 = s2.replaceFirst("^[0-9]+-", "");

                        if (SortingChoice == 'A') {
                            return name1.compareToIgnoreCase(name2); // ascending regardless of capitalization
                        } else {
                            return name2.compareToIgnoreCase(name1); // descending regardless of capitalization
                        }
                    }
                });
            }
        }
    }

    public static void Prompt() {
        do {
            System.out.println("enter A/a for ascending or D/d for descending: ");
            SortingChoice = Character.toUpperCase(Input.next().charAt(0));
        } while (SortingChoice != 'A' && SortingChoice != 'D');

    }

    public static void CounterMethod(int[][] Counter) {

        for (int i = 0; i < Category.length; i++) {
            System.out.println("--------------------");
            for (int j = 0; j < EventNames.get(i).size(); j++) {
                System.out.println(
                        Category[i] + "/" + EventNames.get(i).get(j) + " has " + Counter[i][j] + " participants");
            }

        }
    }

    public static void SearchByValue() {
        boolean SearchFound = false;
        String str = Integer.toString(PersonSearch);

        for (int i = 0; i < Category.length; i++) {
            for (int j = 0; j < EventNames.get(i).size(); j++) {
                for (int k = 0; k < Registrations.get(i).get(j).size(); k++) {
                    // we created a variable str to search for the string version of the id

                    if (Registrations.get(i).get(j).get(k).endsWith(str)) {
                        System.out.println(Category[i] + "/" + EventNames.get(i).get(j) + ":"
                                + Registrations.get(i).get(j).get(k).replaceAll("^[0-9]+", "").replaceFirst("-", ""));

                        SearchFound = true;
                    }

                }
            }

        }
        if (!SearchFound) {
            System.out.println("No participants were found with ID of "+str);
        }

    }

    public static void SearchValidation() {
        do {
            try {
                System.out.print("Please enter an id to search for ");
                PersonSearch = Input.nextInt();
                System.out.println("-------------------------");

            } catch (InputMismatchException e) {
                System.out.println("Error: That's not a valid Input. Try again.");
                Input.next();
                PersonSearch = -1;
            }
        } while (PersonSearch == -1);

    }

    public static void ShowRecent() {

        for (int i = 0; i < Category.length; i++) {
            for (int j = 0; j < EventNames.get(i).size(); j++) {
                for (int k = 0; k < Registrations.get(i).get(j).size(); k++) {
                    if (Registrations.get(i).get(j).get(k).startsWith(PersonFind)) {
                        System.out.print(Category[i] + "/" + EventNames.get(i).get(j) + " :");
                        System.out.println(
                                Registrations.get(i).get(j).get(k).replaceAll("^[0-9]+", "").replaceFirst("-", ""));
                    }

                }

            }

        }

    }

    public static void Summary(int[][] Counter) {
        MaxCategory = CategorySize[0];

        for (int i = 1; i < CategorySize.length; i++) {
            if (CategorySize[i] > MaxEvent) {
                MaxCategory = CategorySize[i]; // the number of paritcipants in the highest Category
            }
        }
        MaxEvent = Counter[0][0];

        for (int i = 0; i < Category.length; i++) {
            for (int j = 0; j < Counter[i].length; j++) {
                if (Counter[i][j] > MaxEvent) {
                    MaxEvent = Counter[i][j];

                }

            }
        }

        System.out.println("Category(s) with the highest number of participants (" + MaxCategory + "):");
        for (int i = 0; i < CategorySize.length; i++) {
            if (CategorySize[i] == MaxCategory) {
                System.out.println("-" + Category[i]);
            }
        }
        System.out.println(
                "Event(s) with the highest number of participants (" + MaxEvent + "):");
        for (int i = 0; i < Counter.length; i++) {
            for (int j = 0; j < Counter[i].length; j++) {
                if (Counter[i][j] == MaxEvent) {
                    System.out.println("-" + EventNames.get(i).get(j));
                }
            }
        }
    }

    public static void CheckElegbility() {
        for (int i = 0; i < Category.length; i++) {
            for (int j = 0; j < EventNames.get(i).size(); j++) {
                for (int j2 = 0; j2 < Registrations.get(i).get(j).size(); j2++) {
                    if (PersonNameId.equals(Registrations.get(i).get(j).get(j2))) {
                        CounterElegbility++;
                        if (CounterElegbility == 3) {
                            // int code = 100000 + new Random().nextInt(999999);
                            int code = (int) (Math.random() * (999999 - 100000 + 1)) + 100000;
                            System.out.println("Congrats!");
                            System.out.println("You are eligable for a cultural gift");
                            System.out.println("here is your gift: " + code);
                        }
                    }
                }
            }
        }
    }
}
