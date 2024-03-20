/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
/**
 *
 * @author michael.roy-diclemen
 */
public class VirtualPet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        System.out.println("            |\\_/|        D\\___/\\\n" +
                            "            (0_0)         (0_o)\n" +
                            "           ==(Y)==         (V)\n" +
                            "----------(u)---(u)----oOo--U--oOo---\n" +
                            "__|_______|_______|_______|_______|___");
        
        System.out.println("               PET ISLAND");
        
        System.out.println("\n1. Start\n2. Instructions\n3. Exit");
        System.out.print("Please make a selection(1,2,3): ");
        int menuSelection = input.nextInt();
        

        switch (menuSelection) {
            case 1: 
                System.out.println("\nSelect your pet: \n1. Dog\n2. Cat\n3. Bear");
                System.out.print("Your selection(1,2,3): ");
                int petSelection = input.nextInt();
                input.nextLine();
                String pet = "";
                
                switch (petSelection) {
                    case 1: 
                        pet = "Dog";
                        break;
                    case 2: 
                        pet = "Cat";
                        break;
                    case 3: 
                        pet = "Bear";
                        break;
                    default: 
                        System.out.println("Bad Input");
                        System.exit(0);
                }
                
                System.out.println("You chose: " + pet);
                
                break;
                
            case 2:
                
            case 3: 
                System.exit(0); 
                break;
                
            default: 
                System.out.println("Bad Input");
                
        }
    }
    
}
