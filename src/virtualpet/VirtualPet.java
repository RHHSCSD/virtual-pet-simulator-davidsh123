/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;
/**
 *
 * @author michael.roy-diclemen
 */
public class VirtualPet {
    
    static int money = 0;
    static String petName = "", petSpecies = "";
    static int[] maxStats = new int[3]; //{health, food, energy}
    static double[] currentStats = new double[3];
    static String username, password;
    
    
    //shuffle a string
    //for matching game
    public static String shuffleString(String s, Random r) {
        
        String shuffled = "";
        
        //length of string to be shuffled
        int len = s.length();
        
        //randomly select characters from s to be put into shuffled
        for(int i = 0; i < len; i++) {
            int randomIndex = r.nextInt(s.length());
            String randomChar = s.substring(randomIndex, randomIndex + 1);
            shuffled += randomChar;
            
            //remove that char from s
            s = s.substring(0, randomIndex) + s.substring(randomIndex + 1);
        }
        return shuffled;
    }
    
    //reveal two indexes in shuffledCensored
    public static String revealTwoIndexes(String censored, String s, int index1, int index2) {
        int min = Math.min(index1, index2);
        int max = Math.max(index1, index2);
        
        return censored.substring(0, min) + s.charAt(min) + censored.substring(min + 1, max) + s.charAt(max) + censored.substring(max + 1);
    }
    
    public static String generateName(int lenName, double chanceDblLetter, Random random) {
        String consonants = "bcdfghjklmnpqrstvwxyz";
        String vowels = "aeoiu";
        
        String petName = "";
        
        for(int i = 0; i < lenName; i++) {
            if(i % 2 == 0) {
                //consonant
                petName += consonants.charAt(random.nextInt(21));
            }
            else if(i % 2 == 1) {
                //vowel
                //double letter?
                if(Math.random() < chanceDblLetter && lenName - (i + 1) >= 2) {
                    //make sure that there there is still space for 2 letters in name
                    char vow = vowels.charAt(random.nextInt(5));
                    petName += vow;
                    petName += vow;
                    lenName--;
                }
                //no double ltter
                else {
                    petName += vowels.charAt(random.nextInt(5));
                }
            }
                            
            if(i == 0) petName = petName.toUpperCase(); // capitalize the first letter
        }
        
        
        return petName;
    }
    
    public static void printStartScreen() {
        System.out.println("            |\\_/|        D\\___/\\\n" +
                            "            (0_0)         (0_o)\n" +
                            "           ==(Y)==         (V)\n" +
                            "----------(u)---(u)----oOo--U--oOo---\n" +
                            "__|_______|_______|_______|_______|___");
        
        System.out.println("               PET ISLAND");
    }
    
    public static void playWithPet() {
        System.out.println("You bought a toy to play with your pet.");
        money -= 5;
    }
    
    public static void feedPet() {
        System.out.println("You feed your pet");
        money -= 5;
    }
    
    public static void groomPet() {
        System.out.println("You groom your pet");
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        
        //final variables
        final int STARTING_POINTS = 20;//20 starting points
        final double CHANCE_DOUBLE_LETTER = 0.3;//chance of having a double letter in pet name
        
        
        //Starting screen
        printStartScreen();
        
        
        System.out.print("Welcome\nWould you like to create new profile (1) or login (2): ");
        int login = input.nextInt();
        
        if(login == 1) {
            //new profile
            String username = JOptionPane.showInputDialog("Enter your username: ");

            String password = JOptionPane.showInputDialog("Enter your password: ");

            try {
                File f = new File(username + ".txt");
                PrintWriter pw = new PrintWriter(f);
                pw.println(username);
                pw.println(password);
                pw.close();
            }
            catch(FileNotFoundException e) {
                System.exit(0);
            }
        }
        else if(login == 2) {
            //logging in
            String inputUsername = JOptionPane.showInputDialog("Enter your username: ");

            String inputPassword = JOptionPane.showInputDialog("Enter your password: ");

            try {
                File f = new File(inputUsername + ".txt");
                Scanner s = new Scanner(f);
                username = s.nextLine();
                password = s.nextLine();
                int counter = 1;
                while(!inputPassword.equals(password) && counter <= 3) {
                    if(counter == 3) {
                        JOptionPane.showMessageDialog(null, "Login failed.");
                        System.exit(0);
                    }

                    JOptionPane.showMessageDialog(null, "Incorrect credentials. \nAttempts left: " + (3 - counter));

                    inputPassword = JOptionPane.showInputDialog("Enter your password: ");
                    counter++;
                }
                s.close();
            }
            catch(FileNotFoundException e) {
                System.out.println("Username does not exist");
                System.exit(0);
            }
        }
        
        //display menu options
        JOptionPane.showMessageDialog(null, "Access Granted");
        System.out.println("\n1. Start\n2. Instructions\n3. Exit");
        System.out.print("Please make a selection(1,2,3): ");
        int menuSelection = input.nextInt();
        
        
        //switch for different menu selections
        switch (menuSelection) {
            
            case 1: 
                //start
                
                //variables

                int[] activities = new int[3]; //{play, feed, groom}
                
                
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
                try {
                    File f = new File(username + ".txt");
                    PrintWriter pw = new PrintWriter(f);
                    pw.println(petSpecies);
                    
                }
                catch(FileNotFoundException e) {
                    
                }
                
                
                //naming
                System.out.println("\nNow we need to name your pet " + petSpecies + ".");
                System.out.println("Would you like to:\n1) Choose the name yourself\n2) Generate a random name");
                System.out.print("Your selection (1,2): ");
                int namingChoice = input.nextInt();
                
                
                switch(namingChoice){
                    
                    case 1:
                        //user chooses the name
                        System.out.println("(Open your gui)");
                        petName = JOptionPane.showInputDialog("Enter your pet's name: ");
                        break;
                               
                    case 2:
                        //generate a random name
                        int lenName = random.nextInt(5) + 4;
                        petName = generateName(lenName, CHANCE_DOUBLE_LETTER, random);
                        break;
                        
                    default:
                        System.out.println("Bad Input");
                        System.exit(0);
                }
                
                //display name
                System.out.println("\nYour " + petSpecies + ", " + petName + ", has been born!");
                
                
                //dividing starting points
                int startingPoints = STARTING_POINTS; // number of total starting points to be randomly divided among health, food, energy
                
                
                
                //max health 4 to 8
                maxStats[0] = random.nextInt(startingPoints - 15) + 4;
                startingPoints -= maxStats[0];
                //max food 4 to 8
                maxStats[1] = random.nextInt(startingPoints - 11) + 4;
                startingPoints -= maxStats[1];
                // max energy 4 to 12
                maxStats[2] = startingPoints;
                try {
                    File f = new File(username + ".txt");
                    PrintWriter pw = new PrintWriter(f);
                    pw.println(maxStats[0]);
                    pw.println(maxStats[1]);
                    pw.println(maxStats[2]);
                }
                catch(FileNotFoundException e) {
                    
                }
                
                
                currentStats[0] = maxStats[0] / 2.0;
                currentStats[1] = maxStats[1] / 2.0;
                currentStats[2] = maxStats[2] / 2.0;
                
                
                //display stats
                System.out.println("\nMAX HEALTH = " + maxStats[0] + "\nMAX FOOD = " + maxStats[1] + "\nMAX ENERGY = " + maxStats[2]);
                
                
                //mini games
                System.out.print("Do you want to play number guessing game (1) or matching game (2): ");
                int gameChoice = input.nextInt();
                
                switch(gameChoice) {
                    case 1: 
                        //number guessing game
                        
                        System.out.println("\nWelcome to the number guessing game!\n");
                        System.out.println("Guess '-1' to quit the game.");
                        
                        
                        //choose randum number 1-100
                        int randNum = random.nextInt(100) + 1;
                        
                        //10 total attempts
                        final int TOTAL_NUM_GUESSES = 10;
                        int guessCounter = 0;
                        int guess = 0;
                        
                        while (guess != -1) {
                            System.out.println("Random number from 1-100 has been chosen!");
                            
                            while(guess != randNum  && guessCounter < TOTAL_NUM_GUESSES && guess != -1) {
                                System.out.print("Your guess: ");
                                guess = input.nextInt();
                                //compare guess to random number
                                if(guess == -1) {
                                    break;
                                }
                                else if (guess < randNum) {
                                    System.out.println("Too low");
                                    guessCounter++;
                                    System.out.println("Guesses left: " + (TOTAL_NUM_GUESSES - guessCounter) + "\n");
                                }
                                else if(guess > randNum) {
                                    System.out.println("Too high");
                                    guessCounter++;
                                    System.out.println("Guesses left: " + (TOTAL_NUM_GUESSES - guessCounter) + "\n");
                                }
                                else if(guess == randNum){
                                    System.out.println("Correct!");
                                    //Win money
                                    int moneyGained = random.nextInt(10) + 1;
                                    System.out.println("You won " + moneyGained + " moneys!\n");
                                    money += moneyGained;
                                    
                                }
                                if(TOTAL_NUM_GUESSES - guessCounter == 0) {
                                    System.out.println("You ran out of guesses!");
                                }
                                
                            }
                            randNum = random.nextInt(100) + 1;
                            guessCounter = 0;
                            
                        }
                        
                        break;
                        
                    case 2:
                        //matching game
                        
                        //shuffling
                        String unshuffled = "aabbccddee";
                        String shuffled = shuffleString(unshuffled, random);
                        int numGuesses = 0;
                        
                        System.out.println(shuffled);  //UNCOMMENT IF YOU WANT TO REVEAL ANSWER BEFORE PLAYING (FOR TESTING)***
                        
                        //Censored string that will be updated after every correct match
                        String shuffledCensored = "**********";
                        
                        //take guess input
                        System.out.println("Shuffled string: " + shuffledCensored);
                        System.out.print("\nGuess Index 1: ");
                        int index1 = input.nextInt();
                        System.out.print("Guess Index 2: ");
                        int index2 = input.nextInt();
                        
                        //while there are still censored characters (more guesses to be made)
                        while (shuffledCensored.contains("*")) {
                            
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
                                shuffledCensored = revealTwoIndexes(shuffledCensored, shuffled, index1, index2);
                                
                            }
                            
                            //One of the indexes have already been guessed correctly
                            else if(shuffledCensored.charAt(index1) != '*' || shuffledCensored.charAt(index2) != '*') {
                                System.out.println("You already guessed one of those indexes");
                            }
                            //not a match
                            else {
                                System.out.println(revealTwoIndexes(shuffledCensored, shuffled, index1, index2));
                            }
                            
                            //Display shuffledCensored
                            System.out.println("\nShuffled string: " + shuffledCensored);
                            
                            numGuesses++;
                            
                            //take new input if there is still characters to be guessed
                            if(shuffledCensored.contains("*")) {
                                System.out.print("\nGuess Index 1: ");
                                index1 = input.nextInt();
                                System.out.print("Guess Index 2: ");
                                index2 = input.nextInt();
                            }
                            
                            //No more characters being censored = win
                            else {
                                System.out.println("Congratulations! You solved it in " + numGuesses + " guesses!");
                            }
                            
                        }
                        
                        break;
                    default: 
                        System.out.println("Bad Input");
                        System.exit(0);
                        
                }
                
                while(money >= 5) {
                    System.out.println("Your Money: " + money);
                    System.out.println("\nChoose an activity to do: ");
                    System.out.println("1. Buy a toy to play with your pet\n2. Buy food to feed your pet\n3. Groom your pet\n4. Exit");
                    System.out.print("Your selection: ");
                    int activityChoice = input.nextInt();
                    switch(activityChoice) {
                        case 1:
                            playWithPet();
                            currentStats[2] += currentStats[2] * 0.05;
                            activities[0]++;
                            break;
                        case 2:
                            feedPet();
                            currentStats[1] += currentStats[1] * 0.05;
                            activities[1]++;
                            break;
                        case 3:
                            groomPet();
                            currentStats[0] += currentStats[0] * 0.05;
                            activities[2]++;
                            break;
                        case 4:
                            
                            break;
                        default:
                            System.out.println("Bad input.");

                    }
                    if(activityChoice == 4) break;
                }
                
                System.out.println("Your Money: " + money);
                try {
                    File f = new File(username + ".txt");
                    PrintWriter pw = new PrintWriter(f);
                    pw.println(currentStats[0]);
                    pw.println(currentStats[1]);
                    pw.println(currentStats[0]);
                    pw.println(money);
                    
                }
                catch(FileNotFoundException e) {
                    
                }
                
                System.out.println("\nToday you:"
                        + "\nPlayed with your pet " + activities[0] + " times"
                        + "\nFed your pet " + activities[1] + " times"
                        + "\nGroomed your pet " + activities[2] + " times");
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
