/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.st10483250_quickchat;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class login {

    String storeUsername;
    String storePassword;
    String storeCellPhoneNumber;
    Scanner input = new Scanner(System.in);

    boolean checkUsername(String username) {
        if (username.contains("_") && username.length() == 5) {
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length");
            System.out.println("Username incorrect, please try again");
            return false;
        }
    }

    boolean checkPasswordComplexity(String password) {
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        if (password.length() > 8) {
            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    hasUpperCase = true;
                } else if (Character.isDigit(c)) {
                    hasDigit = true;
                } else if (!Character.isLetterOrDigit(c)) {
                    hasSpecial = true;
                }
            }
           
            if (hasUpperCase && hasDigit && hasSpecial) {
                System.out.println("Password successfully captured");
                return true;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains a capital letter, a number and a special character");
                return false;
            }
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains a capital letter, a number and a special character");
            System.out.println("Password incorrect, please try again");
            return false;
        } 
    }

    boolean checkCellPhoneNumber(String cellPhoneNumber) {
          String regex="^\\+27[0-9]{9}$";
        if(cellPhoneNumber.matches(regex)) {
            System.out.println("Cell phone number successfully added");
            return true;
        } else { 
            System.out.println("Cell phone number incorrectly formatted or does not contain international code");
            return false;
        }
    }

    void registerUser() {
        System.out.println("\n======== REGISTER ==========");
        System.out.print("Enter Username: ");
        String username = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();
        System.out.print("Enter Cell Phone Number: ");
        String cellPhoneNumber = input.nextLine();

        storeUsername = username;
        storePassword = password;
        storeCellPhoneNumber = cellPhoneNumber;

        if (checkUsername(storeUsername) && checkPasswordComplexity(storePassword) && checkCellPhoneNumber(storeCellPhoneNumber)) {
            System.out.println("Successful registration!");
        } else {
            System.out.println("Failed registration!");
        }
    }

   
    boolean userlogin() {
        System.out.println("\n======== LOGIN ==========");
        System.out.print("Enter Username: ");
        String username = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();

        if (username.equals(storeUsername) && password.equals(storePassword)) {
            System.out.println("Login successful! Welcome back, " + storeUsername + "!");
            return true;
        } else {
            System.out.println("Login failed. Username or password incorrect.");
            return false;
        }
    }
}
class messegeData { 
    
String messageID;
int messageNumber;
String recipient;
String message;
String messageHash;
 
public messegeData(String messageID, int messageNumber,String recipient,String message, String messageHash ){
this.messageID=messageID;
this.messageNumber=messageNumber;
this.recipient=recipient;
this.message=message;
this.messageHash=messageHash;
}
}

class message {

    private String messageID;
    private int numMessageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    private static String[] storedMessages = new String[100];
    private static int totalMessages = 0;

    
    public message(String messageID, int numMessageNumber, String recipient, String messageText) {
        this.messageID = messageID;
        this.numMessageNumber = numMessageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    
     boolean checkMessageID() { 
        return messageID.length() <= 10;
    }

    String checkRecipientCell() {
        String regex="^\\+27[0-9]{9}$";
        if (recipient.matches(regex)) {
            return "Cell number vaild";
        }
       
        return "Cell phone number incorrectly formatted or does not contain international code";
    }

    String createMessageHash() {
        String idStart = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String first = words[0];
        String last = words[words.length - 1];
        String hash = idStart + ":" + numMessageNumber + ":" + first + last;
        return hash.toUpperCase();
    }

    String SentMessage(Scanner scanner) {
        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1. Send");
        System.out.println("2. Store");
        System.out.println("3. Disregard");
        System.out.print("Choose: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                storeMessages();
                return "Message sent";
            case "2":
                storeMessages();
                return "Message stored";
            case "3":
                return "Message disregarded";
            default:
                return "Invalid choice. Message disregarded.";
        }
    }

   
    static String printMessages() {
        if (totalMessages == 0) {
            return "No messages sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== All Sent Messages =====\n");
        for (int i = 0; i < totalMessages; i++) {
            sb.append(storedMessages[i]).append("\n");
        }
        return sb.toString();
    }

    public static int returnTotalMessage() {
        return totalMessages;
    }

    public void storeMessages() {
        if (totalMessages < storedMessages.length) {
            String json = "{"
                    + "\"messageID\":\"" + messageID + "\","
                    + "\"messageNumber\":" + numMessageNumber + ","
                    + "\"recipient\":\"" + recipient + "\","
                    + "\"message\":\"" + messageText + "\","
                    + "\"messageHash\":\"" + messageHash + "\""
                    + "}";
            storedMessages[totalMessages] = json;
            totalMessages++;
            saveToJsonFile();
        } else {
            System.out.println("Message storage is full.");
        }
    }
    public void saveToJsonFile(){
    Gson gson=new GsonBuilder().setPrettyPrinting().create();
    
    messegeData data=new messegeData(
    messageID,
    numMessageNumber,
    recipient,
    messageText,
    messageHash       
    );
    try (FileWriter writer=new FileWriter("messagees.json",true)){
    gson.toJson(data,writer);
        System.out.println("Message saved to message.json");
    }catch(IOException e){
        System.out.println("Error saving message:"+ e.getMessage());    
            }
    
    }
    
    
    String getMessageID()   { return messageID; }
    String getRecipient()   { return recipient; }
    String getMessage()     { return messageText; }
    String getMessageHash() { return messageHash; }
}
public class ST10483250_QuickChat {
static Scanner scanner = new Scanner(System.in);
    static int numMessageSent = 0;
    static int maxMessages = 0;
    static boolean loggedIn = false;

public static void main(String[] args) {

        login loginApp = new login();
        int choice;

        do {
            System.out.println("\n====== MENU =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter Menu Option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    loginApp.registerUser();
                    break;
                case 2:
                
                    loggedIn = loginApp.userlogin();
                    if (loggedIn) {
                        runQuickChat(); 
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);

        scanner.close();
    }

  
    static void runQuickChat() {
        System.out.println("\nWelcome to QuickChat.");
        System.out.print("How many messages would you like to send? ");
        maxMessages = Integer.parseInt(scanner.nextLine().trim());

        boolean running = true;
        while (running) {
            System.out.println("\n---- Menu -----");
            System.out.println("1. Send Message");
            System.out.println("2. Show recent sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            String menuChoice = scanner.nextLine().trim();

            switch (menuChoice) {
                case "1":
                    sendMessages();
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    running = false;
                    System.out.println("Total messages sent: " + message.returnTotalMessage());
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

   
    static void sendMessages() {
        if (numMessageSent >= maxMessages) {
            System.out.println("Message limit of " + maxMessages + " reached.");
            return;
        }

        int remaining = maxMessages - numMessageSent;
        System.out.println("You can send " + remaining + " more message(s).");

        for (int i = 0; i < remaining; i++) {
            System.out.println("\n--- Message " + (numMessageSent + 1) + " ---");

            String recipient = "";
            while (true) {
                System.out.print("Enter recipient cell number (e.g. +27xxxxxxxx, max 10 chars): ");
                recipient = scanner.nextLine();
                if (recipient.length() <= 10 && recipient.startsWith("+")) break;
                System.out.println("Invalid. Must start with + and be max 10 characters.");
            }

            String messageText = "";
            while (true) {
                System.out.print("Enter your message (max 250 characters): ");
                messageText = scanner.nextLine();
                if (messageText.length() <= 250) break;
                System.out.println("Please enter a message of less than 250 characters.");
            }

            String messageID = generateMessageID();
            numMessageSent++;

            message msg = new message(messageID, numMessageSent, recipient, messageText);

           
            if (!msg.checkMessageID()) {
                System.out.println("Error: Message ID invalid.");
                numMessageSent--; 
                continue;
            }

            String cellCheck = msg.checkRecipientCell();
           
            if (!cellCheck.equals("Cell number valid.")) {
                System.out.println("Error: " + cellCheck);
                numMessageSent--;
                continue;
            }

            System.out.println("Message Hash: " + msg.getMessageHash());

            String result = msg.SentMessage(scanner);
            System.out.println(result);

            if (numMessageSent >= maxMessages) {
                System.out.println("\nMessage limit reached.");
                break;
            }
        }

        
        System.out.println(message.printMessages());
    }

    static String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }
}