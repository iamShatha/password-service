package passwordservices;

import java.util.Scanner;

public class PasswordServices {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to our passwords services project");
        // declare the variable choice
        int choice;
        // using do-while loop to repeat the display of the server services 
        do {
            System.out.println(
                    "\nTo generate password, please enter 1. "
                    + "\nTo check strength of your password, please enter 2."
                    + "\nTo generate passwords and check thier strengths, please enter 3."
                    + "\nTo exit the program, please enter 0.\n");
            choice = input.nextInt();

            // using switch method to allowed the user to chooese 
            switch (choice) {
                // choice 0 will end the program
                case 0:
                    System.out.println("Message : program ended");
                    break;
                case 1:
                    // declare the variable len
                    Double len;
                    System.out.println("Enter the password length: ");
                    // asking the user to enter the required length
                    len = input.nextDouble();
                    // check if the length equals 0 or a negative number
                    if (len % 1 != 0 || len <= 0) {
                        System.out.println("Password length should be a positive integer.\n");
                    } else {
                        // calling the methoed generatedPasswords to generat 3 arrays with random passwords
                        String[] passwords = generatePasswords(len);
                        // calling the method printPasswords to print the generatedPasswords method           
                        printPasswords(passwords);
                    }
                    break;

                case 2:
                    System.out.println("Enter your password : ");

                    String password = input.next();
// declare the variable score by method checkStrength and checking the password strength's
                    int score = checkStrength(password);
// calling the method printStrength to print a specific statement for the user
                    printStrength(score);

                    break;

                case 3:
                    System.out.print("Enter the password length: ");

                    // check if the input equals 0 or a negative number
                    len = input.nextDouble();
                    if (len % 1 != 0 || len <= 0) {
                        System.out.println("Password length should be a positive integer.\n");
                    } else {
// calling the method generatePasswords check the strengths of the passwords by the method checkStrength                        //
                        String[] generatedPasswords = generatePasswords(len);
                        int[] strengthScores = new int[generatedPasswords.length];
// using for loop to check every random characters
                        for (int i = 0; i < generatedPasswords.length; i++) {
                            strengthScores[i] = checkStrength(generatedPasswords[i]);
                        }
// calling the method printPasswords to print the generatedPasswords method with the checking statement           

                        printPasswords(generatedPasswords, strengthScores);
                    }

                    break;

                default:
                    // any other case that not mentioned will end the program
                    System.out.println("Error: Invild entry ");
                    break;
            }
// the loop won't repeat if the choice equals 0
        } while (choice != 0);

    }

    public static String[] generatePasswords(double len) {
        //Declare a String array that stores 3 passwords
        String[] passwords = new String[3];

        System.out.println("Here are a few options:");
        //declare Strings that contains uppercase and lowercase letters, digits and symbols
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String characters = "!@#$%^&*|/:;<>=+-?~`[]{}()";
// adding them together under the string all
        String all = capitalLetters + lowerLetters + characters + digits;
// using for loop to generatePasswords arrys
        for (int i = 0; i < passwords.length; i++) {
            String password = "";
            for (int j = 0; j < len; j++) {
                // math random method will import a random element from the String all in each cycle of the loop based on input length from the user
                int total = (int) (Math.random() * all.length());
                password += all.charAt(total);
            }
            passwords[i] = password;
        }
// this method will return the passwords array
        return passwords;

    }
//Create a method that prints the passwords's arrays

    public static void printPasswords(String[] passwords) {
        for (int i = 0; i < passwords.length; i++) {
            System.out.println(passwords[i]);
        }

    }
//Create a method to print the password arrays with the password array and scores

    public static void printPasswords(String[] passwords, int[] scores) {
        for (int i = 0; i < passwords.length; i++) {
            System.out.print(passwords[i] + ",");
            //Call a printStrength method that receives the password score and prints checking statement
            printStrength(scores[i]);
        }
    }

    public static int checkStrength(String S) {
        int score = 0;
        int uppercase = 0, lowercase = 0, symbol = 0, number = 0, moreThan8 = 0;
        char letter = S.charAt(0);
        for (int ch = 0; ch < S.length(); ch++) {
            letter = S.charAt(ch);
            if (letter >= 65 && letter <= 90) {
                uppercase++;
            }
            //ASCII for uppercase letters
            if (letter >= 97 && letter <= 122) {
                lowercase++;
            }
            //ASCII for lowercase letters
            if (letter >= 48 && letter <= 57) {
                number++;
            }
            //ASCII for numbers
            if (letter >= 33 && letter <= 47 || letter >= 58 && letter <= 64 || letter >= 91 && letter <= 96 || letter >= 123 && letter <= 126) {
                symbol++;
            }
            //ASCII for all special characters on the keyboard
            if (S.length() >= 8) {
                moreThan8++;
            }
        }//to read the password character by character and determine weather it has the potential
        if (moreThan8 >= 1) {
            score++;
        }
        if (uppercase >= 1) {
            score++;
        }
        if (lowercase >= 1) {
            score++;
        }
        if (number >= 1) {
            score++;
        }
        if (symbol >= 1) {
            score++;
        }
        //to calculate the score
        return score;
    }//calculate the score

    public static void printStrength(int score) {
//Create a method that compares the password score with the criteria specified for passwords strength
//will print a descriptive sentence
        if (score == 5) {

            System.out.println("This is a very good password !");
        } else if (score == 4) {

            System.out.println("This is a good password, but you can still do better");
        } else if (score == 3) {

            System.out.println("This is a medium password, try making it better");
        } else {

            System.out.println("This is a weak password, you should find a new one!");
        }

        //Multi-way if-else  
    }

}
