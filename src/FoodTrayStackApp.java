
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
                case 1:
                    addFood(scanner, foodStack);
                    break;
                case 2:
                    Food removedFood = foodStack.pop();
                    if (removedFood != null) {
                        System.out.println("Removed food " + removedFood.getName());
                        
                    }
                    break;
                case 3:
                    Food topFood = foodStack.peek();
                    if (topFood != null) {
                        System.out.println("Top food: " + topFood);
                    }
                    break;
                case 4:
                    foodStack.display();
                    break;
                case 5:
                    searchFood(scanner, foodStack);
                    break;
                case 6:
                    System.out.println("Stored items: " + foodStack.size() + "/8");
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("\nInvalid option. Please choose from 1 to 7.");
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
            System.out.println("Enter weight in grams: ");
            String input = scanner.nextLine().trim();
            
            try {
                double number = Double.parseDouble(input);
                if(number > 0){
                    return number;
                }
                System.out.println("Input must be a positive number");
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number valid: ");
            }
        }
    }
    
    //Read a date in yyyy-MM-dd format 
    private static LocalDate readDate(Scanner scanner){
        while(true){
            System.out.println("Enter best-before date (yyyy-MM-dd): ");
            
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input);
            } catch (Exception e) {
                System.out.println("Invalis date. Example: 2026-10-11");
            }
        }
        
    }
    
    //Add a new food item
    private static void addFood(Scanner scanner, FoodStack foodStack){
        System.out.println("\nSelect food type:");
        System.out.println("1. Burger");
        System.out.println("2. Pizza");
        System.out.println("3. Fries");
        System.out.println("4. Sandwich");
        System.out.println("5. Hotdog");
        
        System.out.println("Choose food type: ");
        int type = readInt(scanner);
        
        if (type < 1 || type > 5) {
            System.out.println("Invalid food type.");
            return;
        }
        System.out.println("Enter food name: ");
        String name = scanner.nextLine().trim();
        
        while (name.isEmpty()) {            
            System.out.println("Food name cannot be empty. Try again: ");
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
        System.out.println("Enter food name to search: ");
        String name = scanner.nextLine();
        
        Food foundFood = foodStack.search(name);
        
        if (foundFood != null) {
            System.out.println("Food found: " + foundFood);
        }
    }
}

