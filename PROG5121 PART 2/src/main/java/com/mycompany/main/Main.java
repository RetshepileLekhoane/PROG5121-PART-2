/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

/**
 *
 * @author Student
 */import java.util.Scanner;
 
public class Main { 

    public static void main(String[] args) { 

        Scanner input = new Scanner(System.in); 
        
        Login user = new Login(); 

        String firstname; 
        String username; 
        String password; 
        String cellphone; 
        String message;
        
        //-----Registration Loop------
        do { 
            System.out.println("\n=== Registration ==="); 
            System.out.print("Enter firstname: "); 
            firstname = input.nextLine(); 

           
            System.out.print("Enter lastname: "); 
            String lastname = input.nextLine(); 
 

            System.out.print("Enter username: "); 
            username = input.nextLine(); 

            System.out.print("Enter password: "); 
            password = input.nextLine(); 


            System.out.print("Enter cellphone number (+27...): "); 
            cellphone = input.nextLine(); 

            message = user.registerUser(firstname, username, password, cellphone); 
            System.out.println(message); 

  
        } while (!message.equals("User successfully registered.")); 

  
        //-----LOGIN------ 
        int attempts = 0; 
        boolean status = false; 
        
        while (attempts < 3 && !status) { 
            
            System.out.println("\n=== Login ==="); 
            
            System.out.print("Enter username: "); 
            
            String loginUser = input.nextLine(); 
            
            System.out.print("Enter password: "); 
            String loginPass = input.nextLine(); 
            
  
            status = user.loginUser(loginUser, loginPass); 
            System.out.println(user.returnLoginStatus(status)); 
            attempts++;
            if (!status && attempts < 3) { 
                System.out.println("Attempts left: " + (3 - attempts)); 
            } 
        } 
        //If all the attempts are used lock the account 
        if (!status) { 
            System.out.println("Too many failed attempts. Account locked."); 
        return;
        }
        //===== QUICKCHAT)===== 
        System.out.println("\nWelcome to QuickChat.");
        
        int numMessages = 0;
        while (numMessages <= 0) { 
        System.out.print("How many messages do you wish to send? "); 
            try {
                
                             
        numMessages = Integer.parseInt(input.nextLine().trim()); 
       if (numMessages <= 0) System.out.println("Please enter a number greater than 0."); 
            } catch (NumberFormatException e) { 
        System.out.println("Invalid input. Please enter a whole number."); 

            } 

        } 

        int messageCounter = 0; 
        boolean running = true; 
        while (running) { 

         System.out.println("\n--- QuickChat Menu ---"); 
         System.out.println("1) Send Messages"); 
         System.out.println("2) Show recently sent messages"); 
         System.out.println("3) Quit"); 
         System.out.print("Choose an option: "); 
         String choice = input.nextLine().trim(); 

  

         switch (choice) { 
            case "1": 
                messageCounter = 0; 
                for (int i = 0; i < numMessages; i++) { 
                    
                        messageCounter++; 
       System.out.println("\n--- Message " + messageCounter + " of " + numMessages + " ---");
       System.out.print("Enter recipient cell number (+international code, max 10 chars): "); 
       String recipient = input.nextLine().trim(); 

            String msgText = ""; 
               boolean validMessage = false; 
                    while (!validMessage) { 

        System.out.print("Enter message (max 250 characters): "); 
                msgText = input.nextLine(); 

                        if (msgText.length() > 250) { 
                             int over = msgText.length() - 250; 
         System.out.println("Please enter a message of less than 250 characters."); 

         System.out.println("Message exceeds 250 characters by " + over + "; please reduce the size."); 
                            } else { 

                validMessage = true; 
                
                  } 

                    } 
                    
        Message msg = new Message(messageCounter, recipient, msgText);
        System.out.println(msg.checkRecipientCell()); 
        System.out.println("Message ID generated: " + msg.getMessageID()); 
        System.out.println("Message Hash: " + msg.getMessageHash()); 

  
            String result = msg.SentMessage(input); 
            System.out.println(result); 

                if (result.equals("Message successfully sent.")) { 
             System.out.println("\n" + msg.printMessageDetails()); 
            } 
                    } 
             System.out.println("\nTotal messages sent: " + Message.returnTotalMessages()); 
                   break; 

                case "2":
                System.out.println("Coming Soon."); 
                    break; 

                case "3": 
                running = false; 
                System.out.println("Goodbye!"); 
                 break; 

                default: 
                    System.out.println("Invalid option. Please choose 1, 2, or 3."); 
            } 
        } 

        System.out.println("\nTotal messages sent this session: " + Message.returnTotalMessages()); 
        input.close(); 

    } 

} 
     

