
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author diogodias
 */
public class FoodStorageManagementApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FoodStack foodStack = new FoodStack();
        FoodCircularQueue foodQueue = new FoodCircularQueue();

        boolean running = true;

        while (running) {
            System.out.println("\n+ === Food Storage Management System === +");
            System.out.println("+ > 1. Stack (LIFO)                      +");
            System.out.println("+ > 2. Circular Queue (FIFO)             +");
            System.out.println("+ > 3. Exit                              +");
            System.out.println("+ ====================================== +");
            System.out.print("\nChoose an option: ");

            int option = readInt(scanner);

            switch (option) {
                case 1 ->
                    runStackMenu(scanner, foodStack);
                case 2 ->
                    runQueueMenu(scanner, foodQueue);
                case 3 -> {
                    running = false;
                    System.out.println("\nExiting application.\n");
                }
                default ->
                    System.out.println("\nInvalid option. Please choose from 1 to 3.\n");
            }

        }
        scanner.close();
    }

    private static void runStackMenu(Scanner scanner, FoodStack foodStack) {

        boolean isRunStackMenu = true;

        while (isRunStackMenu) {
            System.out.println("\n+ ============ Stack (LIFO) ============ +");
            System.out.println("+ > 1. Add food                          +");
            System.out.println("+ > 2. Remove top food                   +");
            System.out.println("+ > 3. Peek top foot                     +");
            System.out.println("+ > 4. Display all food                  +");
            System.out.println("+ > 5. Search food                       +");
            System.out.println("+ > 6. Show number of stored items       +");
            System.out.println("+ > 7. Return to main menu               +");
            System.out.println("+ ====================================== +");
            System.out.print("\nChoose an option: ");

            int option = readInt(scanner);

            switch (option) {
                case 1 -> {
                    Food food = createFood(scanner);

                    if (food != null) {
                        foodStack.push(food);
                    }
                }
                case 2 -> {
                    Food removedFood = foodStack.pop();
                    if (removedFood != null) {
                        System.out.println("\nRemoved food " + removedFood.getName() + "\n");
                    }
                }
                case 3 -> {
                    Food topFood = foodStack.peek();
                    if (topFood != null) {
                        System.out.println("\nTop food: " + topFood + "\n");
                    }
                }
                case 4 ->
                    foodStack.display();
                case 5 -> {
                    if (foodStack.isEmpty()) {
                        System.out.println("\nStack is empty!!\n");
                    } else {
                        foodStack.search(readSearchName(scanner));
                    }
                }
                case 6 ->
                    System.out.println("\nStored items: " + foodStack.size() + "/8\n");
                case 7 -> {
                    isRunStackMenu = false;
                    System.out.println("\nReturning to main menu.\n");
                }
                default ->
                    System.out.println("\nInvalid option. Please choose from 1 to 7.\n");
            }
        }
    }

    private static void runQueueMenu(Scanner scanner, FoodCircularQueue foodQueue) {

        boolean isRunQueueMenu = true;

        while (isRunQueueMenu) {
            System.out.println("\n+ ======= Circular Queue (FIFO) ======== +");
            System.out.println("+ > 1. Add food                          +");
            System.out.println("+ > 2. Remove front food                 +");
            System.out.println("+ > 3. Peek front food                   +");
            System.out.println("+ > 4. Display all food                  +");
            System.out.println("+ > 5. Search food                       +");
            System.out.println("+ > 6. Show number of stored items       +");
            System.out.println("+ > 7. Return to main menu               +");
            System.out.println("+ ====================================== +");

            System.out.print("\nChoose an option: ");
            int option = readInt(scanner);

            switch (option) {
                case 1 -> {
                    Food food = createFood(scanner);
                    if (food != null) {
                        foodQueue.enqueue(food);
                    }
                }
                case 2 -> {
                    Food removedFood = foodQueue.dequeue();
                    if (removedFood != null) {
                        System.out.println("\nRemoved food: " + removedFood.getName() + "\n");
                    }
                }
                case 3 -> {
                    Food frontFood = foodQueue.peek();
                    if (frontFood != null) {
                        System.out.println("\nFront food: " + frontFood + "\n");
                    }
                }
                case 4 ->
                    foodQueue.display();
                case 5 -> {
                    if (foodQueue.isEmpty()) {
                        System.out.println("\nQueue is empty!!\n");
                    } else {
                        foodQueue.search(readSearchName(scanner));
                    }
                }
                case 6 ->
                    System.out.println(
                            "\nStored items: " + foodQueue.size() + "/8\n"
                    );
                case 7 ->
                    isRunQueueMenu = false;
                default ->
                    System.out.println(
                            "\nInvalid option. Please choose from 1 to 7.\n"
                    );
            }
        }
    }

    //  Read and validate an integer
    private static int readInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("\nInvalid input. Please enter a valid number: ");
            }
        }
    }

    //  Read and validate a positive weight
    private static double readPositiveDouble(Scanner scanner) {
        while (true) {
            System.out.print("Enter weight in grams: ");
            String input = scanner.nextLine().trim();

            try {
                double weight = Double.parseDouble(input);
                if (weight > Double.MAX_VALUE) {
                    System.out.println("\nThe number entered is greater than the supported value.");
                    System.out.println("Try a value lower than " + Double.MAX_VALUE + ".\n");
                    continue;
                }
                if (weight > 0) {
                    return weight;
                }
                System.out.println("\nPlease enter a number greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a valid number.");
            }
        }
    }

    //  Read and validate best-before date in yyyy-MM-dd format 
    private static LocalDate readDate(Scanner scanner) {
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusWeeks(2);

        while (true) {
            System.out.print("Enter best-before date (yyyy-MM-dd): ");

            String input = scanner.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input);
                if (date.isBefore(today)) {
                    System.out.println("\nInvalid date. The data cannot be in the past.\n");
                    continue;
                }
                if (date.isAfter(maxDate)) {
                    System.out.println("\nInvalid date. The maximum is two weeks from today.\n");
                    continue;
                }
                return date;

            } catch (Exception e) {
                System.out.println("\nInvalid date. Example: " + maxDate.toString() + "\n");
            }
        }

    }

    //  Add a new food item
    private static Food createFood(Scanner scanner) {
        System.out.println("\n+ ========== Select food type ========== +");
        System.out.println("+ > 1. Burger                            +");
        System.out.println("+ > 2. Pizza                             +");
        System.out.println("+ > 3. Fries                             +");
        System.out.println("+ > 4. Sandwich                          +");
        System.out.println("+ > 5. Hotdog                            +");
        System.out.println("+ ====================================== +");

        System.out.print("\nChoose food type: ");
        int type = readInt(scanner);

        if (type < 1 || type > 5) {
            System.out.println("Invalid food type.");
            return null;
        }
        System.out.print("Enter food name: ");
        String name = scanner.nextLine().trim();

        while (name.isEmpty()) {
            System.out.print("\nFood name cannot be empty. Try again: ");
            name = scanner.nextLine().trim();
        }

        double weight = readPositiveDouble(scanner);
        LocalDate bestBefore = readDate(scanner);
        LocalDateTime storageTime = LocalDateTime.now();
        Food food;

        switch (type) {
            case 1 ->
                food = new Burger(name, weight, bestBefore, storageTime);
            case 2 ->
                food = new Pizza(name, weight, bestBefore, storageTime);
            case 3 ->
                food = new Fries(name, weight, bestBefore, storageTime);
            case 4 ->
                food = new Sandwich(name, weight, bestBefore, storageTime);
            case 5 ->
                food = new Hotdog(name, weight, bestBefore, storageTime);
            default -> {
                return null;
            }
        }
        return food;
    }

    //  Read a food name to search for
    private static String readSearchName(Scanner scanner) {
        System.out.print("Enter food name to search: ");
        return scanner.nextLine();
    }
}
