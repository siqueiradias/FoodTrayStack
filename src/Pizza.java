
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author diogodias
 */
public class Pizza extends Food{
    
    public Pizza(String name, double weight, LocalDate bestBefore, LocalDateTime storageTime) {
        super(name, weight, bestBefore, storageTime);
    }
    
}
