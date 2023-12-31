// importing the scanner class and the inputmismatchexception class
import java.util.Scanner;
import java.util.InputMismatchException;
// declaring the class
public class EventSeatingPlanner {
// declaring constants max people and group sizes using fixed values
    private static final int MAX_PEOPLE = 56;
    private static final int[] GROUP_SIZES = {2, 3, 4, 5, 6};
    // main method
    public static void main(String[] args) {
        //using scanner to get user input for entering  the program menu
        Scanner scanner = new Scanner(System.in);
        int[] groupCounts = new int[5];

        while (true) {
            System.out.println("\nEvent Seating Planner");
            System.out.println("1. Enter total number of people and their group details");
            System.out.println("2. Create seating plan");
            System.out.println("3. Exit");
            System.out.print("Enter a choice: ");
            // checking the scanner input for potential errors by using try and catch
            // if the input is not a number, the program will throw an error and ask the user to enter a number
            // if the input is a negative number, the program will throw an error and ask the user to enter a number
            int choice;
            choice = -1;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    if (choice < 0) {
                        throw new InputMismatchException("Wrong number selected.");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid selection. Please enter a number.");
                    scanner.next();
                }
            }
            // using switch statement to create a menu for the user to choose from
            switch (choice) {
                // case 1 is used to get the total number of people attending the event
                // if the total number of people is less than the max number of people,
                // the program will ask the user to enter the number of people in each group
                case 1:
                    int totalAttendees = getAttendeesCount(scanner);
                    if (totalAttendees <= MAX_PEOPLE) {
                        getGroupCounts(scanner, totalAttendees, groupCounts);
                    } else {
                        System.out.println("Error: Total attendees exceeds the maximum limit of " + MAX_PEOPLE);
                    }
                    break;
                    // case 2 is used to create the seating plan
                    // the program will display the number of tables of 6 and 8 needed for the event
                case 2:
                    planSeating(groupCounts);
                    break;
                    // case 3 is used to exit the program
                    // the program will display a message to the user that the program is exiting
                case 3:
                    System.out.println("Exiting program...");
                    return;
                    // default is used to display a message to the user that the selection is invalid
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }
    // method to get the total number of people attending the event
    // the program will ask the user to enter the total number of people attending the event
    public static int getAttendeesCount(Scanner scanner) {
        while (true) {
            // using try and catch to check for potential errors
            // if the input is not a number, the program will throw an error and ask the user to enter a number
            try {
                System.out.print("Enter the total number of people attending the event: ");
                int attendees = scanner.nextInt();
                if (attendees < 0) {
                    throw new InputMismatchException("Negative number inserted.");
                }
                return attendees;
            } catch (InputMismatchException e) {
                System.out.println("Invalid selection. Please try again.");
                scanner.next();
            }
        }
    }
    // method to get the number of people in each group
    // the program will ask the user to enter the number of people in each group
    // the program will display the total number of groups and the total number of people attending the event
    public static void getGroupCounts(Scanner scanner, int totalAttendees, int[] groupCounts) {
        int attendeesAssigned = 0;
        for (int i = 0; i < GROUP_SIZES.length && attendeesAssigned < totalAttendees; i++) {
            while (true) {
                // using try and catch to check for potential errors
                try {
                    System.out.print("Please enter the number of groups size " + GROUP_SIZES[i] + ": ");
                    groupCounts[i] = scanner.nextInt();
                    if (groupCounts[i] < 0) {
                        throw new InputMismatchException("Negative number detected.");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid selection. Please try again.");
                    scanner.next();
                }
            }
            // calculating the total number of people attending the event
            attendeesAssigned += groupCounts[i] * GROUP_SIZES[i];
        }
        // checking if the total number of people in groups matches the total number of people attending the event
        if (attendeesAssigned != totalAttendees) {
            System.out.println("Error: The total of attendees in groups doesn't match the total attendees of the event.");
        } else {

            displayGroupDetails(groupCounts);
            System.out.println("Press Enter to return to the main menu.");
            // using scanner to get user input for entering the program menu
            scanner.nextLine();

            scanner.nextLine();
        }
    }
    // method to display the total number of groups and the total number of people attending the event
    public static void displayGroupDetails(int[] groupCounts) {
        int totalGroups = 0;
        int totalPeople = 0;
        // using for loop to calculate the total number of groups and the total number of people attending the event
        for (int i = 0; i < GROUP_SIZES.length; i++) {
            totalGroups += groupCounts[i];
            totalPeople += groupCounts[i] * GROUP_SIZES[i];
        }
        // displaying the total number of groups and the total number of people attending the event
        System.out.println("\nTotal number of groups: " + totalGroups);
        System.out.println("Total number of people attending: " + totalPeople);
    }
    // method to create the seating plan
    public static void planSeating(int[] groupCounts) {
        int tablesOf6 = 0;
        int tablesOf8 = 0;
        // using for loop to calculate the number of tables of 6 and 8 needed for the event
        // by multiplying the number of groups by the group size
        for (int i = 0; i < GROUP_SIZES.length; i++){
            int totalPeoplePerGroup = GROUP_SIZES[i] * groupCounts[i];
            while (totalPeoplePerGroup > 0){
                if (totalPeoplePerGroup == 8){
                    tablesOf8++;
                    totalPeoplePerGroup -= 8;
                } else if (totalPeoplePerGroup == 6){
                    tablesOf6++;
                    totalPeoplePerGroup -= 6;
                } else {
                    tablesOf6++;
                    totalPeoplePerGroup = 0;
                }
            }
        }

        System.out.println("Tables of 6 needed: " + tablesOf6);
        System.out.println("Tables of 8 needed: " + tablesOf8);
    }
}
// end of program