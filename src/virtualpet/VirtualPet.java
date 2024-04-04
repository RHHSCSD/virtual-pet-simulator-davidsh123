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
                
                //variables
                String petName = "", petSpecies = "";
                int maxHealth, maxFood, maxEnergy, money = 0;
                
                
                //pet selection
                System.out.println("\nSelect your pet: \n1. Dog\n2. Cat\n3. Bear");
                System.out.print("Your selection(1,2,3): ");
                int petSelection = input.nextInt();
                
                switch (petSelection) {
                    case 1: 
                        petSpecies = "Dog";
                        break;
                    case 2: 
                        petSpecies = "Cat";
                        break;
                    case 3: 
                        petSpecies = "Bear";
                        break;
                    default: 
                        System.out.println("Bad Input");
                        System.exit(0);
                }
                //display pet species
                System.out.println("You chose: " + petSpecies + "!");
                
                
                //naming
                System.out.println("\nNow we need to name your pet " + petSpecies + ".");
                System.out.println("Would you like to:\n1) Choose the name yourself\n2) Generate a random name");
                System.out.print("Your selection (1,2): ");
                int namingChoice = input.nextInt();
                
                
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
                System.out.println("\nYour " + petSpecies + ", " + petName + ", has been born!");
                
                
                //dividing starting points
                int startingPoints = STARTING_POINTS; // number of total starting points to be randomly divided among health, food, energy
                
                maxHealth = random.nextInt(startingPoints) + 1;
                startingPoints -= maxHealth;
                maxFood = random.nextInt(startingPoints) + 1;
                startingPoints -= maxFood;
                maxEnergy = startingPoints;
                
                //display stats
                System.out.println("\nMAX HEALTH = " + maxHealth + "\nMAX FOOD = " + maxFood + "\nMAX ENERGY = " + maxEnergy);
                
                
                //mini games
                System.out.print("Do you want to play number guessing game (1) or matching game (2): ");
                int gameChoice = input.nextInt();
                
                switch(gameChoice) {
                    case 1: 
                        //number guessing game
                        
                        System.out.println("\nWelcome to the number guessing game!\n");
                        System.out.println("Random number from 1-100 has been chosen!");
                        
                        //choose randum number 1-100
                        int randNum = random.nextInt(100) + 1;
                        
                        //10 total attempts
                        final int TOTAL_NUM_GUESSES = 10;
                        int guessCounter = 0;
                        int guess = 0;
                        
                        
                        while(guess != randNum  && guessCounter < TOTAL_NUM_GUESSES) {
                            System.out.print("Your guess: ");
                            guess = input.nextInt();
                            
                            //compare guess to random number
                            if (guess < randNum) {
                                System.out.println("Too low");
                                guessCounter++;
                                System.out.println("Guesses left: " + (TOTAL_NUM_GUESSES - guessCounter));
                            }
                            else if(guess > randNum) {
                                System.out.println("Too high");
                                guessCounter++;
                                System.out.println("Guesses left: " + (TOTAL_NUM_GUESSES - guessCounter));
                            }
                            else {
                                System.out.println("Correct!");
                                //Win money
                                int moneyGained = random.nextInt(5) + 1;
                                System.out.println("You won " + moneyGained + " moneys!");
                                money += moneyGained;
                            }
                        }
                        break;
                        
                    case 2:
                        //matching game
                        
                        //shuffling
                        String unshuffled = "aabbccddee";
                        String shuffled = "";
                        
                        for(int i = 0; i < 10; i++) {
                            int randomIndex = random.nextInt(unshuffled.length());
                            String randomChar = unshuffled.substring(randomIndex, randomIndex + 1);
                            shuffled += randomChar;
                            unshuffled = unshuffled.substring(0, randomIndex) + unshuffled.substring(randomIndex + 1);
                        }
                        //System.out.println(shuffled);  UNCOMMENT IF YOU WANT TO REVEAL ANSWER BEFORE PLAYING (FOR TESTING)
                        
                        //Censored string that will be updated after every correct match
                        String shuffledCensored = "**********";
                        
                        //take guess input
                        System.out.println("Shuffled string: " + shuffledCensored);
                        System.out.print("\nGuess Index 1: ");
                        int index1 = input.nextInt();
                        System.out.print("Guess Index 2: ");
                        int index2 = input.nextInt();
                        
                        //while there are still censored characters (more guesses to be made)
                        while (shuffledCensored.indexOf("*") >= 0) {
                            
                            //characters at the guessed index are a match and haven't been chosen already 
                            //are represented by '*' in shuffledCensored
                            if(shuffled.charAt(index1) == shuffled.charAt(index2) && shuffledCensored.charAt(index1) == '*') {
                                System.out.println("They are a match!");
                                
                                //Win money
                                int moneyGained = random.nextInt(5) + 1;
                                System.out.println("You won " + moneyGained + " moneys!");
                                money += moneyGained;
                                
                                //update shuffledCensored
                                //replace asterix with the proper letters
                                shuffledCensored = shuffledCensored.substring(0, Math.min(index1, index2)) + shuffled.charAt(index1) + shuffledCensored.substring(Math.min(index1, index2) + 1, Math.max(index1, index2)) + shuffled.charAt(index1) + shuffledCensored.substring(Math.max(index1, index2) + 1);
                                
                            }
                            
                            //One of the indexes have already been guessed correctly
                            else if(shuffledCensored.charAt(index1) != '*' || shuffledCensored.charAt(index2) != '*') {
                                System.out.println("You already guessed one of those indexes");
                            }
                            //not a match
                            else {
                                System.out.println("They are not a match!");
                            }
                            
                            //Display shuffledCensored
                            System.out.println("\nShuffled string: " + shuffledCensored);
                            
                            //take new input if there is still characters to be guessed
                            if(shuffledCensored.indexOf("*") >= 0) {
                                    System.out.print("\nGuess Index 1: ");
                                    index1 = input.nextInt();
                                    System.out.print("Guess Index 2: ");
                                    index2 = input.nextInt();
                            }
                            
                            //No more characters being censored = win
                            else {
                                System.out.println("Congratulations! You solved it!");
                            }
                        }
                        
                    break;
                        
                }
                
                
                
                
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
