
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
public class FoodTrayStackApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoodStack foodStack = new FoodStack();
        
        boolean running = true;
        
        while (running) {            
            //System.out.println("\n++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(  "+ === Food Storage Management System === +");
            System.out.println(  "+ > 1. Add food                          +");
            System.out.println(  "+ > 2. Remove top food                   +");
            System.out.println(  "+ > 3. Peek top foot                     +");
            System.out.println(  "+ > 4. Display all food                  +");
            System.out.println(  "+ > 5. Search food                       +");
            System.out.println(  "+ > 6. Show number of stored items       +");
            System.out.println(  "+ > 7. Exit                              +");
            System.out.println(  "+ ====================================== +");
            //System.out.println(  "++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("\nChoose an option: ");
            
            int option = readInt(scanner);
            
            switch (option) {
                case 1 -> addFood(scanner, foodStack);
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
                case 4 -> foodStack.display();
                case 5 -> searchFood(scanner, foodStack);
                case 6 -> System.out.println("\nStored items: " + foodStack.size() + "/8\n");
                case 7 -> {
                    running = false;
                    System.out.println("\nExiting application.\n");
                }
                default -> System.out.println("\nInvalid option. Please choose from 1 to 7.");
            }
        }
        scanner.close();
    }
    
    //Read and validate an integer
    private static int readInt(Scanner scanner){
        while(true){
            String input = scanner.nextLine().trim();
            
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("\nInvalid input. Please enter a number valid: ");
            }
        }
    }
    
    //Read and validate a positive weight
    private static double readPositiveDouble(Scanner scanner){
        while (true) {
            System.out.print("Enter weight in grams: ");
            String input = scanner.nextLine().trim();
            
            try {
                double number = Double.parseDouble(input);
                if(number > 0){
                    return number;
                }
                System.out.println("Input must be a positive number\n");
            } catch (NumberFormatException e) {
                System.out.print("\nInvalid input. Please enter a number valid: ");
            }
        }
    }
    
    //Read and validate best-before date in yyyy-MM-dd format 
    private static LocalDate readDate(Scanner scanner){
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusWeeks(2);
        
        while(true){
            System.out.print("Enter best-before date (yyyy-MM-dd): ");
            
            String input = scanner.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input);
                if (date.isBefore(today)) {
                    System.out.println("\nInvalid date. The data cannot be in the past.\n");
                    continue;
                }
                if (date.isAfter(maxDate)){
                    System.out.println("\nInvalid date. The maximum is two weeks from today.\n");
                    continue;
                }
                return date;
                
            } catch (Exception e) {
                System.out.println("Invalid date. Example: " + maxDate.toString());
            }
        }
        
    }
    
    //Add a new food item
    private static void addFood(Scanner scanner, FoodStack foodStack){
        System.out.println("\n+ ========== Select food type ========== +");
        System.out.println(  "+ > 1. Burger                            +");
        System.out.println(  "+ > 2. Pizza                             +");
        System.out.println(  "+ > 3. Fries                             +");
        System.out.println(  "+ > 4. Sandwich                          +");
        System.out.println(  "+ > 5. Hotdog                            +");
        System.out.println(  "+ ====================================== +");
        
        System.out.print("\nChoose food type: ");
        int type = readInt(scanner);
        
        if (type < 1 || type > 5) {
            System.out.println("Invalid food type.");
            return;
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
            case 1 -> food = new Burger(name, weight, bestBefore, storageTime);
            case 2 -> food = new Pizza(name, weight, bestBefore, storageTime);
            case 3 -> food = new Fries(name, weight, bestBefore, storageTime);
            case 4 -> food = new Sandwich(name, weight, bestBefore, storageTime);
            case 5 -> food = new Hotdog(name, weight, bestBefore, storageTime);
            default -> {
                return;
            }
        }
        foodStack.push(food);
    }
    
    //Search for food by name
    private static void searchFood(Scanner scanner, FoodStack foodStack){
        System.out.print("Enter food name to search: ");
        String name = scanner.nextLine();
        
        Food foundFood = foodStack.search(name);
        
        if (foundFood != null) {
            System.out.println("\nFood found: " + foundFood + "\n");
        }
    }
}

