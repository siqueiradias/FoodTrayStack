/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author diogodias
 */
public class FoodCircularQueue implements QueueADT<Food> {

    private static final int CAPACIRY = 8;

    private Food[] items;
    private int front;
    private int rear;
    private int size;

    public FoodCircularQueue() {
        items = new Food[CAPACIRY];
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
        rear = (rear + 1) % CAPACIRY;
        items[rear] = item;
        size++;
        System.out.println("\n" + item.getName() + "added to queue.\n");
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
        front = (front + 1) % CAPACIRY;
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
        return size == CAPACIRY;
    }

    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("\nQueue is empty!!\n");
            return;
        }

        System.out.println("\nFood queue (FRONT to REAR):");
        for (int i = front; i <= rear; i++) {
            int index = (front + i) % CAPACIRY;
            System.out.println(items[index]);
        }
        System.out.println();
    }
    
    public int size(){
        return size;
    }
}
