import java.util.Scanner;
import java.util.InputMismatchException;

public class EventSeatingPlanner {

    private static final int MAX_PEOPLE = 56;
    private static final int[] GROUP_SIZES = {2, 3, 4, 5, 6};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] groupCounts = new int[5];

        while (true) {
            System.out.println("\nEvent Seating Planner");
            System.out.println("1. Enter total number of people and their group details");
            System.out.println("2. Create seating plan");
            System.out.println("3. Exit");
            System.out.print("Enter a choice: ");

            int choice = -1;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    if (choice < 0) {
                        throw new InputMismatchException("Negative number detected.");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a non-negative number.");
                    scanner.next();
                }
            }

            switch (choice) {
                case 1:
                    int totalAttendees = getAttendeesCount(scanner);
                    if (totalAttendees <= MAX_PEOPLE) {
                        getGroupCounts(scanner, totalAttendees, groupCounts);
                    } else {
                        System.out.println("Error: Total attendees exceeds the maximum limit of " + MAX_PEOPLE);
                    }
                    break;
                case 2:
                    planSeating(groupCounts);
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static int getAttendeesCount(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter the total number of people attending the event: ");
                int attendees = scanner.nextInt();
                if (attendees < 0) {
                    throw new InputMismatchException("Negative number detected.");
                }
                return attendees;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a non-negative number.");
                scanner.next();
            }
        }
    }

    public static void getGroupCounts(Scanner scanner, int totalAttendees, int[] groupCounts) {
        int attendeesAssigned = 0;
        for (int i = 0; i < GROUP_SIZES.length && attendeesAssigned < totalAttendees; i++) {
            while (true) {
                try {
                    System.out.print("Enter the number of groups of size " + GROUP_SIZES[i] + ": ");
                    groupCounts[i] = scanner.nextInt();
                    if (groupCounts[i] < 0) {
                        throw new InputMismatchException("Negative number detected.");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a non-negative number.");
                    scanner.next();
                }
            }
            attendeesAssigned += groupCounts[i] * GROUP_SIZES[i];
        }

        if (attendeesAssigned != totalAttendees) {
            System.out.println("Error: The sum of attendees in groups doesn't match the total attendees.");
        } else {
            displayGroupDetails(groupCounts);
            System.out.println("Press Enter to return to the main menu.");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public static void displayGroupDetails(int[] groupCounts) {
        int totalGroups = 0;
        int totalPeople = 0;

        for (int i = 0; i < GROUP_SIZES.length; i++) {
            totalGroups += groupCounts[i];
            totalPeople += groupCounts[i] * GROUP_SIZES[i];
        }

        System.out.println("\nTotal number of groups: " + totalGroups);
        System.out.println("Total number of people attending: " + totalPeople);
    }

    public static void planSeating(int[] groupCounts) {
        int tablesOf6 = 0;
        int tablesOf8 = 0;

        tablesOf6 += groupCounts[4];
        tablesOf6 += groupCounts[3];
        groupCounts[4] = 0;
        groupCounts[3] = 0;

        while (groupCounts[2] > 0 && groupCounts[0] > 0) {
            tablesOf8++;
            groupCounts[2]--;
            groupCounts[0]--;
        }

        while (groupCounts[1] > 1) {
            tablesOf8++;
            groupCounts[1] -= 2;
        }

        while (groupCounts[2] > 0) {
            tablesOf8++;
            groupCounts[2]--;
        }

        while (groupCounts[1] > 0 && groupCounts[0] > 0) {
            tablesOf8++;
            groupCounts[1]--;
            groupCounts[0]--;
        }

        tablesOf6 += groupCounts[1];
        tablesOf8 += (int) Math.ceil(groupCounts[0] / 2.0);

        System.out.println("Tables of 6 needed: " + tablesOf6);
        System.out.println("Tables of 8 needed: " + tablesOf8);
    }
}
