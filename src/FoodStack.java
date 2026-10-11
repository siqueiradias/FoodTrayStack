
import java.util.NoSuchElementException;
import java.util.Objects;

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

    public FoodStack() {
        items = new Food[CAPACITY];
        top = -1;
    }

    @Override
    public void push(Food item) {
        if (item == null) {
            throw new NullPointerException("Food cannot be null");
        }
        if (isFull()) {
            throw new IllegalStateException("The food stack is full. Maximum capacity is 8");
        }
        items[++top] = item;
    }

    @Override
    public Food pop() {
        if (isEmpty()){
            throw new NoSuchElementException("Cannot remove food beacuse the stack is empty");
        }
        Food removedFood = items[top];
        items[top] = null;
        top--;
        
        return removedFood;
    }

    @Override
    public Food peek() {
        if(isEmpty()){
            throw new NoSuchElementException("Cannot peek because the stack is empty.");
        }
        return items[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == CAPACITY - 1;
    }

    @Override
    public void display() {
        if (isEmpty()){
            System.out.println("The food stack is empty.");
            return;
        }
        
        System.out.println("Food stack (TOP to BOTTOM):");
        
        for (int i = top; i >= 0; i--) {
            System.out.println(items[i]);
        }
    }

}
