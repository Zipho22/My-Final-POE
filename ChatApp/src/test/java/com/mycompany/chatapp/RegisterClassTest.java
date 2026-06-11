/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class RegisterClassTest {
    
 

    // Helper method to create a valid user quickly
    private RegisterClass createValidUser() {
        return new RegisterClass(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "John",
                "Smith"
        );
    }

    // ===== TESTING USERNAME =====

    @Test
    public void testUsernameCorrectlyFormatted_ReturnsSuccessMessage() {
        RegisterClass user = createValidUser();

        String expected = "Username successfully captured. " +
                "Password successfully captured. " +
                "Cell phone number successfully added.";

        assertEquals(expected, user.registerUser());
    }

    @Test
    public void testUsernameIncorrectlyFormatted_ReturnsErrorMessage() {
        RegisterClass user = new RegisterClass(
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        String expected = "Username is not correctly formatted; " +
                "please ensure that your username contains " +
                "an underscore and is no more than five " +
                "characters in length.";

        assertEquals(expected, user.registerUser());
    }

    // ===== TESTING PASSWORD =====

    @Test
    public void testPasswordDoesNotMeetComplexity_ReturnsErrorMessage() {
        RegisterClass user = new RegisterClass(
                "kyl_1",
                "password",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        String expected =
                "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

        assertEquals(expected, user.registerUser());
    }

    // ===== TESTING PHONE NUMBER =====

    @Test
    public void testCellPhoneCorrectlyFormatted_ReturnsSuccessMessage() {
        RegisterClass user = createValidUser();

        String expected =
                "Username successfully captured. Password successfully captured. Cell phone number successfully added.";

        assertEquals(expected, user.registerUser());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted_ReturnsErrorMessage() {
        RegisterClass user = new RegisterClass(
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553",
                "Kyle",
                "Smith"
        );

        String expected =
                "Cell phone number incorrectly formatted or does not contain international code; " +
                "please correct the number and try again.";

        assertEquals(expected, user.registerUser());
    }

    // ===== TESTING LOGIN =====

    @Test
    public void testLoginSuccessful() {
        RegisterClass user = createValidUser();
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        RegisterClass user = createValidUser();
        assertFalse(user.loginUser("wrongUser", "wrongPass"));
    }

    @Test
    public void testReturnLoginStatus_Success() {
        RegisterClass user = createValidUser();

        String expected = "Welcome John Smith, it is great to see you again!";
        assertEquals(expected, user.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatus_Failed() {
        RegisterClass user = createValidUser();

        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, user.returnLoginStatus(false));
    }
}
