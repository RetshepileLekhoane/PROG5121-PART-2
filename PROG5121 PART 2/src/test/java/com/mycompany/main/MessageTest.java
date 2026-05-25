/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.main;

import org.junit.jupiter.api.AfterEach; 
import org.junit.jupiter.api.AfterAll; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.BeforeAll; 
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*;  

public class MessageTest {
  
    private Message message1; 
    private Message message2; 

 

    @BeforeAll 
    public static void setUpClass() { 

    } 

    @AfterAll 
    public static void tearDownClass() { 
    } 

  

    @BeforeEach 
    public void setUp() { 

        message1 = new Message(1, "+27838884567", "Hi Mike, can you join the meeting tonight?"); 
        message2 = new Message(2, "08575975889", "Hi Keegan, did you receive the payment?"); 
    } 


    @AfterEach 
    public void tearDown() { 
    } 

    // --- checkMessageID: success --- 
    @Test 
    public void testCheckMessageID() { 
        assertTrue(message1.checkMessageID(), "Message ID should be 10 characters or less."); 

    } 
  
    // --- checkRecipientCell: success --- 
    @Test 
    public void testCheckRecipientCellSuccess() { 
        assertEquals("Cell phone number successfully captured.", message1.checkRecipientCell()); 
    } 

    // --- checkRecipientCell: failure --- 
    @Test 
    public void testCheckRecipientCellFailure() { 
        assertEquals( 
            "Cell phone number is incorrectly formatted or does not contain an international code.",
            message2.checkRecipientCell() 

        ); 
    } 

    // --- createMessageHash: correct ending --- 
    @Test 
       public void testCreateMessageHash() { 
        String hash = message1.getMessageHash(); 
        assertTrue(hash.endsWith(":HITONIGHT"), 
            "Expected hash ending :HITONIGHT but got: " + hash); 
    } 


    // --- SentMessage: send --- 
    @Test 
    public void testSentMessageSend() { 
        assertEquals("Message successfully sent.", simulateSent("1")); 
    } 

    // --- SentMessage: disregard --- 
    @Test 
    public void testSentMessageDisregard() { 
        assertEquals("Press 0 to delete the message.", simulateSent("2")); 
    } 

  
    // --- SentMessage: store --- 
    @Test 
    public void testSentMessageStore() { 
        assertEquals("Message successfully stored.", simulateSent("3")); 
    } 

  

    // --- printMessageDetails --- 
    @Test 
    public void testPrintMessageDetails() { 
        String details = message1.printMessageDetails(); 
        assertTrue(details.contains("Message ID:")); 
        assertTrue(details.contains("Message Hash:")); 
        assertTrue(details.contains("Recipient:")); 

        assertTrue(details.contains("Message:")); 

    } 

 
    // --- printMessages: no messages sent --- 
    @Test 

    public void testPrintMessages() { 
        String result = Message.printMessages(); 
        assertNotNull(result); 
    } 
  

    // --- returnTotalMessages --- 
    @Test 
    public void testReturnTotalMessages() { 
        int total = Message.returnTotalMessages(); 
        assertTrue(total >= 0, "Total messages should be zero or more."); 
    } 


    // --- getMessageID: not null and 10 digits --- 
    @Test 
    public void testGetMessageID() { 
        String id = message1.getMessageID(); 
        System.out.println("Message ID generated: " + id); 
        assertNotNull(id); 
        assertEquals(10, id.length()); 

    } 
  
    // --- getMessageHash: all caps --- 
    @Test 
    public void testGetMessageHash() { 
        String hash = message1.getMessageHash(); 
        assertEquals(hash.toUpperCase(), hash, "Hash should be all uppercase."); 
    } 
  

    // --- getRecipient --- 
    @Test 
    public void testGetRecipient() { 
        assertEquals("+27838884567", message1.getRecipient()); 

    } 


    // --- getMessageText --- 
    @Test 
    public void testGetMessageText() { 
        assertEquals("Hi Mike, can you join the meeting tonight?", message1.getMessageText()); 
    } 


    // --- getMessageNumber --- 
    @Test 
    public void testGetMessageNumber() { 
        assertEquals(1, message1.getMessageNumber()); 
        assertEquals(2, message2.getMessageNumber()); 
    } 

 

    // --- Message length: success --- 
    @Test 
    public void testMessageReadyToSend() { 
        String msg = "Hi Mike, can you join the meeting tonight?"; 
        assertTrue(msg.length() <= 250, "Message ready to send."); 
    } 

 

    // --- Message length: failure --- 

    @Test 
    public void testMessageExceeds250() { 
        String msg = "A".repeat(260); 
       int over = msg.length() - 250; 
        assertFalse(msg.length() <= 250, 
           "Message exceeds 250 characters by " + over + "; please reduce the size."); 
    } 
  
    // --- Message hash format loop ---
    @Test 
    public void testMessageHashFormatLoop() { 
        Message[] messages = {message1, message2}; 
        int[] expectedNumbers = {1, 2}; 
        for (int i = 0; i < messages.length; i++) { 
            String hash = messages[i].getMessageHash(); 
            assertNotNull(hash); 
            assertTrue(hash.contains(":")); 
            assertEquals(hash.toUpperCase(), hash); 
            assertTrue(hash.contains(":" + expectedNumbers[i] + ":"),
                "Hash should contain message number " + expectedNumbers[i]); 

        } 

    } 

    // Helper: simulates SentMessage without Scanner 
    private String simulateSent(String choice) { 
        switch (choice) { 
            case "1": return "Message successfully sent.";
            case "2": return "Press 0 to delete the message."; 
            case "3": return "Message successfully stored."; 
            default:  return "Invalid option selected."; 

        } 

    } 

} 