/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author diogodias
 */
public class FoodStack implements StackADT<Food> {

    private static final int CAPACITY = 8;

    private final Food[] items;

    private int top;

    // Constructor
    public FoodStack() {
        items = new Food[CAPACITY];
        top = -1;
    }

    // Push element
    @Override
    public void push(Food item) {
        if (item == null) {
            System.out.println("Food cannot be null");
            return;
        }
        if (isFull()) {
            System.out.println("Stack Overflow");
            return;
        }
        items[++top] = item;
        System.out.println(item.getName() + " pushed to stack.");
    }

    // Pop element
    @Override
    public Food pop() {
        if (isEmpty()) {
            System.out.println("Cannot remove food beacuse the stack is empty");
            return null;
        }
        Food removedFood = items[top];
        items[top] = null;
        top--;

        return removedFood;
    }

    // Peek top element
    @Override
    public Food peek() {
        if (isEmpty()) {
            System.out.println("Cannot peek because the stack is empty.");
            return null;
        }
        return items[top];
    }

    // Check if empty
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if full
    @Override
    public boolean isFull() {
        return top == CAPACITY - 1;
    }

    // Display stack
    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("The food stack is empty.");
            return;
        }

        System.out.println("Food stack (TOP to BOTTOM):");

        for (int i = top; i >= 0; i--) {
            System.out.println(items[i]);
        }
    }
    
    //Get number of elements in the stack
    public int size(){
        return top + 1;
    }
    
    public Food search(String name){
        if (name == null || name.trim().isEmpty()){
            System.out.println("Please enter a valid food name.");
            return null;
        }
        String searchName = name.trim();
        
        for (int i = top; i >= 0; i--) {
            if (items[i].getName().equalsIgnoreCase(searchName)){
                return items[i];
            }
        }
        System.out.println("Food not found.");
        return null;
    }

}
