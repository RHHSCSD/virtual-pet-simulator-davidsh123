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
        Random random = new Random();
        
        //final variables
        final String CORRECT_USERNAME = "snoopy";
        final String CORRECT_PASSWORD = "toto";
        final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
        final String VOWELS = "aeoiu";
        final int STARTING_POINTS = 20;//20 starting points
        final double CHANCE_DOUBLE_LETTER = 0.3;//chance of having a double letter in pet name
        
        
        //Starting screen
        System.out.println("            |\\_/|        D\\___/\\\n" +
                            "            (0_0)         (0_o)\n" +
                            "           ==(Y)==         (V)\n" +
                            "----------(u)---(u)----oOo--U--oOo---\n" +
                            "__|_______|_______|_______|_______|___");
        
        System.out.println("               PET ISLAND");
        
        
        //logging in
        System.out.print("Enter your name: ");
        String userName = input.nextLine();
        
        System.out.print("Enter the password: ");
        String password = input.nextLine();
        
        int counter = 1;
        while((!userName.equals(CORRECT_USERNAME) || !password.equals(CORRECT_PASSWORD)) && counter <= 3) {
            if(counter == 3) {
                System.out.println("Login failed.");
                System.exit(0);
            }
            
            System.out.println("\nIncorrect credentials. ");
            System.out.println("Attempts left: " + (3 - counter) + "\n");
            
            
            System.out.print("Enter your name: ");
            userName = input.nextLine();
        
            System.out.print("Enter the password: ");
            password = input.nextLine();
            counter++;
        }
        
        
        //display menu options
        System.out.println("\n1. Start\n2. Instructions\n3. Exit");
        System.out.print("Please make a selection(1,2,3): ");
        int menuSelection = input.nextInt();
        
        
        //switch for different menu selections
        switch (menuSelection) {
            
            case 1: 
                //start
                
                //pet selection
                System.out.println("\nSelect your pet: \n1. Dog\n2. Cat\n3. Bear");
                System.out.print("Your selection(1,2,3): ");
                int petSelection = input.nextInt();
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
                //display pet species
                System.out.println("You chose: " + pet + "!");
                
                
                //naming
                System.out.println("\nNow we need to name your pet " + pet + ".");
                System.out.println("Would you like to:\n1) Choose the name yourself\n2) Generate a random name");
                System.out.print("Your selection (1,2): ");
                int namingChoice = input.nextInt();
                String petName = "";
                
                switch(namingChoice){
                    
                    case 1:
                        //user chooses the name
                        System.out.print("Enter your pet's name: ");
                        input.nextLine();
                        petName = input.nextLine();
                        break;
                               
                    case 2:
                        //generate a random name
                        int lenName = random.nextInt(5) + 4;
                        
                        for(int i = 0; i < lenName; i++) {
                            if(i % 2 == 0) {
                                //consonant
                                petName += CONSONANTS.charAt(random.nextInt(21));
                            }
                            else if(i % 2 == 1) {
                                //vowel
                                if(Math.random() < CHANCE_DOUBLE_LETTER && lenName - (i + 1) >= 2) {
                                    //make sure that there there is still space for 2 letters in name
                                    char vow = VOWELS.charAt(random.nextInt(5));
                                    petName += vow;
                                    petName += vow;
                                    lenName--;
                                }
                                else {
                                    petName += VOWELS.charAt(random.nextInt(5));
                                }
                            }
                            
                            if(i == 0) petName = petName.toUpperCase(); // capitalize the first letter
                        }
                        break;
                        
                    default:
                        System.out.println("Bad Input");
                        System.exit(0);
                }
                //display name
                System.out.println("\nYour pet, named " + petName + ", has been born!");
                
                
                //dividing starting points
                int startingPoints = STARTING_POINTS; // number of total starting points to be randomly divided among health, food, energy
                
                int maxHealth = random.nextInt(startingPoints) + 1;
                startingPoints -= maxHealth;
                int maxFood = random.nextInt(startingPoints) + 1;
                startingPoints -= maxFood;
                int maxEnergy = startingPoints;
                
                //display stats
                System.out.println("\nMAX HEALTH = " + maxHealth + "\nMAX FOOD = " + maxFood + "\nMAX ENERGY = " + maxEnergy);
                
                
                
                break;
                

            
            case 2:
                //instructions
                
                System.out.println("Instructions");
                break;
            
            case 3: 
                //exit
                
                System.exit(0); 
                break;
            
            default: 
                System.out.println("Bad Input");
                
        }
    }
}
