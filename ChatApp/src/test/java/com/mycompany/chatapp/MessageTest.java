/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

   
    @BeforeEach
    public void setUp() {

        // Clear ArrayLists before every test
        Message.sentMessages.clear();
        Message.disregardedMessages.clear();
        Message.storedMessages.clear();

        Message.sentMessageHashes.clear();
        Message.sentMessageIDs.clear();
        Message.sentRecipients.clear();

        Message.storedMessageHashes.clear();
        Message.storedMessageIDs.clear();
        Message.storedRecipients.clear();

        // Test Data required for Part 3
        Message msg1 = new Message(
                "1000000001",
                0,
                "+27831234567",
                "Did you get the cake?"
        );

        Message msg2 = new Message(
                "1000000002",
                1,
                "+27831234568",
                "Where are you? You are late! I have asked you to be on time."
        );

        Message msg3 = new Message(
                "1000000003",
                2,
                "+27831234567",
                "Okay, I am leaving now."
        );

        msg1.sentMessage(3);
        msg2.sentMessage(3);
        msg3.sentMessage(3);
    }

    // ==========================
    // PART 2 TESTS
    // ==========================

    @Test
    public void testMessageLengthSuccess() {

        Message msg = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "Message ready to send.",
                msg.checkMessageLength()
        );
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage =
                new String(new char[260]).replace("\0", "a");

        Message msg = new Message(
                "0012345678",
                0,
                "+27718693002",
                longMessage
        );

        assertTrue(
                msg.checkMessageLength()
                        .startsWith("Message exceeds 250 characters by")
        );
    }

    @Test
    public void testRecipientSuccess() {

        Message msg = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Hi Mike"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                msg.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientFailure() {

        Message msg = new Message(
                "0012345678",
                0,
                "08575975889",
                "Hi Keegan"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell()
        );
    }

    @Test
    public void testMessageHashCase1() {

        Message msg = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Hi Mike can you join us for dinner tonight"
        );

        assertEquals(
                "00:0:HITONIGHT",
                msg.createMessageHash()
        );
    }

    @Test
    public void testMessageID() {

        Message msg = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Test Message"
        );

        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testMessageSentSend() {

        Message msg = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Test Message"
        );

        assertEquals(
                "Message successfully sent.",
                msg.sentMessage(1)
        );
    }

    @Test
    public void testMessageSentDiscard() {

        Message msg = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Test Message"
        );

        assertEquals(
                "Message discarded.",
                msg.sentMessage(2)
        );
    }

    @Test
    public void testMessageSentStore() {

        Message msg = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Test Message"
        );

        assertEquals(
                "Message successfully stored.",
                msg.sentMessage(3)
        );
    }

    @Test
    public void testTotalMessages() {

        Message.totalMessages = 5;

        Message msg = new Message(
                "0012345678",
                0,
                "+27718693002",
                "Test Message"
        );

        assertEquals(
                5,
                msg.returnTotalMessages()
        );
    }

    
    // PART 3 TESTS
    

    @Test
    public void testDisplayAllStoredMessages() {

        String result =
                Message.displayAllStoredMessages();

        assertTrue(result.contains("Did you get the cake?"));
        assertTrue(result.contains("Okay, I am leaving now."));
    }

    @Test
    public void testLongestStoredMessage() {

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                Message.getLongestStoredMessage()
        );
    }

    @Test
    public void testSearchByMessageID() {

        String result =
                Message.searchByMessageID("1000000001");

        assertTrue(result.contains("+27831234567"));
        assertTrue(result.contains("Did you get the cake?"));
    }

    @Test
    public void testSearchByRecipient() {

        String result =
                Message.searchByRecipient("+27831234567");

        assertTrue(result.contains("Did you get the cake?"));
        assertTrue(result.contains("Okay, I am leaving now."));
    }

    @Test
    public void testDeleteMessageByHash() {

        String hash =
                Message.storedMessageHashes.get(0);

        String result =
                Message.deleteMessageByHash(hash);

        assertTrue(
                result.contains("successfully deleted")
        );
    }

    @Test
    public void testDisplayReport() {

        String report =
                Message.displayReport();

        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
    }
}