 package com.mycompany.main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList; 
import java.util.Random; 
import java.util.Scanner;

public class Message { 

  
    //Variables 
    private String messageID; 
    private int messageNumber; 
    private String recipient; 
    private String messageText; 
    private String messageHash; 

   //Tracking variables  
    private static ArrayList<String> sentMessages = new ArrayList<>(); 
    private static int totalMessagesSent = 0; 

    //Constructor 
    public Message(int messageNumber, String recipient, String messageText) { 
        this.messageNumber = messageNumber; 
        this.recipient = recipient; 
        this.messageText = messageText; 


        //Call helper methods to assign IDs and Hashes automatically 
        this.messageID = generateMessageID(); 
        this.messageHash = createMessageHash(); 

    } 

  
    //Helper method to make a random 12 digit ID
    private String generateMessageID() { 
        Random rand = new Random(); 

        long min = 1000000000L; 
        long max = 9999999999L; 
        long id = min + (long)(rand.nextDouble() * (max - min)); 
        return String.valueOf(id); 

    } 

    //Check ID 
    public boolean checkMessageID() { 
        if (messageID.length() <= 12) { 
           return true; 
        } else { 
            return false; 
       } 

    } 

  
    //Validate 
    public String checkRecipientCell() { 
      
        if (recipient.length() <= 12 && recipient.startsWith("+")) { 
            return "Cell phone number successfully captured."; 
        }  

        return "Cell phone number is incorrectly formatted or does not contain an international code.";
    } 


    //Builds the tracking hash string 
    public String createMessageHash() { 
        String idPart = messageID.substring(0, 2); 
     

        String[] words = messageText.trim().split(" "); 
        String firstWord = words[0]; 
        String lastWord = words[words.length - 1]; 
         
        //Clean up punctuation  
        String cleanLastWord = ""; 

        for (int i = 0; i < lastWord.length(); i++) { 
            char ch = lastWord.charAt(i); 
            if (Character.isLetterOrDigit(ch)) { 
                cleanLastWord += ch; 
            } 

        } 
    

        this.messageHash = (idPart + ":" + messageNumber + ":" + firstWord + cleanLastWord).toUpperCase(); 
        return this.messageHash; 

    } 

  
    //Ask the user what to do with the message 
    public String SentMessage(Scanner input) { 
        System.out.println("\nWhat would you like to do with this message?"); 
        System.out.println("1) Send Message"); 
        System.out.println("2) Disregard Message"); 
        System.out.println("3) Store Message to send later"); 
        System.out.print("Choose an option: "); 

        String choice = input.nextLine().trim(); 
 
        if (choice.equals("1")) { 
            totalMessagesSent++; 
           sentMessages.add(printMessageDetails()); 
            return "Message successfully sent."; 
        } else if (choice.equals("2")) { 
            return "Press 0 to delete the message."; 
        } else if (choice.equals("3")) { 
            return "Message successfully stored."; 
        } else { 
            return "Invalid option selected."; 
        } 

    } 


    public String printMessageDetails() { 
        String details = "Message ID: " + messageID + 

                         " | Message Hash: " + messageHash + 

                         " | Recipient: " + recipient + 

                         " | Message: " + messageText; 

        return details; 
    } 

  
    //Print message history 
    public static String printMessages() {
        if (sentMessages.isEmpty()) { 
            return "No messages sent yet."; 

        } 
        
        String output = "=== Sent Messages ===\n"; 
        for (int i = 0; i < sentMessages.size(); i++) { 
            output += sentMessages.get(i) + "\n"; 
        } 
        return output; 
    } 
    //Counter for tracking total messages sent
    public static int returnTotalMessages() { 
        return totalMessagesSent; 
    } 


    public String getMessageID() { 
        return this.messageID;  
    } 

     

    public String getMessageHash() {  
        return this.messageHash;  
    } 

     

    public String getRecipient() {  
        return this.recipient;  
    } 

     
    public String getMessageText() {  

        return this.messageText;  
    }

    public int getMessageNumber() {  
        return this.messageNumber;  
    } 

}