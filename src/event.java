import java.util.Scanner;
public class event {
    private static final int EventMaxPeople = 56;
    private static final int Group_SIZEof2 = 2;
    private static final int Group_SIZEof3 = 3;
    private static final int Group_SIZEof4 = 4;
    private static final int Group_SIZEof5 = 5;
    private static final int Group_SIZEof6 = 6;
    private static final int Table_SIZEof6 = 6;
    private static final int Table_SIZEof8 = 8;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //menu
        while (true) {
            System.out.println("Event Seating Planner");
            System.out.println("1. Plan a new event");
            System.out.println("2. Exit");
            System.out.print("Enter a choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    planEvent(scanner);
                    break;
                case 2:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    //planner
    public static void planEvent(Scanner scanner) {
        System.out.println("Enter the number of people attending the event:");
        int eventAttend = scanner.nextInt();

        if (eventAttend > EventMaxPeople) {
            System.out.println("Room capacity exceeded of " + EventMaxPeople);
            return;
        } else {
            System.out.println("Thank you!");
        }
        // Gropu numbers
        int[] groupSizes = new int[]{Group_SIZEof2, Group_SIZEof3, Group_SIZEof4, Group_SIZEof5, Group_SIZEof6};
        int[] groups = new int[groupSizes.length];
        int totalGroups = 0;
        for (int i = 0; i < groupSizes.length; i++) {
            System.out.println("How many groups of " + groupSizes[i] + " are attending the event?");
            groups[i] = scanner.nextInt();
            totalGroups += groups[i] * groupSizes[i];
        }
        //
        if (totalGroups != eventAttend) {
            System.out.println("Total number added from groups are higher that people attending.");
            return;
        }
        // Try to seat the groups first 8
        int tablesOf8 = 0;
        int tablesOf6 = 0;
        for (int i = groups.length - 1; i >= 0; i--) {
            while (groups[i] > 0) {
                if (groupSizes[i] <= Table_SIZEof8) {
                    tablesOf8++;
                    groups[i]--;
                } else if (groupSizes[i] <= Table_SIZEof6) {
                    tablesOf6++;
                    groups[i]--;
                }
            }
        }
        System.out.println("Used tables of 8: " + tablesOf8);
        System.out.println("Used tables of 6: " + tablesOf6);
    }
}