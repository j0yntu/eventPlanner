import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("Menu");
            System.out.println("1. Enter group details");
            System.out.println("2. Create seating plan");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    getGroupDetails();
                    break;
                case 2:
                    createSeatingPlan();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2 or 3");
            }

        } while(choice != 3);

        sc.close();

    }

    // Task 1
    public static void getGroupDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of people: ");
        int totalPeople = sc.nextInt();

        System.out.print("Enter total number of groups: ");
        int numGroups = sc.nextInt();

        int[] groupSizes = new int[numGroups];

        for(int i=0; i<numGroups; i++) {
            System.out.print("Enter size for group " + (i+1) + ": ");
            groupSizes[i] = sc.nextInt();
        }

        // Display entered data
        System.out.println("\nNumber of people: " + totalPeople);
        System.out.print("Group sizes: ");
        for(int size : groupSizes) {
            System.out.print(size + " ");
        }
    }

    // Task 2
    public static void createSeatingPlan() {

        int[] groupSizes = {2, 5, 3, 6, 4};

        int total = 0;
        for(int size : groupSizes) {
            total += size;
        }

        System.out.println("\nTotal people: " + total);

        int table6 = 0, table8 = 0;
        int remaining = total;

        // Use 6 seat tables
        while(remaining >= 6) {
            table6++;
            remaining -= 6;
        }

        // Use 8 seat tables for remaining
        while(remaining > 0) {
            table8++;
            remaining -= 8;
        }

        System.out.println("Number of 6 seat tables: " + table6);
        System.out.println("Number of 8 seat tables: " + table8);

        // Display seating plan
        int tableNumber = 1;

        for(int i=0; i<table6; i++) {
            System.out.println("Table " + tableNumber + " (6 seats)");
            tableNumber++;
        }

        for(int i=0; i<table8; i++) {
            System.out.println("Table " + tableNumber + " (8 seats)");
            tableNumber++;
        }
    }

}



