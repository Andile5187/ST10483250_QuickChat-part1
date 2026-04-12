/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package st10483250_quickchart;
import java.util.Scanner;
/**
 *
 * @author Andile
 */
// here we are creating our own class 
class login{
    
String storeUsername;
String storePassword;
String storeCellPhoneNumber;
Scanner input=new Scanner(System.in);
// here we are checking the username if it reaches the username requirment
    boolean checkUsername(String username){
    if(username.contains("_")&& username.length()==5){
        System.out.println("username succefully captured");
        System.out.println("Welcome<user first name>,<userlast name> it is great to see you again.");
    return true;
    }
    else{
      System.out.println(" username is not correctly formatted please ensure that your user name contains an underscore and is no more than five characters in length");
        System.out.println("username incorrect,please try again");
      return false;
    }
   
    }   
// here we are checking the password if it reaches the password requirment
    boolean checkPasswordComplexity(String password ){
        boolean hasUpperCase=false;
        boolean hasDigit=false;
        boolean hasSpecial=false;
        
         if (password.length()>8){
         
             for(char c: password.toCharArray())  {
        
         if(Character.isUpperCase(c)){
         hasUpperCase=true;
         }else if(Character.isDigit(c)){
         hasDigit=true;
         }else if(!Character.isLetterOrDigit(c)){
         hasSpecial=true;
         }
        
        
        System.out.println("password succefully captured");
           System.out.println("Welcome<user first name>,<userlast name> it is great to see you again."); 
         return true;
         }
         }else{
              System.out.println("Password is not correctly formatted; please ensure that the password contains a capital letter,a number and a special character");
               System.out.println("Password incorrect,please try again");
              return false;
         }
    return hasUpperCase&&hasDigit&& hasSpecial;
    }
    // here we are checking the CellPhoneNumber  if it reaches the CellPhoneNumber requirment
   boolean checkCellPhoneNumber(String cellPhoneNumber){
   if ( cellPhoneNumber.startsWith("+")&&cellPhoneNumber.length()==11){
       System.out.println("Cell Phone number successfully added");
       return true;
   } 
   else{
      System.out.println(" cell phone number incorrectly formated or does not contain international code");
       return false;
     }
   }
         //here we are creating the registeruser method so the user can enter the required registration
           void registerUser(){
              
               System.out.println("\n======== REGISTER ==========");
               System.out.println("Enter Username");
        String Username = input.nextLine();
        System.out.println("Enter Password");
        String Password = input.nextLine();
        System.out.println("Enter CellPhoneNumber");
        String CellPhoneNumber = input.nextLine();
        storeUsername= Username;
        storePassword = Password;
        storeCellPhoneNumber = CellPhoneNumber;
        if (checkUsername(storeUsername) && checkPasswordComplexity(storePassword) && checkCellPhoneNumber(storeCellPhoneNumber)) {
            System.out.println("successful registration!");
        } else {
            System.out.println("faild registration!");
        }
    }
   // here we are creating a userlogin method
    void userlogin() {
        if (storeUsername == null) {
            System.out.println("faild login");
            return;
        }
            
        int attempts = 3;
        boolean success = false;
        System.out.println("\n========LOGIN ==========");

        while (attempts > 0 && !success) {
            System.out.println("Enter Username");
            String Username = input.nextLine();
            System.out.println("Enter Password");
            String Password = input.nextLine();
            System.out.println("Enter CellPhoneNumber");
            String CellPhoneNumber = input.nextLine();
           if(checkUsername(storeUsername)&&checkPasswordComplexity(storePassword)&&checkCellPhoneNumber(storeCellPhoneNumber)){
               System.out.println("successful login");
               success=true;
           }else{
           attempts--;
           if(attempts>0){
               System.out.println("incorrect details.Attempt left"+attempts);
           }
           }
           if(!success){
               System.out.println("Too many failed attempts.Accountis locked");
           }
        }
   }
}

               
           
           
           
           
           
public class ST10483250_Quickchart {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // here we are constracting the class that we created to the main class so it can run
        Scanner enterMenu=new Scanner(System.in);
        login loginapp=new login();
          // here it is were the user can choise if they want to register,loging or want to go tothe menu  
        int choice;
        do{
        System.out.println("\n======MENU=====");
        System.out.println("1.Register");
        System.out.println("2.Login");
        System.out.println("3.Exit");
            System.out.println("enter Menu Option");
        choice=enterMenu.nextInt();
        switch(choice){
            case 1:
                loginapp.registerUser();
                break;
            case 2:
                loginapp.userlogin();
                break;
            case 3:
                    System.out.println("good bye ");
                break;
            default:
                System.out.println("invalid choice");
        }
        }while(choice!=3);{
           enterMenu.close();
    }
    
    
}
    
}
