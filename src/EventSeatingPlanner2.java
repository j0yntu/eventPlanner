// importing the scanner class and the inputmismatchexception class
import java.util.*;
import java.util.InputMismatchException;
import java.util.Scanner;
// declaring the class
public class EventSeatingPlanner2 {
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
            System.out.println("1. Enter total number of people and their group details/Plan the Event");
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
                    System.exit(0);
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

          int[] totalPeoplePerGroup = new int[groupCounts.length];
          for (int i = 0; i < groupCounts.length; i++) {
              totalPeoplePerGroup[i] = groupCounts[i] * GROUP_SIZES[i];
          }
        // 1. Assign groups of size 8 to tables of 8
        tablesOf8 += groupCounts[4];
        totalPeoplePerGroup[4] -= groupCounts[4] * GROUP_SIZES[4];

        // 2. Combine groups of size 4 for tables of 8
        while (groupCounts[3] >= 2) {
            tablesOf8++;
            groupCounts[3] -= 2;
        }

        // 3. Combine groups of size 6 with groups of size 2
        while (groupCounts[2] > 0 && groupCounts[0] > 0) {
            tablesOf8++;
            groupCounts[2]--;
            groupCounts[0]--;
        }

        // 4. Combine groups of size 5 with groups of size 3
        while (groupCounts[3] > 0 && groupCounts[1] > 0) {
            tablesOf8++;
            groupCounts[3]--;
            groupCounts[1]--;
        }

        // 5. Assign remaining groups of size 6 to tables of 6
        tablesOf6 += groupCounts[2];
        groupCounts[2] = 0;

        // 6. Combine groups of size 4 with groups of size 2 for tables of 6
        while (groupCounts[3] > 0 && groupCounts[0] > 0) {
            tablesOf6++;
            groupCounts[3]--;
            groupCounts[0]--;
        }

        // 7. Combine groups of size 3 for tables of 6
        while (groupCounts[1] >= 2) {
            tablesOf6++;
            groupCounts[1] -= 2;
        }

        // 8. Allocate remaining groups to tables of 6
        for (int i = 0; i < groupCounts.length; i++) {
            while (groupCounts[i] > 0) {
                tablesOf6++;
                groupCounts[i]--;
            }
        }

        System.out.println("Tables of 6 needed: " + tablesOf6);
        System.out.println("Tables of 8 needed: " + tablesOf8);
    }
}