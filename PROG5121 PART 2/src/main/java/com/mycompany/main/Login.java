/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author Student
 */
public class Login {
    

private String firstname; 
private String username; 
private String password; 
private String cellphoneNumber; 
 
// Username validation 
public boolean checkUserName(String username) { 
    return username.contains("_") && username.length() >= 5; 
} 
 
// Password validation 
public boolean checkPasswordComplexity(String password) { 
    return password.length() >= 8 && 
           password.matches(".*[A-Z].*") && 
           password.matches(".*[0-9].*") && 
           password.matches(".*[^a-zA-Z0-9].*"); 
} 
 
// Cellphone number validation with regex
public boolean checkCellPhoneNumber(String cellphoneNumber) { 
    return cellphoneNumber.matches("^\\+27\\d{9}$"); 
} 
 
// Register user the user
public String registerUser(String firstname,String username, String password, String cellphoneNumber) { 
 
    if (!checkUserName(username)) { 
          
        return "Username is not correctly formatted; please ensure that your username has an underscore and is no more than five characters in length."; 
       
    } 
 
    if (!checkPasswordComplexity(password)) { 
        return "Password is not correctly formatted; please ensure that the password has at least eight characters, a capital letter, a number, and a special character."; 
    } 
 
    if (!checkCellPhoneNumber(cellphoneNumber)) { 
        return "Cell phone number incorrectly formatted or does not have an international code."; 
         
    } 
    //Save users details after successful registration
    this.firstname = firstname; 
    this.username = username; 
    this.password = password; 
    this.cellphoneNumber = cellphoneNumber; 
 
    return "User successfully registered."; 
} 
 
// Login method 
public boolean loginUser(String username, String password) { 
    return this.username.equals(username) && this.password.equals(password); 
} 
 
// Login message 
public String returnLoginStatus(boolean loginStatus ) { 
    if (loginStatus) {  
           
     return "Welcome " + firstname + ", it is great to see you again."; 
    } else { 
        return "Username or password incorrect, please try again."; 
    } 
} 
  

} 

