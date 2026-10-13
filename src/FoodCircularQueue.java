/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author diogodias
 */
public class FoodCircularQueue implements QueueADT<Food> {

    private static final int CAPACITY = 8;

    private Food[] items;
    private int front;
    private int rear;
    private int size;

    public FoodCircularQueue() {
        items = new Food[CAPACITY];
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public void enqueue(Food item) {
        if (item == null) {
            System.out.println("\nFood cannot be null.\n");
            return;
        }
        if (isFull()) {
            System.out.println("\nQueue is full.\n");
            return;
        }
        //capacity=8
        //rear (7+1)%8===>0
        rear = (rear + 1) % CAPACITY;
        items[rear] = item;
        size++;
        System.out.println("\n" + item.getName() + " added to queue.\n");
    }

    @Override
    public Food dequeue() {
        if (isEmpty()) {
            System.out.println("\nQueue is empty!!\n");
            return null;
        }
        //actual delete
        Food removedFood = items[front];
        items[front] = null;
        front = (front + 1) % CAPACITY;
        size--;
        return removedFood;
    }

    @Override
    public Food peek() {
        if (isEmpty()) {
            System.out.println("\nQueue is empty!!\n");
            return null;
        }
        return items[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == CAPACITY;
    }

    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("\nQueue is empty!!\n");
            return;
        }

        System.out.println("\nFood queue (FRONT to REAR):");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % CAPACITY;
            System.out.println(items[index]);
        }
        System.out.println();
    }

    @Override
    public int size() {
        return size;
    }

    public void search(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nPlease enter a valid food name.\n");
            return;
        }

        String searchName = name.trim();
        boolean found = false;

        for (int i = 0; i < size; i++) {
            int index = (front + i) % CAPACITY;
            if (items[index].getName().equalsIgnoreCase(searchName)) {
                if (!found) {
                    System.out.println("\nMatching food items:");
                }
                System.out.println(items[index]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nFood not found.\n");
        } else {
            System.out.println();
        }
    }
}
