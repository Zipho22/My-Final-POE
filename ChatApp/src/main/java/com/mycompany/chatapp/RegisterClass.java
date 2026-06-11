package com.mycompany.chatapp;

public class RegisterClass {

    private String username;
    private String password;
    private String phone;
    private String firstName;
    private String lastName;

    public RegisterClass(String username, String password, String phone, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // ===== USERNAME VALIDATION =====
    private boolean checkUsername() {
        return username.contains("_") && username.length() <= 5;
    }

    // ===== PASSWORD VALIDATION =====
    private boolean checkPassword() {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[0-9].*") &&
               password.matches(".*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>?,./].*");
    }

    // ===== PHONE VALIDATION =====
    private boolean checkPhone() {
        return phone.startsWith("+27") && phone.length() <= 13;
    }

    // ===== REGISTER METHOD (USED BY TESTS) =====
    public String registerUser() {

        boolean u = checkUsername();
        boolean p = checkPassword();
        boolean ph = checkPhone();

        if (!u) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!p) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!ph) {
            return "Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.";
        }

        return "Username successfully captured. Password successfully captured. Cell phone number successfully added.";
    }

    // ===== LOGIN =====
    public boolean loginUser(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }

    // ===== LOGIN STATUS MESSAGE =====
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again!";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}