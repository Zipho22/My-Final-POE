/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.chatapp;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;


public class Message {
    
     // These are the details that every message has
    String messageID;     // The unique ID for this message
    int    messageNumber; // Which number message this is
    String recipient;     // The phone number we are sending to
    String message;       // The actual text of the message

    //counting how many messages have been sent in total
    static int totalMessages = 0;

    //PART 3 POE Arraylist: These lists store all the messages while the app is running
    static ArrayList<String> sentMessages        = new ArrayList<>();
    static ArrayList<String> disregardedMessages = new ArrayList<>();
    static ArrayList<String> storedMessages      = new ArrayList<>();
    static ArrayList<String> sentMessageHashes = new ArrayList<>(); 
    static ArrayList<String> sentMessageIDs    = new ArrayList<>(); 
    static ArrayList<String> sentRecipients    = new ArrayList<>(); 
    static ArrayList<String> storedMessageHashes = new ArrayList<>(); 
    static ArrayList<String> storedMessageIDs    = new ArrayList<>(); 
    static ArrayList<String> storedRecipients    = new ArrayList<>(); 

    // creating a new Message object
    // It takes the ID, message number, recipient, and message text and saves them
    public Message(String ID, int Digits, String Cell, String messages) {
        messageID     = ID;
        messageNumber = Digits;
        recipient     = Cell;
        message       = messages;
    }

    //PART 2 POE: Check if the message ID is 10 characters or less
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    //PART 2: Check if the recipient number is correctly formatted
        public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    //PART 2 POE: Check if the message is 250 characters or less
    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extra = message.length() - 250;
            return "Message exceeds 250 characters by " + extra +
                   " characters; please reduce the size.";
        }
    }

    // ── PART 2 POE: Create a short unique code (hash) for the message
        public String createMessageHash() {
        String[] words   = message.split(" ");   // Split the message into individual words
        String firstWord = words[0].toUpperCase();               // Grab the first word
        String lastWord  = words[words.length - 1].toUpperCase(); // Grab the last word
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    //PART 2 and PART 3: Decide what to do with the message
    public String sentMessage(int choice) {

        // Create the hash once so we can save it
        String hash = createMessageHash();

        if (choice == 1) {
            // PART 3 POE: Add all the details to the sent lists
            sentMessages.add(message);
            sentMessageHashes.add(hash);
            sentMessageIDs.add(messageID);
            sentRecipients.add(recipient);
            totalMessages++; // Add one to the total count
            return "Message successfully sent.";

        } else if (choice == 2) {
            // PART 3 POE: Only save the message text 
            disregardedMessages.add(message);
            return "Message discarded.";

        } else if (choice == 3) {
            // PART 3 POE: Add all the details to the stored lists
            storedMessages.add(message);
            storedMessageHashes.add(hash);
            storedMessageIDs.add(messageID);
            storedRecipients.add(recipient);
            storeMessage(); // Also write it to the JSON file
            return "Message successfully stored.";

        } else {
            return "Invalid option.";
        }
    }

    //PART 2 POE: Return all the details of this message as one string
    public String printMessages() {
        return "\nMessage ID: "   + messageID +
               "\nMessage Hash: " + createMessageHash() +
               "\nRecipient: "    + recipient +
               "\nMessage: "      + message;
    }

    //PART 2 POE: Return the total number of messages sent so far
    public int returnTotalMessages() {
        return totalMessages;
    }

    //PART 2: Save the message to a JSON file on the computer
        public String storeMessage() {
        // Build the text that will be written to the file in JSON format
        String json =
                "{\n" +
                "  \"MessageID\": \""     + messageID     + "\",\n" +
                "  \"MessageNumber\": \"" + messageNumber + "\",\n" +
                "  \"Recipient\": \""     + recipient     + "\",\n" +
                "  \"Message\": \""       + message       + "\"\n"  +
                "}\n";
        try {
            // Open the file and add to it without deleting what is already there
            FileWriter file = new FileWriter("messages.json", true);
            file.write(json);
            file.close();
            return "Message successfully stored.";
        } catch (IOException e) {
            return "Error storing message.";
        }
    }

        // PART 3: Methods for the Stored Messages menu
    

    // PART 3 POE: Show the recipient and message for every stored message
    public static String displayAllStoredMessages() {
        // If the list is empty, tell the user
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }
        String result = "";
        // Loop through every stored message and add it to the result
        for (int i = 0; i < storedMessages.size(); i++) {
            result = result + "Recipient: " + storedRecipients.get(i) + "\n";
            result = result + "Message: "   + storedMessages.get(i)   + "\n\n";
        }
        return result;
    }

    // PART 3 POE: Find and return the longest stored message
    public static String getLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }
        String longest = ""; // Start with an empty string
        // Go through each stored message and keep track of the longest one
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).length() > longest.length()) {
                longest = storedMessages.get(i); // This one is longer, so replace it
            }
        }
        return longest;
    }

    // PART 3 POE: Search for a message using its ID
    // Checks both sent and stored messages because the ID could be in either list
    public static String searchByMessageID(String searchID) {

        // First check the sent messages list
        for (int i = 0; i < sentMessageIDs.size(); i++) {
            if (sentMessageIDs.get(i).equals(searchID)) {
                return "Recipient: " + sentRecipients.get(i) +
                       "\nMessage: " + sentMessages.get(i);
            }
        }

        //Check the stored messages list
        for (int i = 0; i < storedMessageIDs.size(); i++) {
            if (storedMessageIDs.get(i).equals(searchID)) {
                return "Recipient: " + storedRecipients.get(i) +
                       "\nMessage: " + storedMessages.get(i);
            }
        }

        // If we get here, the ID was not found in any of the list
        return "Message ID not found.";
    }

    // PART 3 POE: Find all messages sent or stored for a specific recipient number
    public static String searchByRecipient(String searchRecipient) {
        String result = "";

        // Check the sent messages list for this recipient
        for (int i = 0; i < sentRecipients.size(); i++) {
            if (sentRecipients.get(i).equals(searchRecipient)) {
                result = result + "Message: " + sentMessages.get(i) + "\n";
            }
        }

        // Check the stored messages list for this recipient
        for (int i = 0; i < storedRecipients.size(); i++) {
            if (storedRecipients.get(i).equals(searchRecipient)) {
                result = result + "Message: " + storedMessages.get(i) + "\n";
            }
        }

        // If nothing was found in either list, let the user know
        if (result.equals("")) {
            return "No messages found for this recipient.";
        }
        return result;
    }

    // PART 3 POE: Delete a stored message by finding it using its hash
    public static String deleteMessageByHash(String hash) {
        // Go through the stored message hashes and look for a match
        for (int i = 0; i < storedMessageHashes.size(); i++) {
            if (storedMessageHashes.get(i).equals(hash)) {
                // Save the message text so we can confirm what was deleted
                String deleted = storedMessages.get(i);
                // Remove the message from all four stored lists at the same position
                storedMessages.remove(i);
                storedMessageHashes.remove(i);
                storedMessageIDs.remove(i);
                storedRecipients.remove(i);
                return "Message \"" + deleted + "\" successfully deleted.";
            }
        }
        // If we get here, no message with that hash was found
        return "Message hash not found.";
    }

    // PART 3 POE: Show a full report of all stored messages
    // Each entry shows the hash, recipient, and message text
    public static String displayReport() {
        if (storedMessages.isEmpty()) {
            return "No stored messages to report.";
        }
        String result = "";
        // Loop through every stored message and add all its details to the report
        for (int i = 0; i < storedMessages.size(); i++) {
            result = result + "Message Hash: " + storedMessageHashes.get(i) + "\n";
            result = result + "Recipient: "    + storedRecipients.get(i)    + "\n";
            result = result + "Message: "      + storedMessages.get(i)      + "\n\n";
        }
        return result;
    }

    // PART 3: Helper method 
    // Returns all sent messages as one big string so the test can check them
    public static String getSentMessagesAsString() {
        String result = "";
        for (int i = 0; i < sentMessages.size(); i++) {
            result = result + sentMessages.get(i) + "\n";
        }
        return result;
    }
}