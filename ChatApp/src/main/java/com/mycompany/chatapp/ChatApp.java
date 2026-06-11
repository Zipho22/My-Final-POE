/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class ChatApp {
    
public static void main(String[] args){
    
    Scanner input = new Scanner(System.in);
    
    //Registration panel:
    System.out.println("\n--- REGISTRATION---");
    
    //Request the user to enter their first name 
    System.out.print("Enter firstname :");
    String name1 = input.nextLine();
    
    //Request the user to enter their Last name
    System.out.print("Enter lastname :");
    String name2 = input.nextLine();
     
    //Declare variables to add loops
    
     String LoginID = "";
     String password = "";
     String phone = "";
     
    //loop until the username is correct
    while(true) {
    //Prompt the user to enter their Username
    System.out.print("Enter username :");
    LoginID = input.nextLine();
    
    //Show username results
    if
    (LoginID.contains("_") && LoginID.length() <= 5) {
        System.out.println("Username succesfully captured");
        break;
    }else{
        System.out.println("Username is not correctly formatted please ensure that your username contains \nan underscore(_) and is no more that five characters long.");
    }
    }
    
    //Loop until the password is correctly formatted
    while (true) {
    //Request the user to enter their password
    System.out.print("Enter password :");
    password = input.nextLine();
    
    //Display results of the user's password
    if (password.length() >=8){
        System.out.println("Password successfully captured");
        break;
    }else{
        System.out.println("Password is not correctly formatted please ensure that password contains at least \neight characters,a capital letter,a number and a special character.");
    }
    }
    
    //Loop until the phone number is correctly formatted
    while(true) {
    //Ask the user to enter user's phone number
    System.out.print("Enter phone number :+27");
    String Numberleft = input.nextLine();
    phone = "+27" + Numberleft ;
    
    //Display results of the phone number
    if (phone.startsWith("+27") && (phone.length ()-3) <=10){
        System.out.println("Cell phone number successfully added");
        break;
    }else{
        System.out.println("Cell phone is not correctly formatted or does not contain an international code(+27) please correct the number and try again.");
    }
    }
    
    //Login panel
    System.out.println("\n--- Login now ---");
    
    //Loop until the login is correct
    while (true) {
    //Ask  the user to enter their username
    System.out.print("Enter your username :");
    String UserDetails = input.nextLine();
    
    //Ask the user to enter their password
    System.out.print("Enter your password :");
    String PasswordLogin = input.nextLine();
    
    //Display the user's login results
    if(UserDetails.equals(LoginID) && PasswordLogin.equals(password)){
        System.out.println("Welcome " + name1 + " " + name2  + " " + " , it is great to see you again!");
        break;
    }else{
        System.out.println("Username or password is incorrect please try again.");
    }
        
    }
    QuickChat.runQuickChat(input,name1, name2, LoginID);
    //Close the Scanner
    input.close();
    }
    
    //Boolean test to check whether the username is correctly formatted
    public boolean checkUsername(String LoginID){
        if
        (LoginID.contains("_") && LoginID.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }
    
    //Boolean test to check if the password is correctly formatted
    public boolean checkPasswordComplexity(String password){
        boolean UpperCase = false;
        boolean ContainsNum = false;
        boolean ContainsSpecChar = false;
        boolean TrueLength = false;
        
        if (password.length() >= 8) {
            TrueLength = true;
        }
        
        for (int i = 0; i < password.length(); i++) {
            char currentChar = password.charAt(i);
            
            if (currentChar >= 'A' && currentChar <= 'Z') {
                UpperCase = true;
            }
            if (currentChar >= '0' && currentChar <= '9') {
                ContainsNum = true;
            }
            if (!Character.isLetterOrDigit(currentChar)) {
                ContainsSpecChar = true;
            }
        }
        if (TrueLength == true && UpperCase == true &&
             ContainsNum == true && ContainsSpecChar == true) {
            return true;
         }else{
             return false;
             }
    } 
    
    //Boolean to check if the phone numberis correct
    public boolean checkCellPhoneNumber(String cellNumber){
        if(cellNumber.startsWith("+27") &&
            (cellNumber.length()-3) <= 10) {
                return true;
        } else {
                return false;
        }
    }        
    
    // Boolean method to check user registration panel
    public String registerUser(String username, String password) {
        if 
        (checkUsername(username) == false) {
            return "username is incorrectly formatted.";
        }
        if
        (checkPasswordComplexity(password) == false) {
            return "Password is incorrectly.";
        }
        return "Username correctly formatted.Password correctly formatted.";
    }
    
    //Boolean method to check the Login Panel
    public static boolean loginUser(String LoginID, String password, String storedLoginID, String storedPassword) {
        if(LoginID.equals(storedLoginID) && 
           password.equals(storedPassword)) {
           return true;
        } else {
            return false;
        }
    }

//Boolean method to check if login is successful or not
public static String returnLoginStatus(boolean loginSuccess, String name1, String name2){
    if 
    (loginSuccess == true) {
        return "Welcome " + name1 + " " + name2  + " " + " , it is great to see you again!";
    } else {
        return "Username or password is incorrect.";
    }
}
//PART 2 POE
 class QuickChat {
    
    //PART 3 POE _ ARRAYS
    static String[] sentMessages = new String[100];
    static String[] disregardedMessages = new String[100];
    static String[] storedMessages = new String[100];
    static String[] messageHashes = new String[100];
    static String[] messageIDs = new String[100];
    static String[] recipients = new String[100];
    
    //Counters that are used to track array positions - PART 3 POE
    static int sentCount = 0;
    static int disregardedCount = 0;
    static int storedCount = 0;
    
    public static void runQuickChat(Scanner input, String name1, String name2, String LoginID) {
        
        System.out.println("\n----CHATAPP!-----");
        //Registration panel
        System.out.println("\n---REGISTRATION---");
        
        //Request user to enter their first name
        System.out.print("Create username: ");
        LoginID = input.nextLine();
        
        //Request user to enter their Last name
        System.out.print("Create password:");
        String password = input.nextLine();
        
        //Declaring variables before looping
        String Login = "";
        String pass = "";
        
        //Loop until 
        while(true) {
        //Login panel
        System.out.println("\n---LOGIN---");
        
        //Ask the user to please enter their username
        System.out.print("Enter username:");
        Login = input.nextLine();
        
        System.out.print("Enter password:");
        pass = input.nextLine();
        
        if (Login.equals(LoginID) && pass.equals(password)) {
            System.out.println("Login successful");
            break;
        } else {
            System.out.println("Login failed");
            }
    }
    
    //Welcoming the user
    System.out.println("\nWelcome to ChatApp");
    
    //intialising options variable
    int menuOption = 0;
    int FinalMessages = 0;
    
    //Main menu loop
    while ( menuOption != 3) {
        
        System.out.println("\n1. Send Messages");
        System.out.println("2. Show recently sent messages");
        System.out.println("3. Quit");
        System.out.println("4. Stored Messages");
        
        System.out.print("Choose option:");
        menuOption = input.nextInt();
        input.nextLine();
        
        //======Option 1: Send Message==========
        if (menuOption == 1) {
            
            System.out.print("How many messages would you like to send? ");
            FinalMessages = input.nextInt();
            input.nextLine();
            //=======MESSAGE LOOP=========
            for (int i = 1; i <= FinalMessages; i++) {
                
                System.out.println("\nMessage " + i);
                 
                //========MESSAGE ID=========
                //Message ID
                String messageID =  String.valueOf((long)(Math.random() * 9000000000L) + 1000000000L);
                System.out.println("Message ID generated: " + messageID);
                
                //======RECIPIENT==========
                System.out.print("\nEnter recipient number: +27");
                String Numberleft = input.nextLine();
                String recipient = "+27" + Numberleft;
                
                if (recipient.startsWith("+27") && (recipient.length()-3) <= 10) {
                    System.out.println("\nCell number captured successfully");
                } else {
                    System.out.println("Invalid cell number,number must contain no more than 10 characters and begins with the international code(+27) please try again");
                }
                
                //======MESSAGE==========
                System.out.print("Enter message: ");
                String message = input.nextLine();
                
                if (message.length() > 250) {
                    System.out.println("Please enter a message for less than 250 characters");
                    continue;
                } else {
                    System.out.println("Message sent");
                }
               
                //========MESSAGE HASH==========
                //Split Message into words
                String[] words = message.split(" ");
                //First word
                String firstWord = words[0].toUpperCase();
                //LastWord
                String LastWord = words[words.length - 1].toUpperCase();
                
                String hash = messageID.substring(0,2) + ":" + i + ":" +
               firstWord + LastWord;
                
                //======SEND MENU=============
                System.out.println("\n1. Send Message");
                System.out.println("2. Disregard Message:");
                System.out.println("3. Store Message:");
                
                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                input.nextLine();
                
                //Send message option
                if (choice == 1) {
                    
                    //Store message details in arrays - PART 3 POE 
                    sentMessages[sentCount] = message;
                    messageHashes[sentCount] = hash;
                    messageIDs[sentCount] = messageID;
                    recipients[sentCount] = recipient;
                    //move to the next available position - PART 3 POE
                    sentCount++;
                    
                    System.out.println("\nMessage was sent successfully");
                    System.out.println("Message ID:" + messageID);
                    System.out.println("Message Hash: " + hash);
                    System.out.println("Recipient:" + recipient);
                    System.out.println("Message:" + message);
                  
                  //Disregard message  
            }else if (choice == 2) {
                    
                    //Save disregarded message in an array - PART 3 POE
                   disregardedMessages[disregardedCount] = message;
                   //Increasing the counter - PART 3 POE
                   disregardedCount++;
                   
                   System.out.println("Message disregarded");
                }
                
                //Store message in JSON
            else if (choice == 3) {
                System.out.println("\nMessage was stored successsfully");
                
                //Store message iinformation in arrays- PART 3 POE
                    storedMessages[storedCount] = message;
                    messageHashes[storedCount] = hash;
                    messageIDs[storedCount] = messageID;
                    recipients[storedCount] = recipient;
                    //Increase stored message counter- PART 3 POE
                    storedCount++;
                try {
                    
                    FileWriter file = 
                                     new FileWriter("messages.json",true);
                    String json ="{\n" + 
                                " \"messageID\": \"" + messageID + "\",\n" + " \"recipient\": \"" + recipient + "\", \n" + "\"message\": \"" + message + "\", \n" + " \"hash\": \"" + hash + "\"\n" + " }\n";
                    file.write(json);
                    file.close();
                    
                } catch (IOException e) {
                    
                    System.out.println("Error storing message in JSON file");
                }
            }
       }
            //Total number of messages
            System.out.println("\nTotal messages sent:" + FinalMessages);
        }
       //=======SECOND OPTION======
       else if (menuOption == 2) {
           //Show recently sent messages
           System.out.println("\n---Recently Sent Messages---");
           
           for(int i = 0; i< sentCount; i++){
               System.out.println("Recipient: " + recipients[i]);
               System.out.println("Message: " + sentMessages[i]);
               System.out.println();
       }
  }
       //========THIRD OPTION=======
       else if (menuOption == 3) {
           System.out.println("\nGoodbye.");
           break;
       }
       //PART 3 POE STORED MESSAGES MENU
       else if (menuOption == 4) {

    System.out.println("\n--- STORED MESSAGES ---");

    System.out.println("1. Display Stored Messages");
    System.out.println("2. Display Longest Stored Message");
    System.out.println("3. Search Message ID");
    System.out.println("4. Search Recipient");
    System.out.println("5. Delete Message Using Hash");
    System.out.println("6. Display Report");

    System.out.print("Choose option: ");
    int option = input.nextInt();
    input.nextLine();

    if(option == 1){
        
        //Show all the stored messages and recipients- PART 3 POE
        for(int i = 0; i < storedCount; i++){
            
            if(storedMessages[i] != null){

            System.out.println("Recipient: " + recipients[i]);
            System.out.println("Message: " + storedMessages[i]);
            System.out.println();
            }
        }
    }

    else if(option == 2){

        //Find the longest stored message - PART 3 POE
        String longest = "";

        for(int i = 0; i < storedCount; i++){

            if(storedMessages[i] !=null &&
                storedMessages[i].length() > longest.length()){
                longest = storedMessages[i];
            }
        }

        System.out.println("Longest Message:");
        System.out.println(longest);
    }

    else if(option == 3){

        //Search for message by using the message ID - PART 3 POE
        System.out.print("Enter Message ID: ");
        String searchID = input.nextLine();
        
        int count = 0;

        for(int i = 0; i < storedCount; i++){

            if(messageIDs[i] != null &&
                messageIDs[i].equals(searchID)){

                System.out.println("Messsage Found");
                System.out.println("Recipient: " + recipients[i]);
                System.out.println("Message: " + storedMessages[i]);
                
                count++;
            }
        }
        if(count == 0){
            System.out.println("Message ID not found");
        }
    }

    else if(option == 4){
        
        //Search for messages that belong to specific recipients

        System.out.print("Enter Recipient Number: ");
        String searchRecipient = input.nextLine();
        
        int count = 0;

        for(int i = 0; i < storedCount; i++){

            if(recipients[i] != null &&
                recipients[i].equals(searchRecipient)){

                System.out.println("Recipient: " + recipients[i]);
                System.out.println("Message: " + storedMessages[i]);
                
                count++;
            }
        }
        if(count == 0){
            System.out.println("No messages found for this recipients.");
        }
    }

    else if(option == 5){
        
        //Delete a stored message using its message hash - PART 3 POE
        System.out.print("Enter Message Hash: ");
        String deleteHash = input.nextLine();
        
        int count = 0;

        for(int i = 0; i < storedCount; i++){

            if( messageHashes[i] != null &&
                messageHashes[i].equals(deleteHash)){

                storedMessages[i] = null;
                messageHashes[i] = null;
                messageIDs[i] = null;
                recipients[i] = null;

                System.out.println("Message successfully deleted.");
                count++;
            }
        }
        if(count == 0){
            System.out.println("Message hash not found.");
        }
    }

    else if(option == 6){
 
        //Display a report of all the messages that are stored - PART 3 POE
        System.out.println("\n----- REPORT -----");

        for(int i = 0; i < storedCount; i++){

            if(storedMessages[i] != null){

                System.out.println("Message Hash: " + messageHashes[i]);
                System.out.println("Recipient: " + recipients[i]);
                System.out.println("Message: " + storedMessages[i]);
                System.out.println();
            }
        }
    }
}
    }
    input.close();
   } 
}
    

 

}